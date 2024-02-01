package by.kovalski.eisservlet.service.impl;

import by.kovalski.eisservlet.dao.UserDao;
import by.kovalski.eisservlet.dao.impl.UserDaoImpl;
import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.exception.DaoException;
import by.kovalski.eisservlet.exception.ServiceException;
import by.kovalski.eisservlet.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class UserServiceImpl implements UserService {
  private static final Logger logger = LogManager.getLogger();

  private static final UserServiceImpl instance = new UserServiceImpl();

  private final UserDao userDao;

  private UserServiceImpl() {
    userDao = UserDaoImpl.getInstance();
  }

  public static UserServiceImpl getInstance() {
    return instance;
  }

  @Override
  public boolean authenticate(String login, String password) throws ServiceException {
    if (login == null || password == null) {
      logger.error("Null value of login or password");
      return false;
    }
    if (login.isEmpty() || login.length() > 20 || password.isEmpty()) {
      return false;
    }
    boolean match = false;
    try {
      match = userDao.authenticate(login, DigestUtils.md5Hex(password));
    } catch (DaoException e) {
      logger.error(e);
      throw new ServiceException(e);
    }
    return match;
  }

  @Override
  public boolean addUser(User user) throws ServiceException {
    String login = user.getLogin();
    String password = user.getPassword();
    if (login == null || password == null) {
      logger.error("Null value of login or password");
      return false;
    }
    if (login.isEmpty() || login.length() > 20 || password.isEmpty()) {
      return false;
    }
    try {
      String encrypted = DigestUtils.md5Hex(user.getPassword());
      user.setPassword(encrypted);
      return userDao.create(user);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
  }

  @Override
  public User findUserByLogin(String login) throws ServiceException {
    if (login == null || login.isEmpty()) {
      throw new ServiceException("Not correct value of login");
    }
    User user = null;
    try {
      user = userDao.findByLogin(login);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return user;
  }

  @Override
  public User findUserById(Long id) throws ServiceException {
    if (id == null || id < 1) {
      throw new ServiceException("Not correct value of id");
    }
    User user = null;
    try {
      user = userDao.findById(id);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return user;
  }

  @Override
  public boolean delete(User user) throws ServiceException {
    boolean out;
    try {
      out = userDao.delete(user);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return out;
  }

  @Override
  public boolean update(User user) throws ServiceException {
    boolean out;
    try {
      out = userDao.update(user);
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return out;
  }

  @Override
  public Set<User> findAll() throws ServiceException {
    Set<User> out = null;
    try {
      out = userDao.findAll();
    } catch (DaoException e) {
      throw new ServiceException(e);
    }
    return out;
  }
}
