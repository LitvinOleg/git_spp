package classes.web.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private List<Load> loadList;
    private List<Transport> transportList;

    public Order() {
        loadList = new ArrayList<>();
        transportList = new ArrayList<>();
        this.generateOrderId();
    }

    public Order(int orderID) {
        loadList = new ArrayList<>();
        transportList = new ArrayList<>();
        this.orderID = orderID;
    }

    public int getOrderID() {
        return orderID;
    }
    public List<Load> getLoadList() {
        return loadList;
    }
    public List<Transport> getTransportList() {
        return transportList;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    public void setLoadList(List<Load> loadList) {
        this.loadList = loadList;
    }
    public void setTransportList(List<Transport> transportList) {
        this.transportList = transportList;
    }

    private void generateOrderId() {
        Date date = new Date();
        this.orderID = (int) (date.getTime()/1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderID != order.orderID) return false;
        if (loadList != null ? !loadList.equals(order.loadList) : order.loadList != null) return false;
        return transportList != null ? transportList.equals(order.transportList) : order.transportList == null;

    }

    @Override
    public int hashCode() {
        int result = orderID;
        result = 31 * result + (loadList != null ? loadList.hashCode() : 0);
        result = 31 * result + (transportList != null ? transportList.hashCode() : 0);
        return result;
    }
}
