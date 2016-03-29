package java.web.beans;

import java.util.ArrayList;
import java.util.List;
import java.web.entity.Load;
import java.web.entity.Order;
import java.web.entity.Transport;

public class Admin extends User {
    private final String name = "admin";
    private final String password = "admin";
    private List<Order> allOrders;
    private List<User> allUsers;

    public Admin(List<Load> loads,
                 List<Transport> transports) {
        super(loads, transports);
        allOrders = new ArrayList<>();
        allUsers = new ArrayList<>();
    }

    public void addOrder(Order order) { allOrders.add(order); }
    public void removeOrder(Order order) {
        allOrders.remove(order);
    }

    public void addUser(User user) { allUsers.add(user); }
    public void removeUser(User user) { allUsers.remove(user);}
}
