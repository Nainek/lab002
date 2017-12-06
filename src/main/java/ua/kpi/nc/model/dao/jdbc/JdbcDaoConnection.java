package ua.kpi.nc.model.dao.jdbc;

import ua.kpi.nc.model.dao.DaoConnection;
import ua.kpi.nc.model.exceptions.ServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDaoConnection implements DaoConnection {

    private Connection connection;

    /** check if exists an active (uncommitted) transaction */
    private boolean inTransaction = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
            inTransaction = true;
        } catch (SQLException e) {
            throw new ServerException("JdbcDaoConnection begin error", e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            throw new ServerException("JdbcDaoConnection commit error", e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
        } catch (SQLException e) {
            throw new ServerException("JdbcDaoConnection rollback error", e);
        }
    }

    /*
        If required - delete this method
     */

    @Override
    public void close() {
        if (inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServerException("JdbcDaoConnection close error", e);
        }
    }


}
