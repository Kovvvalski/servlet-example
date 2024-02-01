package by.kovalski.eisservlet.command.impl;

import by.kovalski.eisservlet.command.Command;
import by.kovalski.eisservlet.exception.CommandException;
import by.kovalski.eisservlet.exception.ServiceException;
import by.kovalski.eisservlet.service.UserService;
import by.kovalski.eisservlet.service.impl.UserServiceImpl;
import by.kovalski.eisservlet.util.Pages;
import by.kovalski.eisservlet.util.RequestParameters;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class LoginCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String login = request.getParameter(RequestParameters.LOGIN);
    String password = request.getParameter(RequestParameters.PASSWORD);
    UserService userService = UserServiceImpl.getInstance();
    String page;
    HttpSession session = request.getSession();
    try {
      if (userService.authenticate(login, password)) {
        request.setAttribute(RequestParameters.LOGIN, login);
        session.setAttribute("user_name", login);
        page = Pages.MAIN;
      } else {
        request.setAttribute(RequestParameters.LOGIN_MESSAGE, "incorrect login or password");
        page = Pages.LOGIN;
      }
      session.setAttribute("current_page", page);
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }
}
