package com.everest.delivery.offer;

import com.everest.delivery.factory.OfferFactory;
import com.everest.delivery.packs.NormalPack;
import com.everest.delivery.packs.Pack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class OfferByWeightAndDistance002Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr002 = OfferFactory.getOfferById("OFR002");
        assertEquals("OFR002", ofr002.getId());
        assertEquals(7.0, ofr002.getDiscount(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"50,100", "150,250", "51,101", "149,249"})
    public void should_match_applicable_true(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR002");
        assertTrue(pack.isOfferApplicableForPackage());
    }

    @ParameterizedTest
    @CsvSource({"49,99", "151,251", "200,50", "30,300"})
    public void should_match_applicable_false(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR002");
        assertFalse(pack.isOfferApplicableForPackage());
    }
}
