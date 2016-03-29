package java.web.beans;

import java.util.List;
import java.web.entity.Load;
import java.web.entity.Order;
import java.web.entity.Transport;

public class Client extends User {
    private String name;
    private String password;
    private Order order;



    public Client(List<Load> loads, List<Transport> transports) {
        super(loads, transports);
    }
}
