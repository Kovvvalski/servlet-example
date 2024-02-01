package by.kovalski.eisservlet.command;

import by.kovalski.eisservlet.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface Command {
  String execute(HttpServletRequest request) throws CommandException;
}
