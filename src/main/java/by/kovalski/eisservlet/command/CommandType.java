package by.kovalski.eisservlet.command;

import by.kovalski.eisservlet.command.impl.*;

public enum CommandType {
  LOGIN(new LoginCommand()),
  LOGOUT(new LogoutCommand()),
  DEFAULT(new DefaultCommand()),
  REGISTRATION(new RegistrationCommand()),
  TO_REGISTRATION(new ToRegFormCommand()),
  PROFILE(new ProfileCommand());
  final Command command;

  CommandType(Command command) {
    this.command = command;
  }

  public static Command defineCommand(String commandStr) {
    Command out = null;
    try {
      out = CommandType.valueOf(commandStr.toUpperCase()).command;
    } catch (IllegalArgumentException e) {
      out = DEFAULT.command;
    }
    return out;
  }
}
