package ru.rsreu.RonzhinChistyakov09.datalayer.oracledb;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.datalayer.data.Ship;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.pier.Pier;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.product.Product;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.statement.Statement;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.statement.StatementStatus;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.statement.StatementType;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.User;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.UserRole;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.UserStatus;

public class ResultSetConverter {

	private ResultSetConverter() {
		
	}
	
	public static User getUser(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.users.id"));
		String login = resultSet.getString(Resourcer.getString("database.users.login"));
		String password = resultSet.getString(Resourcer.getString("database.users.password"));
		String fullName = resultSet.getString(Resourcer.getString("database.users.fullName"));
		UserStatus status = getUserStatus(resultSet);
		UserRole role = getUserRole(resultSet);
		return new User(id, login, password, fullName, status, role);
	}
	
	public static UserStatus getUserStatus(ResultSet resultSet) throws SQLException {
		return  new UserStatus(resultSet.getInt(Resourcer.getString("database.users.statusId")), 
				resultSet.getString(Resourcer.getString("database.users.statusTitle")));
	}
	
	public static UserRole getUserRole(ResultSet resultSet) throws SQLException {
		return new UserRole(resultSet.getInt(Resourcer.getString("database.users.roleId")), 
				resultSet.getString(Resourcer.getString("database.users.roleTitle")));
	}	
	
	public static Ship getShip(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.ships.id"));
		String title = resultSet.getString(Resourcer.getString("database.ships.title"));
		Ship ship = new Ship(id, title);
		return ship;
	}
	
	public static Pier getPier(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.piers.id"));
		int capacity = resultSet.getInt(Resourcer.getString("database.piers.capacity"));
		int residualCapacity = resultSet.getInt(Resourcer.getString("database.piers.capacity.residual"));
		String status = resultSet.getString(Resourcer.getString("database.piers.status"));
		Pier pier = new Pier(id, capacity, residualCapacity, status);
		return pier;
	}
	
	private static Pier getPierForStatement(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.piers.id"));
		int capacity = resultSet.getInt(Resourcer.getString("database.piers.capacity"));
		if(id == 0 && capacity == 0) {
			return null;
		}
		Pier pier = new Pier(id, capacity);
		return pier;
	}
	
	public static Statement getStatement(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.statements.id"));
		User user = getUser(resultSet);
		Ship ship = getShip(resultSet);
		Pier pier = getPierForStatement(resultSet);
		StatementType type = getStatementType(resultSet);
		StatementStatus state = getStatementStatus(resultSet);
		Date doDate = resultSet.getDate(Resourcer.getString("database.statements.doDate"));
		Date finishDate = resultSet.getDate(Resourcer.getString("database.statements.finishDate"));
		Statement statement = new Statement(id, user, ship, pier, type, state, doDate, finishDate);
		return statement;
	}
	
	public static StatementStatus getStatementStatus(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.statements.statusId"));
		String title = resultSet.getString(Resourcer.getString("database.statements.statusTitle"));
		return new StatementStatus(id, title);
	}
	
	public static StatementType getStatementType(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt(Resourcer.getString("database.statements.typeId"));
		String title = resultSet.getString(Resourcer.getString("database.statements.typeTitle"));
		return new StatementType(id, title);
	}

	public static Product getProduct(ResultSet resultSet) throws SQLException {
		String title = resultSet.getString(Resourcer.getString("database.products.title"));
		int pier = resultSet.getInt(Resourcer.getString("database.piers.id"));
		int count = resultSet.getInt(Resourcer.getString("database.products.count"));
		return new Product(title, pier, count);
	}
}
