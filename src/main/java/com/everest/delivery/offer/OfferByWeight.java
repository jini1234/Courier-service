package com.everest.delivery.offer;

import com.everest.delivery.packs.Pack;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferByWeight extends Offer {
    private String weightRange;

    @Override
    public boolean isApplicable(Pack pack) {
        return (pack.getWeightKgs() >= getMinApplicableWeight() && pack.getWeightKgs() <= getMaxApplicableWeight());
    }

    private int getMinApplicableWeight() {
        return Integer.parseInt(getWeightRange().split("-")[0]);
    }

    private int getMaxApplicableWeight() {
        return Integer.parseInt(getWeightRange().split("-")[1]);
    }
}
