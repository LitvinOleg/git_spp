package java.web.beans;

import java.util.List;
import java.web.entity.Load;
import java.web.entity.Transport;

public class Dispatcher extends User {
    private String name;
    private String password;

    public Dispatcher(List<Load> allLoads, List<Transport> allTransports) {
        super(allLoads, allTransports);
    }

    public void removeLoad(Load load) { allLoads.remove(load); }
    public void  removeTransport(Transport transport) { allTransports.remove(transport); }
}
