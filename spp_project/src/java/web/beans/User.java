package java.web.beans;

import java.util.List;
import java.web.entity.Load;
import java.web.entity.Transport;

public class User {
    protected List<Load> allLoads;
    protected List<Transport> allTransports;

    public User(List<Load> allLoads, List<Transport> allTransports) {
        this.allLoads = allLoads;
        this.allTransports = allTransports;
    }

    public List<Load> getAllLoads() {
        return allLoads;
    }
    public List<Transport> getAllTransports() {
        return allTransports;
    }
}
