package com.everest.delivery.offer;

public class Offer003 implements Offer {
    @Override
    public String getId() {
        return "OFR003";
    }

    @Override
    public boolean isApplicable(int distance, int weight) {
        return (distance >= 50 && distance <= 250) && (weight >= 10 && weight <= 150);
    }

    @Override
    public double getDiscountPercent() {
        return 5.0;
    }
}
