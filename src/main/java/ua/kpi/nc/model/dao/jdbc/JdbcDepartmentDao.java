package ua.kpi.nc.model.dao.jdbc;

import ua.kpi.nc.model.dao.DepartmentDao;
import ua.kpi.nc.model.entity.Department;
import ua.kpi.nc.model.exceptions.ServerException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;

public class JdbcDepartmentDao implements DepartmentDao {

    private static int CACHE_SIZE = 2;

    private static String GET_TYPE_ID = "SELECT object_type_id FROM object_types WHERE name = ? ";

    private static String GET_DEPARTMENT_BY_DEPTNO =
            "SELECT attributes.name, parameters.value " +
                    "FROM Object_types " +
                    "INNER JOIN ATTRIBUTES ON Object_types.object_type_id = ATTRIBUTES.OBJECT_TYPE_ID " +
                    "INNER JOIN Parameters ON ATTRIBUTES.ATTRIBUTE_ID = Parameters.ATTRIBUTE_ID " +
                    "INNER JOIN Object_inst ON Parameters.OBJECT_INST_ID = Object_inst.OBJECT_INST_ID " +
                    "WHERE Object_types.name = ? " +
                    "AND  Object_inst.NAME = ? " +
                    "ORDER BY Object_inst.OBJECT_INST_ID, ATTRIBUTES.NAME";

    private static String UPDATE_DEPARTMENT = "UPDATE parameters " +
            "SET ( value = ? ) " +
            "AND attribute_id = (SELECT attribute_id FROM attributes WHERE name = ? AND object_type_id = ?)";

    private static String DELETE_DEPARTMENT = "DELETE FROM object_inst " +
            "WHERE name= ? " +
            "AND obj_inst_id = SALGRADE_ID "; /// change to object_type_id

    private static String CREATE_DEPARTMENT = "INSERT INTO object_inst (OBJECT_INST_ID, OBJECT_TYPE_ID, NAME) " +
            "VALUES (object_inst_seq.NEXTVAL, ?, ?)";

    private static String INSERT_PARAM_DEPARTMENT = "INSERT INTO parameters(object_inst_id, attribute_id, value) " +
            "VALUES (?, (SELECT attribute_id FROM attributes WHERE name = ? AND object_type_id = ?), ?))";

    private boolean connectionShouldBeClosed;


    Map<Long, Department> cache = new LRUCache<>(CACHE_SIZE);

    public JdbcDepartmentDao() {
        connectionShouldBeClosed = false;
    }

    public JdbcDepartmentDao(boolean connectionShouldBeClosed) {
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }



    @Override
    public Optional<Department> getById(Long id) {
        return null;
    }

    @Override
    public void create(Department t) {

    }

    @Override
    public void update(Department t) {

    }

    @Override
    public void delete(Long id) {

    }



}
