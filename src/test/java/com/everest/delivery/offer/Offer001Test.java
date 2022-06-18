package com.everest.delivery.offer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class Offer001Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr001 = new Offer001();
        assertEquals("OFR001", ofr001.getId());
        assertEquals(10.0, ofr001.getDiscountPercent(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"200,200", "199,199", "150,70", "10,100"})
    public void should_match_applicable_true(int distance, int weight) {
        Offer ofr001 = new Offer001();
        assertTrue(ofr001.isApplicable(distance, weight));
    }

    @ParameterizedTest
    @CsvSource({"201,200", "199,201", "150,69", "201,69"})
    public void should_match_applicable_false(int distance, int weight) {
        Offer ofr001 = new Offer001();
        assertFalse(ofr001.isApplicable(distance, weight));
    }

}
