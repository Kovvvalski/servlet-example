package by.kovalski.eisservlet.dao.impl;

import by.kovalski.eisservlet.dao.UserDao;
import by.kovalski.eisservlet.dbconnection.ConnectionPool;
import by.kovalski.eisservlet.entity.User;
import by.kovalski.eisservlet.exception.DaoException;
import by.kovalski.eisservlet.factory.UserFactory;
import by.kovalski.eisservlet.factory.impl.UserFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Set;

public class UserDaoImpl implements UserDao {
  private static final Logger logger = LogManager.getLogger();

  private static final String AUTHENTICATE_SQL = "SELECT password FROM users WHERE login = ?";
  private static final String CREATE_SQL = "INSERT INTO users (first_name, last_name, login, password, email) VALUES (?,?,?,?,?)";
  private static final String FIND_BY_ID_SQL = "SELECT id, first_name, last_name, login, password, email FROM users WHERE id = ?";
  private static final String FIND_BY_LOGIN_SQL = "SELECT id, first_name, last_name, login, password, email FROM users WHERE login = ?";
  private static final String FIND_ALL_SQL = "SELECT id, first_name, last_name, login, password, email FROM users";
  private static final String UPDATE_SQL = "UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?, email = ? WHERE id = ?";
  private static final String DELETE_BY_ID_SQL = "DELETE FROM users WHERE id = ?";


  private static UserDaoImpl instance;

  private final UserFactory userFactory;

  private UserDaoImpl() {
    userFactory = UserFactoryImpl.getInstance();
  }

  public static UserDaoImpl getInstance() {
    if (instance == null) {
      instance = new UserDaoImpl();
    }
    return instance;
  }


  @Override
  public boolean create(User user) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();

    try (PreparedStatement authStatement = connection.prepareStatement(AUTHENTICATE_SQL);
         PreparedStatement createStatement = connection.prepareStatement(CREATE_SQL)) {

      authStatement.setString(1, user.getLogin());
      if (authStatement.executeQuery().next()) {
        return false;
      }

      createStatement.setString(1, user.getFirstName());
      createStatement.setString(2, user.getLastName());
      createStatement.setString(3, user.getLogin());
      createStatement.setString(4, user.getPassword());
      createStatement.setString(5, user.getEmail());
      createStatement.executeUpdate();

    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);

    } finally {
      connectionPool.releaseConnection(connection);
    }
    return true;
  }

  @Override
  public User findById(long id) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    ResultSet resultSet = null;
    User user = null;
    try (PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
      statement.setLong(1, id);
      resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        throw new DaoException("No such user");
      }
      user = userFactory.createUserFromResultSet(resultSet);
    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);
    } finally {
      connectionPool.releaseConnection(connection);
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          logger.error("Can not close RS", e);
        }
      }
    }
    return user;
  }

  @Override
  public User findByLogin(String login) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    ResultSet resultSet = null;
    User user = null;
    try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_SQL)) {
      statement.setString(1, login);
      resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        throw new DaoException("No such user");
      }
      user = userFactory.createUserFromResultSet(resultSet);
    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);
    } finally {
      connectionPool.releaseConnection(connection);
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          logger.error("Can not close RS", e);
        }
      }
    }
    return user;
  }

  @Override
  public Set<User> findAll() throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    ResultSet resultSet = null;
    Set<User> users = null;
    try (PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {
      resultSet = statement.executeQuery();
      users = userFactory.createSetOfUsersFromResultSet(resultSet);
    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);
    } finally {
      connectionPool.releaseConnection(connection);
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          logger.error("Can not close RS", e);
        }
      }
    }
    return users;
  }

  @Override
  public boolean update(User user) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    boolean flag = true;
    try (PreparedStatement updateStatement = connection.prepareStatement(UPDATE_SQL)) {
      updateStatement.setString(1, user.getFirstName());
      updateStatement.setString(2, user.getLastName());
      updateStatement.setString(3, user.getLogin());
      updateStatement.setString(4, user.getPassword());
      updateStatement.setString(5, user.getEmail());
      updateStatement.setLong(6, user.getId());
      int res = updateStatement.executeUpdate();
      if (res != 1) {
        flag = false;
      }
    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);
    } finally {
      connectionPool.releaseConnection(connection);
    }
    return flag;
  }

  @Override
  public boolean delete(User user) throws DaoException {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    boolean flag = true;
    try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
      statement.setLong(1, user.getId());
      int res = statement.executeUpdate();
      if (res != 1) {
        flag = false;
      }
    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);
    } finally {
      connectionPool.releaseConnection(connection);
    }
    return flag;
  }

  @Override
  public boolean authenticate(String login, String password) throws DaoException {
    boolean out = true;
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    Connection connection = connectionPool.getConnection();
    ResultSet resultSet = null;
    try (PreparedStatement statement = connection.prepareStatement(AUTHENTICATE_SQL)) {
      statement.setString(1, login);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        String dbPassword = resultSet.getString(1);
        if (!dbPassword.equals(password)) {
          out = false;
        }
      } else {
        out = false;
      }
    } catch (SQLException e) {
      logger.error("Error during working with DB", e);
      throw new DaoException("Error during working with DB", e);
    } finally {
      connectionPool.releaseConnection(connection);
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
          logger.error("Can not close result set", e);
        }
      }
    }
    return out;
  }
}
