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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        return order != null ? order.equals(client.order) : client.order == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }
}
