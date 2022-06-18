package com.everest.delivery.offer;

public class Offer001 implements Offer {
    public static final int MIN_DISTANCE = 200;
    public static final int MAX_WEIGHT = 200;
    public static final int MIN_WEIGHT = 70;
    public static final double DISCOUNT_PERCENTAGE = 10.0;

    @Override
    public String getId() {
        return "OFR001";
    }

    @Override
    public boolean isApplicable(int distance, int weight) {
        return distance <= MIN_DISTANCE && (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT);
    }

    @Override
    public double getDiscountPercent() {
        return DISCOUNT_PERCENTAGE;
    }
}
