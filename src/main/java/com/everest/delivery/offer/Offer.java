package com.everest.delivery.offer;

public interface Offer {
    String getId();
    boolean isApplicable(int distance, int weight);
    double getDiscountPercent();
}
