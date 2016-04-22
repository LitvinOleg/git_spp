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
}
