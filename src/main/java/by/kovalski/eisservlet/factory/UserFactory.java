package by.kovalski.eisservlet.factory;

import by.kovalski.eisservlet.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface UserFactory {
  User createUserFromResultSet(ResultSet resultSet)throws SQLException;
  Set<User> createSetOfUsersFromResultSet(ResultSet resultSet)throws SQLException;
}
