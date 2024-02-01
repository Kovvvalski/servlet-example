package by.kovalski.eisservlet.command.impl;

import by.kovalski.eisservlet.command.Command;
import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.exception.CommandException;
import by.kovalski.eisservlet.exception.ServiceException;
import by.kovalski.eisservlet.service.UserService;
import by.kovalski.eisservlet.service.impl.UserServiceImpl;
import by.kovalski.eisservlet.util.Pages;
import by.kovalski.eisservlet.util.RequestParameters;
import jakarta.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String login = request.getParameter(RequestParameters.LOGIN);
    String password = request.getParameter(RequestParameters.PASSWORD);
    String firstName = request.getParameter(RequestParameters.FIRST_NAME);
    String lastName = request.getParameter(RequestParameters.LAST_NAME);
    String email = request.getParameter(RequestParameters.EMAIL);
    UserService userService = UserServiceImpl.getInstance();
    User user = new User(login, password, firstName, lastName, email);
    String page;
    try {
      if (!userService.addUser(user)) {
        request.setAttribute(RequestParameters.REGISTRATION_MESSAGE, "Not correct login or password, or user with this login already exists");
        page = Pages.REGISTRATION;
      } else {
        request.setAttribute(RequestParameters.LOGIN_MESSAGE, "Registration was completed, please Log In");
        page = Pages.LOGIN;
      }
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }
}
