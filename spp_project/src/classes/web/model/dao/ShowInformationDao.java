package classes.web.model.dao;

import classes.web.entity.Load;
import classes.web.model.dao.connection.DaoConnector;
import classes.web.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Олег on 07.04.2016.
 */
public class ShowInformationDao {
    private static final String SELECT_NON_EXISTING_ELEMENT_STATEMENT = "select * from %s where %s not in (select %s from %s);";
    private static Connection connection;

    /**
     * Selects all free loads from DB
     * @return set of free loads
     * @throws DaoException
     */
    public static List<Load> selectAllFreeLoads() throws DaoException {
        final String loadTable = "loads";
        final String orderLoadTable = "order_load";
        final String columnName = "load_id";
        List<Load> loadList = new ArrayList<>();

        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(String.format(SELECT_NON_EXISTING_ELEMENT_STATEMENT, loadTable, columnName, columnName, orderLoadTable));

            while (result.next()) {
                Load load = new Load();
                load.setLoadID(result.getInt(1));
                load.setWeight(result.getInt(2));
                load.setCostOfDelivery(result.getInt(3));
                load.setLoadType(result.getInt(4));
                load.setLoadDescription(result.getString(5));
                loadList.add(load);
            }

            connection.close();
            statement.close();
        } catch (SQLException ex) {
            throw new DaoException("JDBC error!");
        }
        return loadList;
    }
}
