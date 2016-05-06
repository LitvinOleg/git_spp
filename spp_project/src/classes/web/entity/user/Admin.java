package classes.web.entity.user;

import java.util.List;

import classes.web.entity.Order;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        if (allOrders != null ? !allOrders.equals(admin.allOrders) : admin.allOrders != null) return false;
        return allUsers != null ? allUsers.equals(admin.allUsers) : admin.allUsers == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (allOrders != null ? allOrders.hashCode() : 0);
        result = 31 * result + (allUsers != null ? allUsers.hashCode() : 0);
        return result;
    }
}
