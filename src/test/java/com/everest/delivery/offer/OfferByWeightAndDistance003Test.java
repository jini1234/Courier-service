package com.everest.delivery.offer;

import com.everest.delivery.factory.OfferFactory;
import com.everest.delivery.packs.NormalPack;
import com.everest.delivery.packs.Pack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class OfferByWeightAndDistance003Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr003 = OfferFactory.getOfferById("OFR003");
        assertEquals("OFR003", ofr003.getId());
        assertEquals(5.0, ofr003.getDiscount(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"50,10", "250,150", "51,11", "249,149"})
    public void should_match_applicable_true(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR003");
        assertTrue(pack.isOfferApplicableForPackage());
    }

    @ParameterizedTest
    @CsvSource({"49,9", "251,151", "50,200", "49,5"})
    public void should_match_applicable_false(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR003");
        assertFalse(pack.isOfferApplicableForPackage());
    }
}
