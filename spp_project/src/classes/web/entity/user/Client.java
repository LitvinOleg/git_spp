package classes.web.entity.user;

import classes.web.entity.Order;

public class Client extends User {

    private Order order;

    public Client() {
        this.order = new Order();
        this.userType = UserType.CLIENT;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
