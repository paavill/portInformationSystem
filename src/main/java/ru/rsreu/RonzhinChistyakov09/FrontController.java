package ru.rsreu.RonzhinChistyakov09;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.commandlayer.CommandFactory;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommandResult;
import ru.rsreu.RonzhinChistyakov09.datalayer.DBType;
import ru.rsreu.RonzhinChistyakov09.datalayer.DaoFactory;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;
/***
 * All request handler.
 * @author pavel
 *
 */
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 3363040659143078486L;

	public FrontController() {
		super();
	}

	/***
	 * Servlet initialization method
	 */
	public void init() throws ServletException {
		Locale.setDefault(Locale.US);
		try {
			DaoFactory factory = DaoFactory.getInstance(DBType.ORACLE);
			ServletContext context = this.getServletContext();
			context.setAttribute(Resourcer.getString("serlvet.context.dao.users"), factory.getUserDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.usersRoles"), factory.getUserRoleDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.usersStatuses"), factory.getUserStatusDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.piers"), factory.getPierDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.ships"), factory.getShipDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.statements"), factory.getStatementDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.statementsStatuses"), factory.getStatementStatusDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.statementsTypes"), factory.getStatementTypeDao());
			context.setAttribute(Resourcer.getString("serlvet.context.dao.products"), factory.getProductDao());
		} catch (Exception e) {
			this.log(String.format(Resourcer.getString("exceptions.daoInit"), e.getMessage()));
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.processRequest(request, response);
	}
	
	/***
	 * Method of processing requests with commands.
	 * @param request - request object. 
	 * @param response - responce object.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionCommand command = CommandFactory.getCommand(request);
		this.log(String.format(Resourcer.getString("log.command.received"), command.getClass().getSimpleName()));
		try {
			ActionCommandResult commandExecutionResult = command.execute(request);
			commandExecutionResult.toResponse(getServletContext(), request, response);
		} catch (DataRequestException e) {
			this.log(e.getMessage());
		}
	}

	public void destroy() {
		super.destroy();
	}
}
