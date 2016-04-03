package java.web.entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private List<Load> loadList;
    private List<Transport> transportList;

    public Order() {
        loadList = new ArrayList<>();
        transportList = new ArrayList<>();
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
}
