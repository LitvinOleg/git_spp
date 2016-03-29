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

    public void addLoad(Load load) { loadList.add(load); }
    public void removeLoad (Load load) { loadList.remove(load); }

    public void addTransport(Transport transport) { transportList.add(transport); }
    public void removeTransport(Transport transport) { transportList.remove(transport); }

    public List<Load> getLoadList() {
        return loadList;
    }
    public List<Transport> getTransportList() {
        return transportList;
    }
}
