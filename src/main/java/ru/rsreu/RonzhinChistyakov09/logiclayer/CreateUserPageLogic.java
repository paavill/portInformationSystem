package ru.rsreu.RonzhinChistyakov09.logiclayer;

import java.util.Collection;

import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.UserRole;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.UserRoleDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;

public class CreateUserPageLogic {

	UserRoleDao userRoleDao;

	public CreateUserPageLogic(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	public Collection<UserRole> getUserRoles() throws DataRequestException {
		return userRoleDao.getUserRoles();
	}
}
