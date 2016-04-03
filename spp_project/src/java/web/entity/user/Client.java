package java.web.entity.user;

import java.util.List;
import java.web.entity.Load;
import java.web.entity.Order;
import java.web.entity.Transport;

public class Client extends User {

    private Order order;

    public Client() {
        this.userType = UserType.CLIENT;
    }

    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
