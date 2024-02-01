package by.kovalski.eisservlet.command.impl;

import by.kovalski.eisservlet.command.Command;
import by.kovalski.eisservlet.exception.CommandException;
import by.kovalski.eisservlet.util.Pages;
import jakarta.servlet.http.HttpServletRequest;

public class ToRegFormCommand implements Command {
  @Override
  public String execute(HttpServletRequest request) throws CommandException {
    return Pages.REGISTRATION;
  }
}
