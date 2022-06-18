package com.everest.delivery.offer;

public class Offer001 implements Offer{
    @Override
    public String getId() {
        return "OFR001";
    }

    @Override
    public boolean isApplicable(int distance, int weight) {
        return distance<200 && (weight>=70 && weight <=200);
    }

    @Override
    public double getDiscountPercent() {
        return 10.0;
    }
}
