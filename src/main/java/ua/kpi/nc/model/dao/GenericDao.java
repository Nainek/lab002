package ua.kpi.nc.model.dao;

import ua.kpi.nc.model.exceptions.DaoException;

import java.util.Optional;

/**
 *
 * @author Sikach
 *
 * Generic interface that have to be implemented by dao implementations
 * for realization CRUD operations
 *
 *
 * @param <T>
 *            object that dao work with
 * @param <K>
 *            object key
 */
public interface GenericDao<T, K> {

    Optional<T> getById(K id) throws DaoException;

    void create(T t) throws DaoException;

    void update(T t) throws DaoException;

    void delete(K id) throws DaoException;

}