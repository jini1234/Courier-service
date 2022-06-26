package com.everest.delivery.service;

import com.everest.delivery.packs.Pack;
import com.everest.delivery.vehicle.Vehicle;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class CourierService {
    private final int baseDeliveryCost;
    private final List<Pack> packList;
    private final List<Vehicle> vehicleList;

    public CourierService(int baseDeliveryCost) {
        this.baseDeliveryCost = baseDeliveryCost;
        this.packList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
    }

    public void processDeliveries() {
        calculateCostForAllPacks();
        calculateDeliveryTimeForAllPacks();
    }

    protected abstract void calculateDeliveryTimeForAllPacks();

    // maybe overridden for different implementation
    protected void calculateCostForAllPacks() {
        this.packList.forEach(pack -> pack.calculateCost(this.baseDeliveryCost));
    }
}
