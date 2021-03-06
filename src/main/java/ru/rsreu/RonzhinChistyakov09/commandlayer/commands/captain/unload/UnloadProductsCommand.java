package ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.unload;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.prutzkow.resourcer.Resourcer;

import ru.rsreu.RonzhinChistyakov09.commandlayer.CommandResultResponseSendRedirect;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.JsonToProductFormsDeserializator;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.ProductForm;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommandResult;
import ru.rsreu.RonzhinChistyakov09.datalayer.data.user.User;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.PierDao;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.ProductDao;
import ru.rsreu.RonzhinChistyakov09.datalayer.interfaces.StatementDao;
import ru.rsreu.RonzhinChistyakov09.exceptions.DataRequestException;
import ru.rsreu.RonzhinChistyakov09.exceptions.captain.NotEnoughPierCapacityException;
import ru.rsreu.RonzhinChistyakov09.logiclayer.captain.UnloadProductsLogic;

public class UnloadProductsCommand implements ActionCommand {

	@Override
	public ActionCommandResult execute(HttpServletRequest request) throws DataRequestException {
		ProductDao productDao = (ProductDao) request.getServletContext()
				.getAttribute(Resourcer.getString("serlvet.context.dao.products"));
		StatementDao statementDao = (StatementDao) request.getServletContext()
				.getAttribute(Resourcer.getString("serlvet.context.dao.statements"));
		PierDao pierDao = (PierDao) request.getServletContext()
				.getAttribute(Resourcer.getString("serlvet.context.dao.piers"));
		User user = (User) request.getSession().getAttribute(Resourcer.getString("servlet.session.attributes.user"));

		String productFormsAsJson = request.getParameter("jsonProducts");
		if (productFormsAsJson == null) {
			return new CommandResultResponseSendRedirect(Resourcer.getString("uri.show.mainPage.captain"));
		}
		List<ProductForm> productForms = JsonToProductFormsDeserializator
				.deserializeJsonToProductForms(productFormsAsJson);

		UnloadProductsLogic logic = new UnloadProductsLogic(productDao, statementDao, pierDao);
		try {
			logic.unloadProducts(productForms, user.getId());
		} catch (NotEnoughPierCapacityException e) {
			request.getServletContext().setAttribute(Resourcer.getString("servlet.requests.attributes.errorMessage"),
					e.getMessage());
		}

		return new CommandResultResponseSendRedirect(Resourcer.getString("uri.show.mainPage.captain"));
	}

}
