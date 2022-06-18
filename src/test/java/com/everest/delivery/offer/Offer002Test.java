package com.everest.delivery.offer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Offer002Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr002 = new Offer002();
        assertEquals("OFR002", ofr002.getId());
        assertEquals(7.0, ofr002.getDiscountPercent(), 0.0);
    }
}
