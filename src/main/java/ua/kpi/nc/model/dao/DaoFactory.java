package ua.kpi.nc.model.dao;


import ua.kpi.nc.model.exceptions.ServerException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;



/**
 * @author Sikach
 *
 * Abstract class that represents abstract dao factory that can construct
 * various types of concrete DAO factories that support different types of
 * persistent storage access implementation.
 * <p>
 *
 *
 * Each concrete dao factory that support particular kind of persistent storage access
 * implementation has to extend this abstract class
 *

 *
 */
public abstract class DaoFactory {

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory daoFactory;


    public abstract EmployeeDao createEmployeeDao();

    public abstract EmployeeDao createEmployeeDao (boolean b);

    public abstract DepartmentDao createDepartmentDao();

    public abstract DepartmentDao createDepartmentDao(boolean b);

    public abstract SalgradeDao createSalgradeDao();

    public abstract SalgradeDao createSalgradeDao (boolean b);


    /**
     * Method that returns concrete dao factory that support particular kind of
     * persistent storage access implementation(JDBC implementation). This factory implementation is loaded
     * from db.properties file
     *
     * @return DaoFactory concrete dao factory implementation
     */
    public static DaoFactory getDaoFactory(){
        if(daoFactory == null){
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProperties = new Properties();
                dbProperties.load(inputStream);
                String factoryClass = dbProperties.getProperty(DB_FACTORY_CLASS);
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();

            } catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new ServerException("Can't load inputStream db config file to properties object", e);
            }
        }
        return daoFactory;
        }

}


