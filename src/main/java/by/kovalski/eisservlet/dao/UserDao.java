package by.kovalski.eisservlet.dao;

import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.exception.DaoException;

import java.util.List;

public interface UserDao extends BaseDao<User> {
  boolean authenticate(String login, String password) throws DaoException;
  User findByLogin(String login) throws DaoException;
}
