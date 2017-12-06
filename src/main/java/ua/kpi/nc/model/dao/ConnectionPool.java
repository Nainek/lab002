package ua.kpi.nc.model.dao;


import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * This class tuning BasicDataSource
 * and returns database connection
 */
public class ConnectionPool {

    private static final ResourceBundle PROPERTY = ResourceBundle.getBundle("database.ConnectionPool");

    private BasicDataSource basicDataSource = new BasicDataSource();

    private static volatile ConnectionPool connectionPool;

    private ConnectionPool() {
        basicDataSource.setDriverClassName(PROPERTY.getString("driver.class.name"));
        basicDataSource.setUrl(PROPERTY.getString("url"));
        basicDataSource.setUsername(PROPERTY.getString("user"));
        basicDataSource.setPassword(PROPERTY.getString("password"));
        basicDataSource.setInitialSize(Integer.parseInt(PROPERTY.getString("connection.pool.size")));
    }

    public static ConnectionPool getInstance(){
        if(connectionPool == null){
            synchronized (ConnectionPool.class){
                if(connectionPool == null){
                    connectionPool = new ConnectionPool();
                }
            }
        }
        return connectionPool;
    }

    /**
     * @return database connection
     */
    public Connection getConnection() throws SQLException {
            return basicDataSource.getConnection();
    }

}



