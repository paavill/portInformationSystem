package ru.rsreu.RonzhinChistyakov09.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.datalayer.data.product.Product;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.ProductDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;

public class OracleProductDao implements ProductDao {

	private Connection connection;

	public OracleProductDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void unloadProductsToPier(Collection<Product> products) throws DataRequestException {
		String query = Resourcer.getString("requests.sql.create.product");
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			for (Product product : products) {
				PreparedStatementParametresSetter.set(preparedStatement, product.getTitle(), product.getPierId());
				for (int i = 0; i < product.getCount(); i++) {
					preparedStatement.addBatch();
				}
			}
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
	}

	@Override
	public Collection<Product> getAllProducts() throws DataRequestException {
		Collection<Product> result = new ArrayList<Product>();
		String query = Resourcer.getString("requests.sql.get.products.allProducts");
		try (Statement statement = this.connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(query)) {
				while (resultSet.next()) {
					Product product = ResultSetConverter.getProduct(resultSet);
					result.add(product);
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
		return result;
	}

	@Override
	public void loadProductsFromPier(Collection<Product> products) throws DataRequestException {
		String query = Resourcer.getString("requests.sql.delete.product");
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			for (Product product : products) {
				PreparedStatementParametresSetter.set(preparedStatement, product.getPierId(), product.getTitle(),
						product.getCount());
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
	}

	@Override
	public Collection<Product> getProductsInPier(int pierId) throws DataRequestException {
		Collection<Product> result = new ArrayList<Product>();
		String query = Resourcer.getString("requests.sql.get.products.productsInPier");
		try (PreparedStatement statement = this.connection.prepareStatement(query)) {
			PreparedStatementParametresSetter.set(statement, pierId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Product product = ResultSetConverter.getProduct(resultSet);
					result.add(product);
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
		return result;
	}

	@Override
	public int getCountProduct(String title, int pierId) throws DataRequestException {
		String query = Resourcer.getString("requests.sql.get.products.count");
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			PreparedStatementParametresSetter.set(preparedStatement, pierId, title);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
		return 0;

	}

}
