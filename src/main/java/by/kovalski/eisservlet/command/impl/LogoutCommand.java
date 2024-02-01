package by.kovalski.eisservlet.command.impl;

import by.kovalski.eisservlet.command.Command;
import by.kovalski.eisservlet.util.Pages;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) {
    request.getSession().invalidate();
    return Pages.LOGIN;
  }
}
