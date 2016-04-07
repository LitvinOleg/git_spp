package classes.web.model.dao;

import classes.web.model.dao.connection.DaoConnector;
import classes.web.entity.user.Client;
import classes.web.entity.user.User;
import classes.web.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Олег on 06.04.2016.
 */
public class ModificationDao {
    private static Connection connection;

    private final static String ADD_USER_STATEMENT = "INSERT INTO user(user_login, user_name, user_surname, user_password, user_type) VALUES ('%s', '%s', '%s', '%s', %d);";
    private final static String ADD_CLIENT_STATEMENT = "INSERT INTO client(client_login, order_id) VALUES ('%s', %d);";
    private final static String ADD_ORDER_STATEMENT = "INSERT INTO orders(order_id) VALUE (%d);";
    private final static String SELECT_USER_LOGIN_STATEMENT = "SELECT user_login FROM user WHERE user_login='%s'";

    /**
     * Adds new user to DB
     * @param user that we want to add
     * @return true if user was added
     * @throws DaoException
     */
    public static boolean addNewUser(User user) throws DaoException {
        boolean result = false;
        String login = user.getLogin();
        String name = user.getName();
        String surname = user.getSurname();
        String password = user.getPassword();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet usersLogin = statement.executeQuery(String.format(SELECT_USER_LOGIN_STATEMENT, login));
            if (usersLogin.next()) throw new IllegalArgumentException();
            switch (user.getUserType()) {
                case CLIENT: {
                    Client client = (Client) user;
                    int clientOrderID = client.getOrder().getOrderID();
                    statement.executeUpdate(String.format(ADD_USER_STATEMENT, login, name, surname, password, client.getUserType().getEnumValue()));
                    statement.executeUpdate(String.format(ADD_CLIENT_STATEMENT, login, clientOrderID));
                    statement.executeUpdate(String.format(ADD_ORDER_STATEMENT, clientOrderID));
                } break;
                case DISPATCHER: {
                } break;
                case ADMIN: {
                }
            }
            result = true;
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            throw new DaoException("There are some errors with DB!");
        } catch (IllegalArgumentException ex) {
            throw new DaoException("Current user already exists!");
        }
        return result;
    }
}
