package classes.web;

import classes.web.entity.Load;
import classes.web.entity.Order;
import classes.web.entity.Transport;
import classes.web.entity.user.Client;
import classes.web.entity.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void equalsTransport() throws Exception {
        Transport transport1 = new Transport();
        Transport transport2 = new Transport();
        transport1.setModel("fdst");
        transport1.setPaymentForKilometer(32);
        transport1.setTonnage(43);
        transport2.setModel("fdst");
        transport2.setPaymentForKilometer(32);
        transport2.setTonnage(43);
        assertEquals(transport1.equals(transport2), true);
    }

    @Test
    public void equalsLoad() throws Exception {
        Load load1 = new Load();
        Load load2 = new Load();
        load1.setLoadID(1232);
        load1.setLoadDescription("description");
        load1.setWeight(23);
        load1.setCostOfDelivery(12);
        load1.setLoadType(2);
        load2.setLoadID(1232);
        load2.setLoadDescription("description");
        load2.setWeight(23);
        load2.setCostOfDelivery(12);
        load2.setLoadType(2);
        assertEquals(load1.equals(load2), true);
    }

    @Test
    public void getSetName() throws Exception {
        User user = new User();
        user.setName("Jack");
        assertEquals(user.getName(), "Jack");
    }

    @Test
    public void getSetSurname() throws Exception {
        User user = new User();
        user.setSurname("Stepanov");
        assertEquals(user.getSurname(), "Stepanov");
    }

    @Test
    public void getSetLogin() throws Exception {
        User user = new User();
        user.setLogin("login");
        assertEquals(user.getLogin(), "login");
    }

    @Test
    public void getSetPassword() throws Exception {
        User user = new User();
        user.setPassword("password");
        assertEquals(user.getPassword(), "password");
    }

    @Test
    public void getUserType() throws Exception {
        User user = new User();
        user.setUserType(3);
        assertEquals(user.getUserType(), User.UserType.CLIENT);
    }

    @Test
    public void getSetAllLoads() throws Exception {
        User user = new User();
        user.setAllLoads(new ArrayList<>());
        user.getAllLoads().add(new Load());
        List<Load> list = new ArrayList<>();
        list.add(new Load());
        assertEquals(user.getAllLoads(), list);
    }

    @Test
    public void getSetAllTransports() throws Exception {
        User user = new User();
        List<Transport> list1 = new ArrayList<>();
        list1.add(new Transport());
        List<Transport> list2 = new ArrayList<>();
        list2.add(new Transport());
        user.setAllTransports(list1);
        assertEquals(user.getAllTransports(), list2);
    }

    @Test
    public void getSetOrderID() throws Exception {
        Client client1 = new Client();
        Client client2 = new Client();
        assertEquals(client1.getOrder().getOrderID() == client2.getOrder().getOrderID(), true);
    }

    @Test
    public void equalsClient() throws Exception {
        Client client1 = new Client();
        client1.setOrder(new Order(443));
        Client client2 = new Client();
        client2.setOrder(new Order(443));
        assertEquals(client1.equals(client2), true);
    }

    @Test
    public void equalsOrder() throws Exception {
        Order order1 = new Order(32);
        Order order2 = new Order(32);
        assertEquals(order1.equals(order2), true);
    }

    @Test
    public void equalsTransportFirst() throws Exception {
        Transport transport1 = new Transport();
        Transport transport2 = new Transport();
        transport1.setModel("fds");
        transport1.setPaymentForKilometer(323);
        transport1.setTonnage(453);
        transport2.setModel("fds");
        transport2.setPaymentForKilometer(323);
        transport2.setTonnage(453);
        assertEquals(transport1.equals(transport2), true);
    }

    @Test
    public void equalsUser() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setName("Stepanc");
        user1.setSurname("Ivancov");
        user1.setPassword("432fds");
        user1.setLogin("fsdfsdf");
        user1.setUserType(2);
        user2.setName("Stepanc");
        user2.setSurname("Ivancov");
        user2.setPassword("432fds");
        user2.setLogin("fsdfsdf");
        user2.setUserType(2);
        assertEquals(user1.equals(user2), true);
    }

    @Test
    public void testGetSetStateNumber() throws Exception {
        Transport transport = new Transport();
        transport.setStateNumber(2423);
        assertEquals(transport.getStateNumber(), 2423);
    }

    @Test
    public void testGetSetModel() throws Exception {
        Transport transport = new Transport();
        transport.setModel("Mercedes");
        assertEquals(transport.getModel(), "Mercedes");
    }

    @Test
    public void testGetSetTonnage() throws Exception {
        Transport transport = new Transport();
        transport.setTonnage(342);
        assertEquals(transport.getTonnage(), 342);
    }

    @Test
    public void testGetSetPaymentForKilometer() throws Exception {
        Transport transport = new Transport();
        transport.setPaymentForKilometer(23);
        assertEquals(transport.getPaymentForKilometer(), 23);
    }

    @Test
    public void testGetTrailerTypeName() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(2);
        assertEquals(transport.getTrailerType(), Transport.TrailerType.REFRIGERATOR);
    }

    @Test
    public void testGetEnumValue() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(Transport.TrailerType.JUMBO.getEnumValue());
        assertEquals(transport.getTrailerType().getEnumValue(), Transport.TrailerType.JUMBO.getEnumValue());
    }

    @Test
    public void testGetSetOrderID() throws Exception {
        Order order = new Order();
        order.setOrderID(4324);
        assertEquals(order.getOrderID(), 4324);
    }

    @Test
    public void getSetLoadID() throws Exception {
        Load load = new Load();
        load.setLoadID(23);
        assertEquals(load.getLoadID(), 23);
    }

    @Test
    public void getSetWeight() throws Exception {
        Load load = new Load();
        load.setWeight(20);
        assertEquals(load.getWeight(), 20);
    }

    @Test
    public void getSetCostOfDelivery() throws Exception {
        Load load = new Load();
        load.setCostOfDelivery(21);
        assertEquals(load.getCostOfDelivery(), 21);
    }

    @Test
    public void getSetLoadType() throws Exception {
        Load load = new Load();
        load.setLoadType(1);
        assertEquals(load.getLoadType().getEnumValue(), 1);
    }

    @Test
    public void getSetLoadDescription() throws Exception {
        Load load = new Load();
        load.setLoadDescription("description");
        assertEquals(load.getLoadDescription(), "description");
    }

    @Test
    public void equalsLoadFirst() throws Exception {
        Load load1 = new Load();
        Load load2 = new Load();
        load1.setLoadID(123);
        load1.setLoadDescription("description");
        load1.setWeight(23);
        load1.setCostOfDelivery(125);
        load1.setLoadType(2);
        load2.setLoadID(123);
        load2.setLoadDescription("description");
        load2.setWeight(23);
        load2.setCostOfDelivery(125);
        load2.setLoadType(2);
        assertEquals(load1.equals(load2), true);
    }

    @Test
    public void getSetNameSecond() throws Exception {
        User user = new User();
        user.setName("Jack");
        assertEquals(user.getName(), "Jack");
    }

    @Test
    public void getSetSurnameSecond() throws Exception {
        User user = new User();
        user.setSurname("Stepanov");
        assertEquals(user.getSurname(), "Stepanov");
    }

    @Test
    public void getSetLoginSecond() throws Exception {
        User user = new User();
        user.setLogin("login");
        assertEquals(user.getLogin(), "login");
    }

    @Test
    public void getSetPasswordSecond() throws Exception {
        User user = new User();
        user.setPassword("password");
        assertEquals(user.getPassword(), "password");
    }

    @Test
    public void getUserTypeSecond() throws Exception {
        User user = new User();
        user.setUserType(3);
        assertEquals(user.getUserType(), User.UserType.CLIENT);
    }

    @Test
    public void getSetAllLoadsSecond() throws Exception {
        User user = new User();
        user.setAllLoads(new ArrayList<>());
        user.getAllLoads().add(new Load());
        List<Load> list = new ArrayList<>();
        list.add(new Load());
        assertEquals(user.getAllLoads(), list);
    }

    @Test
    public void getSetAllTransportsSecond() throws Exception {
        User user = new User();
        List<Transport> list1 = new ArrayList<>();
        list1.add(new Transport());
        List<Transport> list2 = new ArrayList<>();
        list2.add(new Transport());
        user.setAllTransports(list1);
        assertEquals(user.getAllTransports(), list2);
    }

    @Test
    public void getSetOrderIDSecond() throws Exception {
        Client client1 = new Client();
        Client client2 = new Client();
        assertEquals(client1.getOrder().getOrderID() == client2.getOrder().getOrderID(), true);
    }

    @Test
    public void equalsClientSecond() throws Exception {
        Client client1 = new Client();
        client1.setOrder(new Order(433));
        Client client2 = new Client();
        client2.setOrder(new Order(433));
        assertEquals(client1.equals(client2), true);
    }

    @Test
    public void equalsOrderSecond() throws Exception {
        Order order1 = new Order(32);
        Order order2 = new Order(32);
        assertEquals(order1.equals(order2), true);
    }

    @Test
    public void equalsTransportSecond() throws Exception {
        Transport transport1 = new Transport();
        Transport transport2 = new Transport();
        transport1.setModel("fdsr");
        transport1.setPaymentForKilometer(32);
        transport1.setTonnage(43);
        transport2.setModel("fdsr");
        transport2.setPaymentForKilometer(32);
        transport2.setTonnage(43);
        assertEquals(transport1.equals(transport2), true);
    }

    @Test
    public void equalsUserSecond() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setName("Serhei");
        user1.setSurname("Ivancov");
        user1.setPassword("432fds");
        user1.setLogin("fsdfsdf");
        user1.setUserType(2);
        user2.setName("Serhei");
        user2.setSurname("Ivancov");
        user2.setPassword("432fds");
        user2.setLogin("fsdfsdf");
        user2.setUserType(2);
        assertEquals(user1.equals(user2), true);
    }

    @Test
    public void testGetSetStateNumberSecond() throws Exception {
        Transport transport = new Transport();
        transport.setStateNumber(23);
        assertEquals(transport.getStateNumber(), 23);
    }

    @Test
    public void testGetSetModelSecond() throws Exception {
        Transport transport = new Transport();
        transport.setModel("Man");
        assertEquals(transport.getModel(), "Man");
    }

    @Test
    public void testGetSetTonnageSecond() throws Exception {
        Transport transport = new Transport();
        transport.setTonnage(3452);
        assertEquals(transport.getTonnage(), 3452);
    }

    @Test
    public void testGetSetPaymentForKilometerSecond() throws Exception {
        Transport transport = new Transport();
        transport.setPaymentForKilometer(223);
        assertEquals(transport.getPaymentForKilometer(), 223);
    }

    @Test
    public void testGetTrailerTypeNameSecond() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(2);
        assertEquals(transport.getTrailerType().getEnumValue(), 2);
    }

    @Test
    public void testGetEnumValueSecond() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(3);
        assertEquals(transport.getTrailerType().getEnumValue(), 3);
    }

    @Test
    public void testGetSetOrderIDSecond() throws Exception {
        Order order = new Order();
        order.setOrderID(43654);
        assertEquals(order.getOrderID(), 43654);
    }

    @Test
    public void getSetLoadIDSecond() throws Exception {
        Load load = new Load();
        load.setLoadID(30);
        assertEquals(load.getLoadID(), 30);
    }

    @Test
    public void getSetWeightSecond() throws Exception {
        Load load = new Load();
        load.setWeight(30);
        assertEquals(load.getWeight(), 30);
    }

    @Test
    public void getSetCostOfDeliverySecond() throws Exception {
        Load load = new Load();
        load.setCostOfDelivery(29);
        assertEquals(load.getCostOfDelivery(), 29);
    }

    @Test
    public void getSetLoadTypeSecond() throws Exception {
        Load load = new Load();
        load.setLoadType(Load.LoadType.ALIVE.getEnumValue());
        assertEquals(load.getLoadType().getEnumValue(), 4);
    }

    @Test
    public void getSetLoadDescriptionSecond() throws Exception {
        Load load = new Load();
        load.setLoadDescription("descr");
        assertEquals(load.getLoadDescription(), "descr");
    }

    @Test
    public void equalsLoadSecond() throws Exception {
        Load load1 = new Load();
        Load load2 = new Load();
        load1.setLoadID(22);
        load1.setLoadDescription("fds");
        load1.setWeight(28);
        load1.setCostOfDelivery(12);
        load1.setLoadType(2);
        load2.setLoadID(123);
        load2.setLoadDescription("description");
        load2.setWeight(23);
        load2.setCostOfDelivery(12);
        load2.setLoadType(2);
        assertEquals(load1.equals(load2), false);
    }

    @Test
    public void getSetNameThird() throws Exception {
        User user = new User();
        user.setName("Jack");
        assertEquals(user.getName(), "Jack");
    }

    @Test
    public void getSetSurnameThird() throws Exception {
        User user = new User();
        user.setSurname("Stepanov");
        assertEquals(user.getSurname(), "Stepanov");
    }

    @Test
    public void getSetLoginThird() throws Exception {
        User user = new User();
        user.setLogin("login");
        assertEquals(user.getLogin(), "login");
    }

    @Test
    public void getSetPasswordThird() throws Exception {
        User user = new User();
        user.setPassword("password");
        assertEquals(user.getPassword(), "password");
    }

    @Test
    public void getUserTypeThird() throws Exception {
        User user = new User();
        user.setUserType(3);
        assertEquals(user.getUserType(), User.UserType.CLIENT);
    }

    @Test
    public void getSetAllLoadsThird() throws Exception {
        User user = new User();
        user.setAllLoads(new ArrayList<>());
        user.getAllLoads().add(new Load());
        List<Load> list = new ArrayList<>();
        list.add(new Load());
        assertEquals(user.getAllLoads(), list);
    }

    @Test
    public void getSetAllTransportsThird() throws Exception {
        User user = new User();
        List<Transport> list1 = new ArrayList<>();
        list1.add(new Transport());
        List<Transport> list2 = new ArrayList<>();
        list2.add(new Transport());
        user.setAllTransports(list1);
        assertEquals(user.getAllTransports(), list2);
    }

    @Test
    public void getSetOrderIDThird() throws Exception {
        Client client1 = new Client();
        Client client2 = new Client();
        assertEquals(client1.getOrder().getOrderID() == client2.getOrder().getOrderID(), true);
    }

    @Test
    public void equalsClientThird() throws Exception {
        Client client1 = new Client();
        client1.setOrder(new Order(4673));
        Client client2 = new Client();
        client2.setOrder(new Order(4673));
        assertEquals(client1.equals(client2), true);
    }

    @Test
    public void equalsOrderThird() throws Exception {
        Order order1 = new Order(32);
        Order order2 = new Order(32);
        assertEquals(order1.equals(order2), true);
    }

    @Test
    public void equalsTransportThird() throws Exception {
        Transport transport1 = new Transport();
        Transport transport2 = new Transport();
        transport1.setModel("fds");
        transport1.setPaymentForKilometer(3243);
        transport1.setTonnage(43);
        transport2.setModel("fds");
        transport2.setPaymentForKilometer(3243);
        transport2.setTonnage(43);
        assertEquals(transport1.equals(transport2), true);
    }

    @Test
    public void equalsUserThird() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setName("Stepan");
        user1.setSurname("Ivancov");
        user1.setPassword("432fdsrt");
        user1.setLogin("fsdfsdf");
        user1.setUserType(2);
        user2.setName("Stepan");
        user2.setSurname("Ivancov");
        user2.setPassword("432fdsrt");
        user2.setLogin("fsdfsdf");
        user2.setUserType(2);
        assertEquals(user1.equals(user2), true);
    }

    @Test
    public void testGetSetStateNumberThird() throws Exception {
        Transport transport = new Transport();
        transport.setStateNumber(2423);
        assertEquals(transport.getStateNumber(), 2423);
    }

    @Test
    public void testGetSetModelThird() throws Exception {
        Transport transport = new Transport();
        transport.setModel("Mercedes");
        assertEquals(transport.getModel(), "Mercedes");
    }

    @Test
    public void testGetSetTonnageThird() throws Exception {
        Transport transport = new Transport();
        transport.setTonnage(342);
        assertEquals(transport.getTonnage(), 342);
    }

    @Test
    public void testGetSetPaymentForKilometerThird() throws Exception {
        Transport transport = new Transport();
        transport.setPaymentForKilometer(23);
        assertEquals(transport.getPaymentForKilometer(), 23);
    }

    @Test
    public void testGetTrailerTypeNameThird() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(2);
        assertEquals(transport.getTrailerType(), Transport.TrailerType.REFRIGERATOR);
    }

    @Test
    public void testGetEnumValueThird() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(Transport.TrailerType.JUMBO.getEnumValue());
        assertEquals(transport.getTrailerType().getEnumValue(), Transport.TrailerType.JUMBO.getEnumValue());
    }

    @Test
    public void testGetSetOrderIDThird() throws Exception {
        Order order = new Order();
        order.setOrderID(4324);
        assertEquals(order.getOrderID(), 4324);
    }

    @Test
    public void getSetLoadIDThird() throws Exception {
        Load load = new Load();
        load.setLoadID(23);
        assertEquals(load.getLoadID(), 23);
    }

    @Test
    public void getSetWeightThird() throws Exception {
        Load load = new Load();
        load.setWeight(20);
        assertEquals(load.getWeight(), 20);
    }

    @Test
    public void getSetCostOfDeliveryThird() throws Exception {
        Load load = new Load();
        load.setCostOfDelivery(21);
        assertEquals(load.getCostOfDelivery(), 21);
    }

    @Test
    public void getSetLoadTypeThird() throws Exception {
        Load load = new Load();
        load.setLoadType(1);
        assertEquals(load.getLoadType().getEnumValue(), 1);
    }

    @Test
    public void getSetLoadDescriptionThird() throws Exception {
        Load load = new Load();
        load.setLoadDescription("description");
        assertEquals(load.getLoadDescription(), "description");
    }

    @Test
    public void equalsLoadThird() throws Exception {
        Load load1 = new Load();
        Load load2 = new Load();
        load1.setLoadID(123);
        load1.setLoadDescription("description");
        load1.setWeight(23);
        load1.setCostOfDelivery(12);
        load1.setLoadType(2);
        load2.setLoadID(123);
        load2.setLoadDescription("description");
        load2.setWeight(23);
        load2.setCostOfDelivery(12);
        load2.setLoadType(2);
        assertEquals(load1.equals(load2), true);
    }

    @Test
    public void getSetAllTransportsFOrth() throws Exception {
        User user = new User();
        List<Transport> list1 = new ArrayList<>();
        list1.add(new Transport());
        List<Transport> list2 = new ArrayList<>();
        list2.add(new Transport());
        user.setAllTransports(list1);
        assertEquals(user.getAllTransports(), list2);
    }

    @Test
    public void getSetOrderIDForth() throws Exception {
        Client client1 = new Client();
        Client client2 = new Client();
        assertEquals(client1.getOrder().getOrderID() == client2.getOrder().getOrderID(), true);
    }

    @Test
    public void equalsClientForth() throws Exception {
        Client client1 = new Client();
        client1.setOrder(new Order(4312));
        Client client2 = new Client();
        client2.setOrder(new Order(4312));
        assertEquals(client1.equals(client2), true);
    }

    @Test
    public void equalsOrderFOrth() throws Exception {
        Order order1 = new Order(32);
        Order order2 = new Order(32);
        assertEquals(order1.equals(order2), true);
    }

    @Test
    public void equalsTransportForth() throws Exception {
        Transport transport1 = new Transport();
        Transport transport2 = new Transport();
        transport1.setModel("fdsOlezha");
        transport1.setPaymentForKilometer(32);
        transport1.setTonnage(43);
        transport2.setModel("fdsOlezha");
        transport2.setPaymentForKilometer(32);
        transport2.setTonnage(43);
        assertEquals(transport1.equals(transport2), true);
    }

    @Test
    public void equalsUserForth() throws Exception {
        User user1 = new User();
        User user2 = new User();
        user1.setName("Stepan");
        user1.setSurname("Ivancov");
        user1.setPassword("432fdsty");
        user1.setLogin("fsdfsdf");
        user1.setUserType(2);
        user2.setName("Stepan");
        user2.setSurname("Ivancov");
        user2.setPassword("432fdsty");
        user2.setLogin("fsdfsdf");
        user2.setUserType(2);
        assertEquals(user1.equals(user2), true);
    }

    @Test
    public void testGetSetStateNumberForth() throws Exception {
        Transport transport = new Transport();
        transport.setStateNumber(2423);
        assertEquals(transport.getStateNumber(), 2423);
    }

    @Test
    public void testGetSetModelForth() throws Exception {
        Transport transport = new Transport();
        transport.setModel("Mercedes");
        assertEquals(transport.getModel(), "Mercedes");
    }

    @Test
    public void testGetSetTonnageForth() throws Exception {
        Transport transport = new Transport();
        transport.setTonnage(3423);
        assertEquals(transport.getTonnage(), 3423);
    }

    @Test
    public void testGetSetPaymentForKilometerForth() throws Exception {
        Transport transport = new Transport();
        transport.setPaymentForKilometer(232);
        assertEquals(transport.getPaymentForKilometer(), 232);
    }

    @Test
    public void testGetTrailerTypeNameForth() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(Transport.TrailerType.REFRIGERATOR.getEnumValue());
        assertEquals(transport.getTrailerType(), Transport.TrailerType.REFRIGERATOR);
    }

    @Test
    public void testGetEnumValueForth() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(Transport.TrailerType.OPEN_PLATFORM.getEnumValue());
        assertEquals(transport.getTrailerType().getEnumValue(), Transport.TrailerType.OPEN_PLATFORM.getEnumValue());
    }

    @Test
    public void testGetSetStateNumberFourth() throws Exception {
        Transport transport = new Transport();
        transport.setStateNumber(2343);
        assertEquals(transport.getStateNumber(), 2343);
    }

    @Test
    public void testGetSetModelFourth() throws Exception {
        Transport transport = new Transport();
        transport.setModel("Mane");
        assertEquals(transport.getModel(), "Mane");
    }

    @Test
    public void testGetSetTonnageFourth() throws Exception {
        Transport transport = new Transport();
        transport.setTonnage(34521);
        assertEquals(transport.getTonnage(), 34521);
    }

    @Test
    public void testGetSetPaymentForKilometerFourth() throws Exception {
        Transport transport = new Transport();
        transport.setPaymentForKilometer(2236);
        assertEquals(transport.getPaymentForKilometer(), 2236);
    }

    @Test
    public void testGetTrailerTypeNameFourth() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(2);
        assertEquals(transport.getTrailerType().getEnumValue(), 2);
    }

    @Test
    public void testGetEnumValueFourth() throws Exception {
        Transport transport = new Transport();
        transport.setTrailerType(2);
        assertEquals(transport.getTrailerType().getEnumValue(), 2);
    }

    @Test
    public void testGetSetOrderIDFourth() throws Exception {
        Order order = new Order();
        order.setOrderID(43654123);
        assertEquals(order.getOrderID(), 43654123);
    }

    @Test
    public void getSetLoadIDFourth() throws Exception {
        Load load = new Load();
        load.setLoadID(3032);
        assertEquals(load.getLoadID(), 3032);
    }

    @Test
    public void getSetWeightFourth() throws Exception {
        Load load = new Load();
        load.setWeight(303);
        assertEquals(load.getWeight(), 303);
    }

    @Test
    public void getSetCostOfDeliveryFourth() throws Exception {
        Load load = new Load();
        load.setCostOfDelivery(219);
        assertEquals(load.getCostOfDelivery(), 219);
    }

    @Test
    public void getSetLoadTypeSecondFouth() throws Exception {
        Load load = new Load();
        load.setLoadType(Load.LoadType.ALIVE.getEnumValue());
        assertEquals(load.getLoadType().getEnumValue(), 4);
    }

    @Test
    public void getSetLoadDescriptionFourth() throws Exception {
        Load load = new Load();
        load.setLoadDescription("descript");
        assertEquals(load.getLoadDescription(), "descript");
    }
}