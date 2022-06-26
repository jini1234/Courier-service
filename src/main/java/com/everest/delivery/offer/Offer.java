package com.everest.delivery.offer;

import com.everest.delivery.packs.Pack;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Offer {
    private String id;
    private double discount;

    public abstract boolean isApplicable(Pack pack);
}
