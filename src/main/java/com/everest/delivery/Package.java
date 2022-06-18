package com.everest.delivery;

import com.everest.delivery.offer.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Package {
    private int id, weightKgs, distanceKms;
    private Offer offer;

    public double calculateCost(int baseDeliveryCost){
        return 0.0;
    }

    private boolean isOfferApplicable(){
        return false;
    }
}
