package com.everest.delivery.offer;

public class Offer002 implements Offer {

    public static final int MIN_DISTANCE = 50;
    public static final int MAX_DISTANCE = 150;
    public static final int MIN_WEIGHT = 100;
    public static final int MAX_WEIGHT = 250;
    public static final double DISCOUNT_PERCENTAGE = 7.0;

    @Override
    public String getId() {
        return "OFR002";
    }

    @Override
    public boolean isApplicable(int distance, int weight) {
        return (distance >= MIN_DISTANCE && distance <= MAX_DISTANCE) && (weight >= MIN_WEIGHT && weight <= MAX_WEIGHT);
    }

    @Override
    public double getDiscountPercent() {
        return DISCOUNT_PERCENTAGE;
    }
}
