package by.kovalski.eisservlet.dao;

import by.kovalski.eisservlet.exception.DaoException;
import by.kovalski.eisservlet.entity.Entity;

import java.util.Set;

public interface BaseDao<T extends Entity> {
  boolean create(T t) throws DaoException;

  T findById(long id) throws DaoException;

  Set<T> findAll() throws DaoException;

  boolean update(T t) throws DaoException;

  boolean delete(T t) throws DaoException;

}
