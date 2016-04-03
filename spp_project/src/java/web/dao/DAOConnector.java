package java.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnector {
    private static Connection instance;
    private static final String driverName = "com.mysql.jdbc.Driver";
    private static boolean isCreated = false;

    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String nameDB = "root";
    private static final String passwordDB = "root";

    private DAOConnector() {}

    public static Connection getInstance() {
        try {
            Class.forName(driverName);
            instance = DriverManager.getConnection(URL, nameDB, passwordDB);
            isCreated = true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Class: " + driverName + " is not found! - " + ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return instance;
    }

    public static void closeConnection() {
        if (isCreated)
            try {
                instance.close();
                isCreated = false;
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        else
            System.out.println("Connection hasn't been created.");
    }
}
