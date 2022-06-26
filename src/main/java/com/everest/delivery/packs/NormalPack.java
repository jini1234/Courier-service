package com.everest.delivery.packs;

import lombok.Getter;

/**
 * This class represents normal Package that can be delivered,
 * for our current requirement
 */
@Getter
public class NormalPack extends Pack {
    public static final int PER_KG_COST = 10;
    public static final int PER_KM_COST = 5;

    public NormalPack(String id, int weightKgs, int distanceKms, String offerId) {
        super(id, weightKgs, distanceKms, offerId);
    }

    public void calculateCost(int baseDeliveryCost) {
        double cost = baseDeliveryCost + (this.getWeightKgs() * PER_KG_COST) + (this.getDistanceKms() * PER_KM_COST);
        if (this.isOfferApplicableForPackage()) {
            double discount = cost * (this.getOffer().getDiscount() / 100);
            cost -= discount;
            this.setDiscount(discount);
        }
        this.setCost(cost);

    }
}
