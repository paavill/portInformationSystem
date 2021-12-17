package ru.rsreu.RonzhinChistyakov09.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.datalayer.data.statement.Statement;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.StatementDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;

public class OracleStatementDao implements StatementDao {

	private Connection connection;

	public OracleStatementDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Collection<Statement> getAllStatements() throws DataRequestException {
		Collection<Statement> result = new ArrayList<Statement>();
		String query = Resourcer.getString("requests.sql.get.statements.allStatements");
		try (java.sql.Statement st = this.connection.createStatement()) {
			try (ResultSet resultSet = st.executeQuery(query)) {
				while (resultSet.next()) {
					Statement statement = ResultSetConverter.getStatement(resultSet);
					result.add(statement);
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
		return result;
	}

	@Override
	public void createStatement(Statement statement) throws DataRequestException {
		String query = Resourcer.getString("requests.sql.create.statement");
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			PreparedStatementParametresSetter.set(preparedStatement, statement.getId(), statement.getUser().getId(),
					statement.getShip().getId(), statement.getType().getId(), statement.getStatus().getId(),
					statement.getDoDate());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
	}

	@Override
	public Statement getLastByUserId(int userId) throws DataRequestException {
		String query = Resourcer.getString("requests.sql.get.statement.lastByUserId");
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			PreparedStatementParametresSetter.set(preparedStatement, userId);
			try(ResultSet resultSet = preparedStatement.executeQuery()){
				if(resultSet.next()) {
					return ResultSetConverter.getStatement(resultSet);
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
		return null;
	}

	@Override
	public int getCount() throws DataRequestException {
		int count = -1;
		String query = Resourcer.getString("requests.sql.get.statements.count");
		try (java.sql.Statement statement = this.connection.createStatement()) {
			try (ResultSet resultSet = statement.executeQuery(query)) {
				if (resultSet.next()) {
					count = resultSet.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new DataRequestException(
					String.format(Resourcer.getString("exceptions.sql.request"), e.getMessage()));
		}
		return count;
	}

}
