package java.web.entity.user;

import java.util.ArrayList;
import java.util.List;
import java.web.entity.Load;
import java.web.entity.Order;
import java.web.entity.Transport;

public class Admin extends User {
    private List<Order> allOrders;
    private List<User> allUsers;

    public Admin() {
        this.userType = UserType.ADMIN;
    }

    public List<Order> getAllOrders() {
        return allOrders;
    }
    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllOrders(List<Order> allOrders) {
        this.allOrders = allOrders;
    }
    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}
