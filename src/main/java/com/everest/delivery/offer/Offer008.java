package com.everest.delivery.offer;

public class Offer008 implements Offer {

    @Override
    public String getId() {
        return "OFR008";
    }

    @Override
    public boolean isApplicable(int distance, int weight) {
        return false;
    }

    @Override
    public double getDiscountPercent() {
        return 0.0;
    }
}
