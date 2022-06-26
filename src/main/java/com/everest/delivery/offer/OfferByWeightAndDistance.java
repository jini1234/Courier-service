package com.everest.delivery.offer;

import com.everest.delivery.packs.Pack;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferByWeightAndDistance extends Offer {
    private String weightRange;
    private String distanceRange;

    public boolean isApplicable(Pack pack) {
        return (pack.getDistanceKms() >= getMinApplicableDistance() && pack.getDistanceKms() <= getMaxApplicableDistance())
                && (pack.getWeightKgs() >= getMinApplicableWeight() && pack.getWeightKgs() <= getMaxApplicableWeight());
    }

    private int getMinApplicableDistance() {
        return Integer.parseInt(getDistanceRange().split("-")[0]);
    }

    private int getMaxApplicableDistance() {
        return Integer.parseInt(getDistanceRange().split("-")[1]);
    }

    private int getMinApplicableWeight() {
        return Integer.parseInt(getWeightRange().split("-")[0]);
    }

    private int getMaxApplicableWeight() {
        return Integer.parseInt(getWeightRange().split("-")[1]);
    }
}
