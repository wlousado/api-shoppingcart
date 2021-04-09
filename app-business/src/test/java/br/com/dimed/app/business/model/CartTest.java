package br.com.dimed.app.business.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CartTest {
    @Test
    public void testCalcTot(){
        Item i1 = new Item();
        i1.setPrice(10.6);
        Item i2 = new Item();
        i2.setPrice(3.2);

        Cart cart = new Cart();
        cart.getItem().put(i1, 2);
        cart.getItem().put(i2, 1);

        assertEquals((Double)24.4 , cart.CalculateTotal());
    }
}
