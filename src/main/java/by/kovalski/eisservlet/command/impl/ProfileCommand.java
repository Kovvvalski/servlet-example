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

public class ProfileCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    String login = (String) request.getAttribute(RequestParameters.LOGIN);
    UserService userService = UserServiceImpl.getInstance();
    String page;
    try {
      User user = userService.findUserByLogin(login);
      request.setAttribute(RequestParameters.ID, user.getId());
      request.setAttribute(RequestParameters.FIRST_NAME, user.getFirstName());
      request.setAttribute(RequestParameters.LAST_NAME, user.getLastName());
      request.setAttribute(RequestParameters.LOGIN, user.getLogin());
      request.setAttribute(RequestParameters.EMAIL, user.getEmail());
      page = Pages.PROFILE;
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }
}
