package ua.kpi.nc.model.dao.jdbc;

import ua.kpi.nc.model.dao.ConnectionPool;
import ua.kpi.nc.model.dao.SalgradeDao;
import ua.kpi.nc.model.entity.Salgrade;
import ua.kpi.nc.model.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class JdbcSalgradeDao implements SalgradeDao {

    private static String SALGRADE_TABLE_NAME = "SALGRADE";
    private static String GRADE_ATTRIBUTE = "GRADE";
    private static String LOSAL_ATTRIBUTE = "LOSAL";
    private static String HISAL_ATTRIBUTE = "HISAL";
    private static int CACHE_SIZE = 2;

    private static String GET_TYPE_ID = "SELECT object_type_id FROM object_types WHERE name = ? ";

    private static String GET_SALGRADE_BY_SALGRADE =
            "SELECT attributes.name, parameters.value " +
            "FROM Object_types " +
            "INNER JOIN ATTRIBUTES ON Object_types.object_type_id = ATTRIBUTES.OBJECT_TYPE_ID " +
            "INNER JOIN Parameters ON ATTRIBUTES.ATTRIBUTE_ID = Parameters.ATTRIBUTE_ID " +
            "INNER JOIN Object_inst ON Parameters.OBJECT_INST_ID = Object_inst.OBJECT_INST_ID " +
            "WHERE Object_types.name = ? " +
            "AND  Object_inst.NAME = ? " +
            "ORDER BY Object_inst.OBJECT_INST_ID, ATTRIBUTES.NAME";

    private static String UPDATE_SALGRADE = "UPDATE parameters " +
            "SET ( value = ? ) " +
            "AND attribute_id = (SELECT attribute_id FROM attributes WHERE name = ? AND object_type_id = ?)";

    private static String DELETE_SALGRADE = "DELETE FROM object_inst " +
            "WHERE name = ? " +
            "AND obj_inst_id = SALGRADE_ID "; /// change to object_type_id

    private static String INSERT_INST_SALGRADE =
            "INSERT INTO object_inst (OBJECT_INST_ID, OBJECT_TYPE_ID, NAME) " +
            "VALUES (object_inst_seq.NEXTVAL, ?, ?)";

    private static String INSERT_PARAM_SALGRADE = "INSERT INTO parameters(object_inst_id, attribute_id, value) " +
            "VALUES (?, (SELECT attribute_id FROM attributes WHERE name = ? AND object_type_id = ?), ?))";


    private boolean connectionShouldBeClosed;

    Map <Long, Salgrade> cache = new LRUCache<>(CACHE_SIZE);

    public JdbcSalgradeDao() {
        connectionShouldBeClosed = true;
    }

    public JdbcSalgradeDao(boolean connectionShouldBeClosed) {
        this.connectionShouldBeClosed = connectionShouldBeClosed;
    }


    @Override
    public Optional<Salgrade> getById(Long id) throws DaoException {
        Optional<Salgrade> salgrade;

        salgrade = Optional.of(getByIdFromCache(id));
        if(!salgrade.isPresent()){
            salgrade = Optional.of(getByIdFromDB(id));
        }
        if(!salgrade.isPresent()){
            putIntoCache(id, salgrade.get());
        }

        return salgrade;
    }

    private Salgrade getByIdFromCache(Long id) {
        return cache.get(id);
    }
    private void putIntoCache(Long id, Salgrade salgrade) {
        cache.put(id, salgrade);
    }

    private Salgrade getByIdFromDB(Long id) throws DaoException {
        try (PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(GET_SALGRADE_BY_SALGRADE)){
            query.setString(1, SALGRADE_TABLE_NAME);
            query.setString(2, id.toString());
            ResultSet rs = query.executeQuery();
            return extractSalgradeFromResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Record isn't received", e);
        }
    }

    private Salgrade extractSalgradeFromResultSet(ResultSet rs) throws SQLException {
        Map<String, String> record = new TreeMap<>();
        while (rs.next()){
            record.put(rs.getString("ATR_NAME"), rs.getString("ATR_VALUE"));
        }
        return new Salgrade.Builder()
                .setGrade(rs.getLong(GRADE_ATTRIBUTE))
                .setLosal(rs.getInt(LOSAL_ATTRIBUTE))
                .setHisal(rs.getInt(HISAL_ATTRIBUTE))
                .build();
    }

    @Override
    public void create(Salgrade t) throws DaoException {
        long object_type_id = getObjectTypeId();
        try (JdbcDaoConnection jdbcDaoConnection = new JdbcDaoConnection(ConnectionPool.getInstance().getConnection())){
            jdbcDaoConnection.begin();
            insertIntsance(t.getGrade(), object_type_id);
            insertParameters(t, object_type_id);
            jdbcDaoConnection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void insertIntsance(Long id, long object_type_id) throws DaoException {
        try (PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(INSERT_INST_SALGRADE)) {
            query.setString(1, String.valueOf(object_type_id)); // Change to long
            query.setLong(2, id); // change to String
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Record isn't received", e);
        }
    }

    private void insertParameters(Salgrade t, Long object_inst_id) throws DaoException {
        try (PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(INSERT_PARAM_SALGRADE)) {
            query.setLong(1, object_inst_id);
            query.setString(2, GRADE_ATTRIBUTE);
            query.setLong(3, getObjectTypeId());
            query.setString(4, t.getGrade().toString());
            query.addBatch();
            query.setString(2, HISAL_ATTRIBUTE);
            query.setString(4, t.getHisal().toString());
            query.addBatch();
            query.setString(2, LOSAL_ATTRIBUTE);
            query.setString(4, t.getLosal().toString());
            query.addBatch();
            query.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Record isn't received", e);
        }
    }

    @Override
    public void update(Salgrade t) throws DaoException {
        try (PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(UPDATE_SALGRADE)) {
            query.setString(1, t.getHisal().toString());
            query.setString(2, HISAL_ATTRIBUTE);
            query.setLong(3, getObjectTypeId());
            query.addBatch();
            query.setString(1, t.getLosal().toString());
            query.setString(2, LOSAL_ATTRIBUTE);
            query.addBatch();
            query.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Record isn't received", e);
        }
    }

    private Long getObjectTypeId() throws DaoException {
        long obj_type_id = 0;
        try(PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(GET_TYPE_ID)) {
            query.setString(1, SALGRADE_TABLE_NAME);
            ResultSet resultSet = query.executeQuery();
            while(resultSet.next()){
                obj_type_id = resultSet.getLong("OBJECT_TYPE_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Record isn't received", e);
        }
        return obj_type_id;
    }

    @Override
    public void delete(Long id) throws DaoException {
        try (PreparedStatement query = ConnectionPool.getInstance().getConnection().prepareStatement(DELETE_SALGRADE)) {
            query.setLong(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("JdbcBookDao delete SQL exception: ", e);
        }
    }


}
