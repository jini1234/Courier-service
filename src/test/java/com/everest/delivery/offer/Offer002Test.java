package com.everest.delivery.offer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class Offer002Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr002 = new Offer002();
        assertEquals("OFR002", ofr002.getId());
        assertEquals(7.0, ofr002.getDiscountPercent(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"50,100", "150,250", "51,101", "149,249"})
    public void should_match_applicable_true(int distance, int weight) {
        Offer ofr002 = new Offer002();
        assertTrue(ofr002.isApplicable(distance, weight));
    }

    @ParameterizedTest
    @CsvSource({"49,99", "151,251", "200,50", "30,300"})
    public void should_match_applicable_false(int distance, int weight) {
        Offer ofr002 = new Offer002();
        assertFalse(ofr002.isApplicable(distance, weight));
    }
}
