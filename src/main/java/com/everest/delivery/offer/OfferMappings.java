package com.everest.delivery.offer;

import java.util.HashMap;
import java.util.Optional;

public class OfferMappings {
    static HashMap<String, Offer> allOffers;

    static {
        allOffers = new HashMap<>();
        Offer offer = new Offer001();
        allOffers.put(offer.getId(), offer);
        offer = new Offer002();
        allOffers.put(offer.getId(), offer);
        offer = new Offer003();
        allOffers.put(offer.getId(), offer);
        offer = new Offer008();
        allOffers.put(offer.getId(), offer);
    }

    public static Optional<Offer> findOfferById(String offerId) {
        return Optional.of(allOffers.getOrDefault(offerId, null));
    }
}
