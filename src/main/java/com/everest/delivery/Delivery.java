package com.everest.delivery;

import com.everest.delivery.offer.OfferMappings;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Delivery {
    private final int baseDeliveryCost;
    private final int noOfPackages;
    private final List<Package> packageList;

    public Delivery(int baseDeliveryCost, int noOfPackages) {
        this.baseDeliveryCost = baseDeliveryCost;
        this.noOfPackages = noOfPackages;
        this.packageList = new ArrayList<>();
    }

    public void addPackages(String id, int weight, int distance, String offerId) throws IllegalArgumentException {
        this.packageList.add(new Package(id, weight, distance, OfferMappings.findOfferById(offerId)));
    }

    public void processDeliveries() {
        this.packageList.forEach(pack -> pack.calculateCost(this.baseDeliveryCost));
    }


}
