package classes.web.model.dao;

import classes.web.entity.Load;
import classes.web.entity.Transport;
import classes.web.entity.user.Admin;
import classes.web.entity.user.Dispatcher;
import classes.web.model.dao.connection.DaoConnector;
import classes.web.entity.user.Client;
import classes.web.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DAO for modification DB
 */
public class ModificationDao {
    private static Connection connection;

    private final static String INSERT_NEW_USER_STATEMENT = "INSERT INTO users(user_login, user_name, user_surname, user_password, user_type) VALUES ('%s', '%s', '%s', '%s', %d);";
    private final static String INSERT_NEW_CLIENT_STATEMENT = "INSERT INTO client(client_login, order_id) VALUES ('%s', %d);";
    private final static String INSERT_NEW_ORDER_STATEMENT = "INSERT INTO orders(order_id) VALUE (%d);";
    private final static String INSERT_NEW_LOAD_TO_CLIENT_STATEMENT = "INSERT INTO order_load(order_id, load_id) VALUES ((SELECT order_id FROM client WHERE client_login='%s'), %d);";
    private final static String INSERT_NEW_TRANSPORT_TO_CLIENT_STATEMENT = "INSERT INTO order_transport(order_id, state_number) VALUES ((SELECT order_id FROM client WHERE client_login='%s'), %d);";
    private final static String INSERT_NEW_FREE_LOAD_STATEMENT = "INSERT INTO loads(weight, cost_of_delivery, load_type, load_description) VALUES (%d, %d, %d, '%s');";
    private final static String INSERT_NEW_TRANSPORT_STATEMENT = "INSERT INTO transport(state_number, model, tonnage, trailer_type, payment_for_kilometer) VALUES (%d, '%s', %d, %d, %d);";

    private final static String SELECT_NON_EXISTING_ELEMENT_BY_ORDER_ID_STATEMENT = "SELECT * FROM %s WHERE %s NOT IN (select %s from %s) AND %s=%d;";
    private final static String SELECT_NON_EXISTING_LOAD_BY_LOAD_ID_STATEMENT = "SELECT * FROM loads where loads.load_id NOT IN (SELECT order_load.load_id FROM order_load) AND loads.load_id = %d;";
    private final static String SELECT_NON_EXISTING_TRANSPORT_BY_STATE_NUMBER_STATEMENT = "SELECT * FROM transport where transport.state_number NOT IN (SELECT order_transport.state_number FROM order_transport) AND transport.state_number = %d;";
    private final static String SELECT_USER_LOGIN_STATEMENT = "SELECT user_login FROM users WHERE user_login='%s'";
    private final static String SELECT_LOAD_BY_ID_AND_LOGIN_STATEMENT = "SELECT * FROM loads INNER JOIN order_load ON loads.load_id = order_load.load_id INNER JOIN client ON order_load.order_id = client.order_id WHERE loads.load_id = %d AND client.client_login = '%s';";
    private final static String SELECT_TRANSPORT_BY_STATE_NUMBER_AND_LOGIN_STATEMENT ="SELECT * FROM transport INNER JOIN order_transport ON transport.state_number = order_transport.state_number INNER JOIN client ON order_transport.order_id = client.order_id WHERE transport.state_number = %d AND client.client_login = '%s';";
    private final static String SELECT_ORDER_ID_STATEMENT = "SELECT client.order_id FROM client WHERE client_login = '%s';";
    private final static String SELECT_TRANSPORT_BY_STATE_NUMBER_STATEMENT = "SELECT * FROM transport WHERE transport.state_number = %d";

    private final static String DELETE_LOAD_BY_ID_STATEMENT = "DELETE FROM loads WHERE loads.load_id = %d;";
    private final static String DELETE_TRANSPORT_BY_ID_STATEMENT = "DELETE FROM transport WHERE transport.state_number = %d;;";
    private final static String DELETE_LOAD_FROM_ORDER_LOAD_STATEMENT = "DELETE FROM order_load WHERE order_load.load_id = %d;";
    private final static String DELETE_TRANSPORT_FROM_ORDER_TRANSPORT_STATEMENT = "DELETE FROM order_transport WHERE order_transport.state_number = %d;";
    private final static String DELETE_USER_STATEMENT = "DELETE FROM users WHERE user_login = '%s';";
    private final static String DELETE_CLIENT_BY_ORDER_ID_STATEMENT = "DELETE FROM client WHERE client.order_id = %d;";
    private final static String DELETE_ORDER_LOAD_STATEMENT = "DELETE FROM order_load WHERE order_load.order_id = %d;";
    private final static String DELETE_LOAD_BY_ORDER_ID_STATEMENT = "DELETE FROM loads WHERE loads.load_id IN (SELECT order_load.load_id FROM order_load WHERE order_load.order_id = %d);";

