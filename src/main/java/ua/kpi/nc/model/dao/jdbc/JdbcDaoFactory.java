package ua.kpi.nc.model.dao.jdbc;

import ua.kpi.nc.model.dao.*;
import ua.kpi.nc.model.exceptions.ServerException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class JdbcDaoFactory extends DaoFactory {

    private DataSource dataSource;

    /**
     * Get DataSource implementation from Initial Context by means of JNDI mechanism
     */
    public JdbcDaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/HRDB");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new ServerException("Can't load pool connection from Initial Context", e);
        }
    }



    @Override
    public EmployeeDao createEmployeeDao() {
        return new JdbcEmployeeDao();
    }


    @Override
    public DepartmentDao createDepartmentDao() {
        return new JdbcDepartmentDao();
    }

    @Override
    public DepartmentDao createDepartmentDao(boolean b) {
        return new JdbcDepartmentDao(b);
    }

    @Override
    public SalgradeDao createSalgradeDao() {
        return new JdbcSalgradeDao();
    }

}
