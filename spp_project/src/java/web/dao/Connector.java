package java.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private Connection connection;
    private final String driverName = "com.mysql.jdbc.Driver";
    private boolean isCreated = false;

    private final String URL = "jdbc:mysql://localhost:3306";
    private final String nameDB = "root";
    private final String passwordDB = "root";

    private Connector() {}

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(URL, nameDB, passwordDB);
            isCreated = true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Class: " + driverName + " is not found! - " + ex);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return connection;
    }

    public void close() {
        if (isCreated)
            try {
                connection.close();
                isCreated = false;
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        else
            System.out.println("Connection hasn't been created.");
    }
}
