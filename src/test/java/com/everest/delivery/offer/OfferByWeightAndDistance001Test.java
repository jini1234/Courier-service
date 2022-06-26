package com.everest.delivery.offer;

import com.everest.delivery.factory.OfferFactory;
import com.everest.delivery.packs.NormalPack;
import com.everest.delivery.packs.Pack;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


public class OfferByWeightAndDistance001Test {

    @Test
    public void should_check_offerId_and_discount() {
        Offer ofr001 = OfferFactory.getOfferById("OFR001");
        assertEquals("OFR001", ofr001.getId());
        assertEquals(10.0, ofr001.getDiscount(), 0.0);
    }

    @ParameterizedTest
    @CsvSource({"200,200", "199,199", "150,70", "10,100"})
    public void should_match_applicable_true(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR001");
        assertTrue(pack.isOfferApplicableForPackage());
    }

    @ParameterizedTest
    @CsvSource({"201,200", "199,201", "150,69", "201,69"})
    public void should_match_applicable_false(int distance, int weight) {
        Pack pack = new NormalPack("PKG1", weight, distance, "OFR001");
        assertFalse(pack.isOfferApplicableForPackage());
    }

}
