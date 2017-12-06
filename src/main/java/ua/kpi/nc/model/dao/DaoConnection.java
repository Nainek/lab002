package ua.kpi.nc.model.dao;

/**
 *
 * @author Sikach
 *
 * Interface that have to be implemented by custom Connection wrapper
 *
 */
public interface DaoConnection extends AutoCloseable {

    void begin();

    void commit();

    void rollback();

    void close();

}