package classes.web;

import classes.web.entity.Load;
import classes.web.entity.Order;
import classes.web.entity.Transport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class Tests {

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
}