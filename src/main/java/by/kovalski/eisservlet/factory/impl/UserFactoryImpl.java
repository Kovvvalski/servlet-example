package by.kovalski.eisservlet.factory.impl;

import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.factory.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserFactoryImpl implements UserFactory {

  private static final UserFactoryImpl instance = new UserFactoryImpl();

  private UserFactoryImpl(){

  }

  public static UserFactoryImpl getInstance(){
    return instance;
  }

  @Override
  public User createUserFromResultSet(ResultSet resultSet) throws SQLException {
    long id = resultSet.getLong("id");
    String login = resultSet.getString("login");
    String password = resultSet.getString("password");
    String firstName = resultSet.getString("first_name");
    String lastName = resultSet.getString("last_name");
    String email = resultSet.getString("email");
    return new User(id, login, password, firstName, lastName, email);

  }

  @Override
  public Set<User> createSetOfUsersFromResultSet(ResultSet resultSet)throws SQLException {
    Set<User> users = new HashSet<>();
    while (resultSet.next()){
      User user = createUserFromResultSet(resultSet);
      users.add(user);
    }
    return users;
  }
}
