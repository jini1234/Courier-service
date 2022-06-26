package com.everest.delivery.packs;

import com.everest.delivery.factory.OfferFactory;
import com.everest.delivery.offer.Offer;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a package that can be delivered
 */
@Getter
public abstract class Pack {
    private final String id;
    private final int weightKgs, distanceKms;
    private final Offer offer;
    @Setter
    private double cost, discount, deliveryTimeHrs;

    public Pack(String id, int weightKgs, int distanceKms, String offerId) {
        this.id = id;
        this.weightKgs = weightKgs;
        this.distanceKms = distanceKms;
        this.offer = OfferFactory.getOfferById(offerId);
    }

    public abstract void calculateCost(int baseDeliveryCost);

    public boolean isOfferApplicableForPackage() {
        if (offer != null) {
            return this.offer.isApplicable(this);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pack aPack = (Pack) o;

        return id.equals(aPack.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