    public static boolean insertNewClient(Client client) throws DaoException {
        boolean result;
        String login = client.getLogin();
        String name = client.getName();
        String surname = client.getSurname();
        String password = client.getPassword();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet usersLogin = statement.executeQuery(String.format(SELECT_USER_LOGIN_STATEMENT, login));
            if (usersLogin.next())
                throw new IllegalArgumentException("Current user already exists!");
            int clientOrderID = client.getOrder().getOrderID();
            statement.executeUpdate(String.format(INSERT_NEW_USER_STATEMENT, login, name, surname, password, client.getUserType().getEnumValue()));
            statement.executeUpdate(String.format(INSERT_NEW_CLIENT_STATEMENT, login, clientOrderID));
            statement.executeUpdate(String.format(INSERT_NEW_ORDER_STATEMENT, clientOrderID));
            result = true;
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            throw new DaoException("There are some errors with DB!");
        } catch (IllegalArgumentException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean insertNewDispatcher(Dispatcher dispatcher) throws DaoException {
        boolean result;
        String login = dispatcher.getLogin();
        String name = dispatcher.getName();
        String surname = dispatcher.getSurname();
        String password = dispatcher.getPassword();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet usersLogin = statement.executeQuery(String.format(SELECT_USER_LOGIN_STATEMENT, login));
            if (usersLogin.next())
                throw new IllegalArgumentException("Current user already exists!");
            statement.executeUpdate(String.format(INSERT_NEW_USER_STATEMENT, login, name, surname, password, dispatcher.getUserType().getEnumValue()));
            result = true;
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            throw new DaoException("There are some errors with DB!");
        } catch (IllegalArgumentException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean insertNewAdmin(Admin admin) throws DaoException {
        boolean result;
        String login = admin.getLogin();
        String name = admin.getName();
        String surname = admin.getSurname();
        String password = admin.getPassword();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet usersLogin = statement.executeQuery(String.format(SELECT_USER_LOGIN_STATEMENT, login));
            if (usersLogin.next())
                throw new IllegalArgumentException("Current user already exists!");
            statement.executeUpdate(String.format(INSERT_NEW_USER_STATEMENT, login, name, surname, password, admin.getUserType().getEnumValue()));
            result = true;
            connection.close();
            statement.close();
        } catch (SQLException ex) {
            throw new DaoException("There are some errors with DB!");
        } catch (IllegalArgumentException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean insertClientLoad(int loadID, String login) throws DaoException {
        boolean result;
        final String loadTable = "loads";
        final String orderLoadTable = "order_load";
        final String columnName = "load_id";
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(SELECT_NON_EXISTING_ELEMENT_BY_ORDER_ID_STATEMENT, loadTable, columnName, columnName, orderLoadTable, columnName, loadID));
            if (!resultSet.next())
                throw new DaoException("Incorrect load id");
            statement.execute(String.format(INSERT_NEW_LOAD_TO_CLIENT_STATEMENT, login, loadID));
            result = true;
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean insertFreeLoad(Load load) throws DaoException {
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            statement.execute(String.format(INSERT_NEW_FREE_LOAD_STATEMENT,
                    load.getWeight(), load.getCostOfDelivery(),
                    load.getLoadType().getEnumValue(), load.getLoadDescription()));
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            throw new DaoException("Some problems with DB!");
        }
    }
    public static boolean insertClientTransport(int state_number, String login) throws DaoException {
        boolean result;
        final String transportTable = "transport";
        final String orderTransportTable = "order_transport";
        final String columnName = "state_number";
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(SELECT_NON_EXISTING_ELEMENT_BY_ORDER_ID_STATEMENT, transportTable, columnName, columnName, orderTransportTable, columnName, state_number));
            if (!resultSet.next())
                throw new DaoException("Incorrect transport state number!");
            statement.execute(String.format(INSERT_NEW_TRANSPORT_TO_CLIENT_STATEMENT, login, state_number));
            result = true;
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean insertFreeTransport(Transport transport) throws DaoException {
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultTransport = statement.executeQuery(String.format(SELECT_TRANSPORT_BY_STATE_NUMBER_STATEMENT, transport.getStateNumber()));
            if (resultTransport.next())
                throw new DaoException("Current transport already exists!");
            statement.execute(String.format(INSERT_NEW_TRANSPORT_STATEMENT, transport.getStateNumber(),
                    transport.getModel(), transport.getTonnage(), transport.getTrailerType().getEnumValue(), transport.getPaymentForKilometer()));
            statement.close();
            connection.close();
            return true;
        } catch (SQLException ex) {
            throw new DaoException("Some problems with DB!");
        } catch (DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    public static boolean deleteUser(String login) throws DaoException {
        boolean result;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultUsers = statement.executeQuery(String.format(SELECT_USER_LOGIN_STATEMENT, login));
            if (resultUsers.next()) {
                statement.execute(String.format(DELETE_USER_STATEMENT, login));
                ResultSet resultClient = statement.executeQuery(String.format(SELECT_ORDER_ID_STATEMENT, login));
                if (resultClient.next()) {
                    int orderID = resultClient.getInt("order_id");
                    statement.execute(String.format(DELETE_CLIENT_BY_ORDER_ID_STATEMENT, orderID));
                    statement.execute(String.format(DELETE_LOAD_BY_ORDER_ID_STATEMENT, orderID));
                    statement.execute(String.format(DELETE_ORDER_LOAD_STATEMENT, orderID));
                }
                result = true;
            } else
                throw new DaoException("There is no current user!");
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean deleteClientLoad(int loadID, String login) throws DaoException {
        boolean result = false;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultLoad = statement.executeQuery(String.format(SELECT_LOAD_BY_ID_AND_LOGIN_STATEMENT, loadID, login));
            if (resultLoad.next()) {
                    statement.execute(String.format(DELETE_LOAD_BY_ID_STATEMENT, loadID));
                    statement.execute(String.format(DELETE_LOAD_FROM_ORDER_LOAD_STATEMENT, loadID));
                    return true;
            }
            if (!result)
                throw new DaoException("Incorrect load id!");
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean deleteFreeLoad(int loadID) throws DaoException {
        boolean result = false;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultLoad = statement.executeQuery(String.format(SELECT_NON_EXISTING_LOAD_BY_LOAD_ID_STATEMENT, loadID));
            if (resultLoad.next()) {
                statement.execute(String.format(DELETE_LOAD_BY_ID_STATEMENT, loadID));
                return true;
            }
            if (!result)
                throw new DaoException("Incorrect load id!");
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean deleteClientTransport(int state_number, String login) throws DaoException {
        boolean result = false;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultLoad = statement.executeQuery(String.format(SELECT_TRANSPORT_BY_STATE_NUMBER_AND_LOGIN_STATEMENT, state_number, login));
            if (resultLoad.next()) {
                statement.execute(String.format(DELETE_TRANSPORT_BY_ID_STATEMENT, state_number));
                statement.execute(String.format(DELETE_TRANSPORT_FROM_ORDER_TRANSPORT_STATEMENT, state_number));
                return true;
            }
            if (!result)
                throw new DaoException("Incorrect truck state number!");
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
    public static boolean deleteFreeTransport(int state_number) throws DaoException {
        boolean result = false;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultLoad = statement.executeQuery(String.format(SELECT_NON_EXISTING_TRANSPORT_BY_STATE_NUMBER_STATEMENT, state_number));
            if (resultLoad.next()) {
                statement.execute(String.format(DELETE_TRANSPORT_BY_ID_STATEMENT, state_number));
                return true;
            }
            if (!result)
                throw new DaoException("Incorrect truck state number!");
            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }
}
