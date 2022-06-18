package com.everest.delivery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryTest {

    @ParameterizedTest
    @CsvSource({"OFR", "NotValidOffer", "Ofer"})
    public void should_verify_exception_for_invalid_offerId_in_delivery(String offerId) {
        Delivery delivery = new Delivery(100, 3);
        Exception ex = assertThrows(IllegalArgumentException.class, () -> delivery.addPackages("PKG1", 5, 5, offerId));
        assertEquals("Invalid Offer Code: " + offerId, ex.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"OFR001", "OFR002", "OFR003"})
    public void should_verify_no_exception_for_valid_offerId_in_delivery(String offerId) {
        Delivery delivery = new Delivery(100, 3);
        assertDoesNotThrow(() -> delivery.addPackages("PKG1", 5, 5, offerId));
    }

    @Test
    public void should_verify_correct_delivery_processing() {
        Delivery delivery = new Delivery(100, 3);
        delivery.addPackages("PKG1", 5, 5, "OFR001");
        delivery.processDeliveries();
        assertEquals(0.0, delivery.getPackageList().get(0).getDiscount(), 0.0000001);
        assertEquals(175.0, delivery.getPackageList().get(0).getCost(), 0.0000001);
    }


}
