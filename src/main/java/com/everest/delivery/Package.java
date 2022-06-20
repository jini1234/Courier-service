package com.everest.delivery;

import com.everest.delivery.offer.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
public class Package {
    public static final int PER_KG_COST = 10;
    public static final int PER_KM_COST = 5;
    private final String id;
    private final int weightKgs, distanceKms;
    @Setter
    private double cost, discount, deliveryTimeHrs;
    private final Offer offer;

    public Package(String id, int weightKgs, int distanceKms, Optional<Offer> offer) {
        this.id = id;
        this.weightKgs = weightKgs;
        this.distanceKms = distanceKms;
        this.offer = offer.orElse(null);
    }

    public void calculateCost(int baseDeliveryCost) {
        double cost = baseDeliveryCost + (this.weightKgs * PER_KG_COST) + (this.distanceKms * PER_KM_COST);
        if (isOfferApplicableForPackage()) {
            double discount = cost * (this.getOffer().getDiscountPercent() / 100);
            cost -= discount;
            this.setDiscount(discount);
        }
        this.setCost(cost);

    }

    private boolean isOfferApplicableForPackage() {
        if (offer != null) {
            return this.offer.isApplicable(this.distanceKms, this.weightKgs);
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Package aPackage = (Package) o;

        return id.equals(aPackage.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
