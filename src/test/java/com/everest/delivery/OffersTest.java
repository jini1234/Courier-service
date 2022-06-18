package com.everest.delivery;

import com.everest.delivery.offer.Offer;
import com.everest.delivery.offer.Offer001;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class OffersTest {

    @Test
    public void should_check_offerId_and_discount(){
        Offer ofr001 = new Offer001();
        assertEquals("OFR001", ofr001.getId());
        assertEquals(10.0, ofr001.getDiscountPercent(), 0.0);
    }

}
