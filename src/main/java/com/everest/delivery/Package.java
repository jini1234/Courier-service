package com.everest.delivery;

import com.everest.delivery.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Package {
    private String id;
    private int weightKgs, distanceKms;
    @Setter
    private double cost, discount, deliveryTimeHrs;
    private Offer offer;

    public Package(String id, int weightKgs, int distanceKms, Offer offer) {
        this.id = id;
        this.weightKgs = weightKgs;
        this.distanceKms = distanceKms;
        this.offer = offer;
    }

    public void calculateCost(int baseDeliveryCost) {
        double cost = baseDeliveryCost + (this.weightKgs * 10) + (this.distanceKms * 5);
        if (isOfferApplicableForPackage()) {
            double discount = cost * (offer.getDiscountPercent() / 100);
            cost -= discount;
            this.setDiscount(discount);
        }
        this.setCost(cost);

    }

    private boolean isOfferApplicableForPackage() {
        return this.offer.isApplicable(this.distanceKms, this.weightKgs);
    }
}
