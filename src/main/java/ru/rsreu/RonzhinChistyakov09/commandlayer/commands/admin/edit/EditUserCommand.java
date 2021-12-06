package ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.edit;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.RonzhinChistyakov09.commandlayer.CommandResultResponseSendRedirect;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommandResult;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.User;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.UserDao;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.UserRoleDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;
import ru.rsreu.RonzhinChistyakov09.exceptions.LoginBusyException;
import ru.rsreu.RonzhinChistyakov09.logiclayer.EditUserLogic;

public class EditUserCommand implements ActionCommand {

	@Override
	public ActionCommandResult execute(HttpServletRequest request) {
		try {
			UserDao userDao = (UserDao) request.getServletContext().getAttribute("userDao");
			UserRoleDao userRoleDao = (UserRoleDao) request.getServletContext().getAttribute("userRoleDao");
			EditUserLogic logic = new EditUserLogic(userDao);
			EditUserDataTransferObject dto = new EditUserDataTransferObject(userDao, userRoleDao);
			User user = dto.getModel(request);
			logic.editUser(user);
		} catch (DataRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LoginBusyException e) {
			System.out.println("Login is busy");
			e.printStackTrace();
		}

		return new CommandResultResponseSendRedirect("FrontController?command=SHOW_MAIN_ADMIN_PAGE");
	}

}
