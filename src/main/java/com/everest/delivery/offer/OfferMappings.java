package com.everest.delivery.offer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfferMappings {
    static List<Offer> allOffers;

    static {
        allOffers = new ArrayList<>();
        allOffers.add(new Offer001());
        allOffers.add(new Offer002());
        allOffers.add(new Offer003());
    }

    public static Offer findOfferById(String offerId) {
        List<Offer> filteredOffer = allOffers.stream().filter(offer -> offer.getId().equals(offerId)).collect(Collectors.toList());
        if (filteredOffer.size() > 0) {
            return filteredOffer.get(0);
        } else {
            throw new IllegalArgumentException("Invalid Offer Code: " + offerId);
        }
    }
}
