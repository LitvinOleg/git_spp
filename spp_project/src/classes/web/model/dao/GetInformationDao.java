package classes.web.model.dao;

import classes.web.entity.Load;
import classes.web.entity.Order;
import classes.web.entity.Transport;
import classes.web.entity.user.Client;
import classes.web.entity.user.User;
import classes.web.model.dao.connection.DaoConnector;
import classes.web.model.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * DAO for showing information from DB
 */
public class GetInformationDao {
    private static final String SELECT_USERS_WITHOUT_ONE_ADMIN_STATEMENT = "SELECT * FROM users WHERE NOT user_login = '%s';";
    private static final String SELECT_NON_EXISTING_ELEMENT_STATEMENT = "SELECT * FROM %s WHERE %s NOT IN (select %s from %s);";
    private static final String SELECT_USER_STATEMENT = "SELECT * FROM users WHERE user_login = '%s' AND user_password = '%s';";
    private static final String SELECT_CLIENT_ORDER_STATEMENT = "select order_id from client where client_login = '%s';";
    private static final String SELECT_CLIENT_LOADS_STATEMENT = "select * from loads inner join order_load on loads.load_id = order_load.load_id where order_load.order_id = %d;";
    private static final String SELECT_CLIENT_ORDER_ID_STATEMENT = "SELECT client.order_id FROM client where client.client_login = '%s';";
    private static final String SELECT_CLIENT_TRANSPORTS_STATEMENT = "SELECT * FROM transport INNER JOIN order_transport ON transport.state_number = order_transport.state_number where order_transport.order_id = %d;";
    private static Connection connection;


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
                load.setLoadID(result.getInt("load_id"));
                load.setWeight(result.getInt("weight"));
                load.setCostOfDelivery(result.getInt("cost_of_delivery"));
                load.setLoadType(result.getInt("load_type"));
                load.setLoadDescription(result.getString("load_description"));
                loadList.add(load);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            throw new DaoException("Some problems with connection!");
        }
        return loadList;
    }

    public static List<Transport> selectAllFreeTransports() throws DaoException {
        final String transportTable = "transport";
        final String orderTransportTable = "order_transport";
        final String columnName = "state_number";
        List<Transport> transportList = new ArrayList<>();
        try
        {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(String.format(SELECT_NON_EXISTING_ELEMENT_STATEMENT, transportTable, columnName, columnName, orderTransportTable));
            while (result.next()) {
                Transport transport = new Transport();
                transport.setStateNumber(result.getInt("state_number"));
                transport.setModel(result.getString("model"));
                transport.setTonnage(result.getInt("tonnage"));
                transport.setTrailerType(result.getInt("trailer_type"));
                transport.setPaymentForKilometer(result.getInt("payment_for_kilometer"));
                transportList.add(transport);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            throw new DaoException("Some problems with connection!");
        }
        return transportList;
    }

    public static List<Load> selectAllClientLoads(String login) throws DaoException {
        List<Load> loadList = new ArrayList<>();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet orderIDResult = statement.executeQuery(String.format(SELECT_CLIENT_ORDER_ID_STATEMENT, login));
            int orderID = 0;
            while (orderIDResult.next())
                orderID = orderIDResult.getInt("order_id");
            ResultSet loadsResult = statement.executeQuery(String.format(SELECT_CLIENT_LOADS_STATEMENT, orderID));
            while (loadsResult.next()) {
                Load load = new Load();
                load.setLoadID(loadsResult.getInt("load_id"));
                load.setWeight(loadsResult.getInt("weight"));
                load.setCostOfDelivery(loadsResult.getInt("cost_of_delivery"));
                load.setLoadType(loadsResult.getInt("load_type"));
                load.setLoadDescription(loadsResult.getString("load_description"));
                loadList.add(load);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex ) {
            throw new DaoException(ex.getMessage());
        }
        return loadList;
    }

    public static List<Transport> selectAllClientTransports(String login) throws DaoException {
        List<Transport> transportList = new ArrayList<>();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet orderIDResult = statement.executeQuery(String.format(SELECT_CLIENT_ORDER_ID_STATEMENT, login));
            int orderID = 0;
            while (orderIDResult.next())
                orderID = orderIDResult.getInt("order_id");
            ResultSet transportResult = statement.executeQuery(String.format(SELECT_CLIENT_TRANSPORTS_STATEMENT, orderID));
            while (transportResult.next()) {
                Transport transport = new Transport();
                transport.setStateNumber(transportResult.getInt("state_number"));
                transport.setModel(transportResult.getString("model"));
                transport.setTonnage(transportResult.getInt("tonnage"));
                transport.setTrailerType(transportResult.getInt("trailer_type"));
                transport.setPaymentForKilometer(transportResult.getInt("payment_for_kilometer"));
                transportList.add(transport);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex ) {
            throw new DaoException(ex.getMessage());
        }
        return transportList;
    }

    public static List<User> selectAllUsers(String login) throws DaoException {
        List<User> userList = new ArrayList<>();
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultUsers = statement.executeQuery(String.format(SELECT_USERS_WITHOUT_ONE_ADMIN_STATEMENT, login));
            while (resultUsers.next()) {
                User user = new User();
                user.setLogin(resultUsers.getString("user_login"));
                user.setName(resultUsers.getString("user_name"));
                user.setSurname(resultUsers.getString("user_surname"));
                user.setUserType(resultUsers.getInt("user_type"));
                userList.add(user);
            }
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            throw new DaoException("Some problems with DB!");
        }
        return userList;
    }

    public static int loginUserDao(String login, String password) throws DaoException {
        int result = 0;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();
            ResultSet resultDB = statement.executeQuery(String.format(SELECT_USER_STATEMENT, login, password));
            if (resultDB.next())
                result = resultDB.getInt("user_type");
            else
                throw new DaoException("Incorrect password or login!");

            statement.close();
            connection.close();
        } catch (SQLException | DaoException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }












    //TODO get user!!!
    public static User getUserDao(String login, String password) throws DaoException {
        User user;
        try {
            connection = DaoConnector.getInstance();
            Statement statement = connection.createStatement();

            ResultSet userResult = statement.executeQuery(String.format(SELECT_USER_STATEMENT, login, password));
            if (userResult == null)
                throw new DaoException("Incorrect login or password!");

            while (userResult.next()) {
                String userLogin = userResult.getString("user_login");
                String userName = userResult.getString("user_name");
                String userSurname = userResult.getString("user_surname");
                String userPassword = userResult.getString("user_password");
                int iUserType = userResult.getInt("user_type");
                switch (iUserType) {
                    case 1: { // client
                        ResultSet clientResult = statement.executeQuery(String.format(SELECT_CLIENT_ORDER_STATEMENT, userLogin));
                        int orderID=0;
                        while (clientResult.next())
                            orderID = clientResult.getInt("order_id");
                        Order order = new Order(orderID);
                        //ResultSet loadsAndTransports = statement.executeQuery();

                    } break;
                    case 2: { // dispatcher
                    } break;
                    case 3: { // admin

                    }
                }


            }

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            throw new DaoException("Some problems with connection!");
        }
        return null;
    }
}
