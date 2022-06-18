package com.everest.delivery.offer;

public class Offer002 implements Offer {
    @Override
    public String getId() {
        return "OFR002";
    }

    @Override
    public boolean isApplicable(int distance, int weight) {
        return (distance >= 50 && distance <= 150) && (weight >= 100 && weight <= 250);
    }

    @Override
    public double getDiscountPercent() {
        return 7.0;
    }
}
