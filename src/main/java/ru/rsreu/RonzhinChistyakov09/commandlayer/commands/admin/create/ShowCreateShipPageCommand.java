package ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.commandlayer.CommandResultResponseForward;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommandResult;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.UserDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;
import ru.rsreu.RonzhinChistyakov09.logiclayer.admin.CreateShipPageLogic;

public class ShowCreateShipPageCommand implements ActionCommand {
	@Override
	public ActionCommandResult execute(HttpServletRequest request) throws DataRequestException {
		UserDao userDao = (UserDao) request.getServletContext()
				.getAttribute(Resourcer.getString("serlvet.context.dao.users"));
		CreateShipPageLogic logic = new CreateShipPageLogic(userDao);
		Collection<Integer> captainsIds = logic.getCaptainsWithoutShipId();
		request.setAttribute(Resourcer.getString("servlet.requests.attributes.captains.ids"), captainsIds);
		String page = Resourcer.getString("jsp.admin.createShip");	
		return new CommandResultResponseForward(page);
	}

}
