package com.everest.delivery.offer;

public class Offer003 implements Offer {

    public static final int MIN_DISTANCE = 50;
    public static final int MAX_DISTANCE = 250;
    public static final int MIN_WEIGHT = 10;
    public static final int MAX_WEIGHT = 150;
    public static final double DISCOUNT_PERCENTAGE = 5.0;

    @Override
    public String getId() {
        return "OFR003";
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
