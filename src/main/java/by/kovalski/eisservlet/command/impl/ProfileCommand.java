package by.kovalski.eisservlet.command.impl;

import by.kovalski.eisservlet.command.Command;
import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.exception.CommandException;
import by.kovalski.eisservlet.exception.ServiceException;
import by.kovalski.eisservlet.service.UserService;
import by.kovalski.eisservlet.service.impl.UserServiceImpl;
import by.kovalski.eisservlet.util.Pages;
import by.kovalski.eisservlet.util.RequestAndSessionData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ProfileCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    HttpSession session = request.getSession();
    String login = (String) session.getAttribute(RequestAndSessionData.LOGIN);
    UserService userService = UserServiceImpl.getInstance();
    String page;
    try {
      User user = userService.findUserByLogin(login);
      request.setAttribute(RequestAndSessionData.ID, user.getId());
      request.setAttribute(RequestAndSessionData.FIRST_NAME, user.getFirstName());
      request.setAttribute(RequestAndSessionData.LAST_NAME, user.getLastName());
      request.setAttribute(RequestAndSessionData.LOGIN, user.getLogin());
      request.setAttribute(RequestAndSessionData.EMAIL, user.getEmail());
      page = Pages.PROFILE;
    } catch (ServiceException e) {
      throw new CommandException(e);
    }
    return page;
  }
}
