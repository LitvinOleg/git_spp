package classes.web.model.dao.connection;

import classes.web.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC connection class
 */
public class DaoConnector {
    private static volatile Connection instance;
    private static final String driverName = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/project_db";
    private static final String nameDB = "root";
    private static final String passwordDB = "root";

    private DaoConnector() {}

    public static Connection getInstance() throws DaoException {
        try {
            Class.forName(driverName);
            instance = DriverManager.getConnection(URL, nameDB, passwordDB);
        } catch (ClassNotFoundException ex) {
            throw new DaoException("Class not found!");
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return instance;
    }
}
