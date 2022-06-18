package com.everest.delivery.offer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class Offer003Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr003 = new Offer003();
        assertEquals("OFR003", ofr003.getId());
        assertEquals(5.0, ofr003.getDiscountPercent(), 0.0);
    }
}
