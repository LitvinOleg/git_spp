package java.web.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.web.entity.Load;
import java.web.entity.Order;
import java.web.entity.Transport;
import java.web.entity.user.User;

/**
 * Created by Олег on 30.03.2016.
 */
public class cachedData {
    List<Order> allOrderList = new ArrayList<>();
    List<Load> allLoadList = new ArrayList<>();
    List<Transport> allTransportList = new ArrayList<>();
    List<User> allUserList = new ArrayList<>();

    public void selectAllLoadList() {
        try {
            Connection connection = DAOConnector.getInstance();
            Statement statement = connection.createStatement();




        }catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
