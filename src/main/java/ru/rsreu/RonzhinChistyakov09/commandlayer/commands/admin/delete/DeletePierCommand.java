package ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.delete;

import javax.servlet.http.HttpServletRequest;

import ru.rsreu.RonzhinChistyakov09.commandlayer.CommandResultResponseSendRedirect;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommandResult;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.PierDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;
import ru.rsreu.RonzhinChistyakov09.logiclayer.DeletePierLogic;

public class DeletePierCommand implements ActionCommand {

	@Override
	public ActionCommandResult execute(HttpServletRequest request) {

		try {
			PierDao pierDao = (PierDao) request.getServletContext().getAttribute("pierDao");
			DeletePierLogic logic = new DeletePierLogic(pierDao);
			int pierId = Integer.parseInt(request.getParameter("pierIdToDelete"));
			logic.deletePier(pierId);
		} catch (DataRequestException e) {
			e.printStackTrace();
		}

		return new CommandResultResponseSendRedirect("FrontController?command=SHOW_MAIN_ADMIN_PAGE");
	}
}