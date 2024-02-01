package by.kovalski.eisservlet.command.impl;

import by.kovalski.eisservlet.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import by.kovalski.eisservlet.util.Pages;

public class DefaultCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) {
    return Pages.LOGIN;
  }
}
