package ru.rsreu.RonzhinChistyakov09.logiclayer.admin;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.User;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.UserDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;
import ru.rsreu.RonzhinChistyakov09.exceptions.LoginBusyException;

public class CreateUserLogic {

	private UserDao userDao;

	public CreateUserLogic(UserDao userDao) {
		this.userDao = userDao;
	}

	public void createUser(User user) throws DataRequestException, LoginBusyException {
		if (userDao.getUserByLogin(user.getLogin()) != null) {
			throw new LoginBusyException(
					String.format(Resourcer.getString("exceptions.captain.create.user"), user.getLogin()));
		}
		this.userDao.createUser(user);
	}

}
