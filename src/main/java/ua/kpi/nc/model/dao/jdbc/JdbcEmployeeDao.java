package ua.kpi.nc.model.dao.jdbc;

import ua.kpi.nc.model.dao.ConnectionPool;
import ua.kpi.nc.model.dao.EmployeeDao;
import ua.kpi.nc.model.entity.Employee;
import ua.kpi.nc.model.entity.Salgrade;
import ua.kpi.nc.model.exceptions.DaoException;
import ua.kpi.nc.model.exceptions.ServerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcEmployeeDao implements EmployeeDao {

    private static int CACHE_SIZE = 2;
    private static String GET_EMPLOYEE_BY_EMPNO = "";

    private static String UPDATE_EMPLOYEE = "";

    private static String DELETE_EMPLOYEE = "DELETE ";

    private static String CREATE_EMPLOYEE = "";


    private boolean connectionShouldBeClosed;

    LRUCache <Long, Employee> cache = new LRUCache<>(CACHE_SIZE);


    public JdbcEmployeeDao() {
        connectionShouldBeClosed = false;
    }

    public JdbcEmployeeDao(boolean connectionShouldBeClosed) {
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }


    @Override
    public Optional<Employee> getById(Long id) {
        return null;
    }

    @Override
    public void create(Employee t) {

    }

    @Override
    public void update(Employee t) {
        try (PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(UPDATE_EMPLOYEE)) {

            query.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void delete(Long id) {

    }



}
