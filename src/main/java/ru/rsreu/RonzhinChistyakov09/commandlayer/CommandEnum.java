package ru.rsreu.RonzhinChistyakov09.commandlayer;

import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.LoginCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.LogoutCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.RoutingUserCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.ShowLoginPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.ShowMainNoLoginPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.ShowMainAdminPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create.CreatePierCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create.CreateShipCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create.CreateUserCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create.ShowCreatePierPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create.ShowCreateShipPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.create.ShowCreateUserPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.delete.DeletePierCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.delete.DeleteShipCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.delete.DeleteUserCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.edit.EditUserCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.admin.edit.ShowEditUserPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.FinishStatementCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.ShowMainCaptainPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.create.CancelStatementCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.create.CreateStatementCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.load.LoadProductsCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.load.ShowLoadPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.unload.ShowUnloadPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.captain.unload.UnloadProductsCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.dispatcher.ApplyStatementCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.dispatcher.ProcessStatementCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.dispatcher.RejectStatementCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.dispatcher.ShowMainDispatcherPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.dispatcher.ShowProcessStatementPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.moderator.BlockUserCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.moderator.ShowMainModeratorPageCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.commands.moderator.UnblockUserCommand;
import ru.rsreu.RonzhinChistyakov09.commandlayer.interfaces.ActionCommand;

public enum CommandEnum {
	SHOW_MAIN_NO_LOGIN_PAGE {
		{
			this.command = new ShowMainNoLoginPageCommand();
		}
	},

	SHOW_LOGIN_PAGE {
		{
			this.command = new ShowLoginPageCommand();
		}
	},
	// admin show commands
	SHOW_MAIN_ADMIN_PAGE {
		{
			this.command = new ShowMainAdminPageCommand();
		}
	},
	SHOW_CREATE_USER_PAGE {
		{
			this.command = new ShowCreateUserPageCommand();
		}
	},
	SHOW_EDIT_USER_PAGE {
		{
			this.command = new ShowEditUserPageCommand();
		}
	},
	SHOW_CREATE_PIER_PAGE {
		{
			this.command = new ShowCreatePierPageCommand();
		}
	},
	SHOW_CREATE_SHIP_PAGE {
		{
			this.command = new ShowCreateShipPageCommand();
		}
	},
	// end admin show commands

	// admin action commands
	CREATE_USER {
		{
			this.command = new CreateUserCommand();
		}
	},
	EDIT_USER {
		{
			this.command = new EditUserCommand();
		}
	},
	CREATE_PIER {
		{
			this.command = new CreatePierCommand();
		}
	},
	DELETE_USER {
		{
			this.command = new DeleteUserCommand();
		}
	},
	DELETE_PIER {
		{
			this.command = new DeletePierCommand();
		}
	},
	CREATE_SHIP {
		{
			this.command = new CreateShipCommand();
		}
	},
	DELETE_SHIP {
		{
			this.command = new DeleteShipCommand();
		}
	},
	// end admin action commands

	// moderator show command
	SHOW_MAIN_MODERATOR_PAGE {
		{
			this.command = new ShowMainModeratorPageCommand();
		}
	},
	// moderator action commands
	BLOCK_USER {
		{
			this.command = new BlockUserCommand();
		}
	},
	UNBLOCK_USER {
		{
			this.command = new UnblockUserCommand();
		}
	},
	// dispatcher show commands
	SHOW_MAIN_DISPATCHER_PAGE {
		{
			this.command = new ShowMainDispatcherPageCommand();
		}
	},
	PROCESS_STATEMENT {
		{
			this.command = new ProcessStatementCommand();
		}
	},
	SHOW_PROCESS_STATEMENT_PAGE {
		{
			this.command = new ShowProcessStatementPageCommand();
		}
	},
	APPLY_STATEMENT {
		{
			this.command = new ApplyStatementCommand();
		}
	},
	REJECT_STATEMENT {
		{
			this.command = new RejectStatementCommand();
		}
	},

	// captain show commands
	SHOW_MAIN_CAPTAIN_PAGE {
		{
			this.command = new ShowMainCaptainPageCommand();
		}
	},
	SHOW_UNLOAD_PAGE {
		{
			this.command = new ShowUnloadPageCommand();
		}
	},
	SHOW_LOAD_PAGE {
		{
			this.command = new ShowLoadPageCommand();
		}
	},
	// end captain show commands

	// captain action commands
	CREATE_STATEMENT {
		{
			this.command = new CreateStatementCommand();
		}
	},
	CANCEL_STATEMENT {
		{
			this.command = new CancelStatementCommand();
		}
	},
	FINISH_STATEMENT{
		{
			this.command = new FinishStatementCommand();
		}
	},
	UNLOAD_PRODUCTS {
		{
			this.command = new UnloadProductsCommand();
		}
	},
	LOAD_PRODUCTS {
		{
			this.command = new LoadProductsCommand();
		}
	},
	// end captain action commands

	// system commands
	ROUTING_USER {
		{
			this.command = new RoutingUserCommand();
		}
	},
	LOGIN {
		{
			this.command = new LoginCommand();
		}
	},
	LOGOUT {
		{
			this.command = new LogoutCommand();
		}
	};

	ActionCommand command;

	public ActionCommand getCurrentCommand() {
		return command;
	}
}
