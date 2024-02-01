package by.kovalski.eisservlet.service;

import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.exception.ServiceException;

import java.util.Set;

public interface UserService {
  boolean authenticate(String login, String password) throws ServiceException;

  boolean addUser(User user) throws ServiceException;

  User findUserByLogin(String login) throws ServiceException;

  User findUserById(Long id) throws ServiceException;

  boolean delete(User user) throws ServiceException;

  boolean update(User user) throws ServiceException;

  Set<User> findAll() throws ServiceException;
}
