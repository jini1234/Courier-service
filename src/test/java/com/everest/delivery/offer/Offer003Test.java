package com.everest.delivery.offer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class Offer003Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr003 = new Offer003();
        assertEquals("OFR003", ofr003.getId());
        assertEquals(5.0, ofr003.getDiscountPercent(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"50,10", "250,150", "51,11", "249,149"})
    public void should_match_applicable_true(int distance, int weight) {
        Offer ofr003 = new Offer003();
        assertTrue(ofr003.isApplicable(distance, weight));
    }

    @ParameterizedTest
    @CsvSource({"49,9", "251,151", "50,200", "49,5"})
    public void should_match_applicable_false(int distance, int weight) {
        Offer ofr003 = new Offer003();
        assertFalse(ofr003.isApplicable(distance, weight));
    }
}
