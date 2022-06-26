package com.everest.delivery.offer;

import com.everest.delivery.factory.OfferFactory;
import com.everest.delivery.packs.NormalPack;
import com.everest.delivery.packs.Pack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class OfferByWeight004Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr004 = OfferFactory.getOfferById("OFR004");
        assertEquals("OFR004", ofr004.getId());
        assertEquals(6.0, ofr004.getDiscount(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"200,90", "199,91", "150,149", "10,150"})
    public void should_match_applicable_true(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR004");
        assertTrue(pack.isOfferApplicableForPackage());
    }

    @ParameterizedTest
    @CsvSource({"201,89", "199,50", "150,151", "201,200"})
    public void should_match_applicable_false(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR004");
        assertFalse(pack.isOfferApplicableForPackage());
    }

}
