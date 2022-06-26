package com.everest.delivery.service;

import com.everest.delivery.packs.NormalPack;
import com.everest.delivery.packs.Pack;
import com.everest.delivery.vehicle.Vehicle;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class NormalPackCourierService extends CourierService {
    private final List<Pack> deliveredPackagesList;

    public NormalPackCourierService(int baseDeliveryCost) {
        super(baseDeliveryCost);
        this.deliveredPackagesList = new ArrayList<>();
    }

    public void addPackages(String id, int weight, int distance, String offerId) {
        this.getPackList().add(new NormalPack(id, weight, distance, offerId));
    }

    public void addVehicles(int id, int maxSpeed, int maxWeight) {
        this.getVehicleList().add(new Vehicle(id, maxSpeed, maxWeight));
    }

    @Override
    protected void calculateDeliveryTimeForAllPacks() {
        double currentTime = 0.0;
        int totalPacksToDeliver = this.getPackList().size();
        while (deliveredPackagesList.size() < totalPacksToDeliver) {
            Vehicle availableVehicle = getAvailableVehicle(currentTime);
            Set<Pack> deliverablePackagesSet = getDeliverablePackagesForWeight(availableVehicle.getMaxWeight());
            if (deliverablePackagesSet == null) {
                break;
            }
            updateDeliveryTimeForDeliveredPackages(currentTime, availableVehicle, deliverablePackagesSet);
            updateReturningTimeForVehicle(currentTime, availableVehicle, deliverablePackagesSet);
            currentTime = getMinReturnTime();
            deliveredPackagesList.addAll(deliverablePackagesSet);
            this.getPackList().removeAll(deliverablePackagesSet);
        }
    }

    private Vehicle getAvailableVehicle(double currentTime) {
        return this.getVehicleList().stream().filter(v -> currentTime == v.getReturningTime()).collect(Collectors.toList()).get(0);
    }

    private void updateReturningTimeForVehicle(double currentTime, Vehicle availableVehicle, Set<Pack> deliverablePackagesSet) {
        int maxDistance = getMaxDistance(deliverablePackagesSet);
        double roundTripTime = 2 * calculateDeliveryTime(availableVehicle, maxDistance);
        availableVehicle.setReturningTime(currentTime + roundTripTime);
    }

    private void updateDeliveryTimeForDeliveredPackages(double currentTime, Vehicle availableVehicle, Set<Pack> deliverablePackagesSet) {
        for (Pack deliverablePack : deliverablePackagesSet) {
            deliverablePack.setDeliveryTimeHrs(currentTime + calculateDeliveryTime(availableVehicle, deliverablePack.getDistanceKms()));
        }
    }

    private double calculateDeliveryTime(Vehicle availableVehicle, int maxDistance) {
        return maxDistance / (double) availableVehicle.getMaxSpeed();
    }

    private double getMinReturnTime() {
        return this.getVehicleList().stream().map(Vehicle::getReturningTime).min(Double::compareTo).orElse(0.0);
    }

    private int getMaxDistance(Set<Pack> deliverablePacks) {
        return deliverablePacks.stream().map(Pack::getDistanceKms).max(Integer::compareTo).orElse(0);
    }

    private Set<Pack> getDeliverablePackagesForWeight(int maxWeight) {
        int maxPackageCount = getMaxPackagesCountForWeight(maxWeight);
        return getBestPackagesForWeight(maxPackageCount, maxWeight);
    }

    private int getMaxPackagesCountForWeight(int maxWeight) {
        this.getPackList().sort(Comparator.comparingInt(Pack::getWeightKgs));
        int maxLength = 0;
        int currWeightSum = 0;
        for (Pack pack : this.getPackList()) {
            if (isUnderWeightLimit(maxWeight, currWeightSum, pack)) {
                currWeightSum += pack.getWeightKgs();
                maxLength++;
            }
        }
        return maxLength;
    }

    private boolean isUnderWeightLimit(int maxWeight, int currWeightSum, Pack pack) {
        return (currWeightSum + pack.getWeightKgs()) <= maxWeight;
    }

    private Set<Pack> getBestPackagesForWeight(int maxLength, int maxWeight) {
        Set<Set<Pack>> allPackageCombinations = Sets.combinations(new HashSet<>(this.getPackList()), maxLength);
        int maxWeightSet = 0;
        Set<Pack> maxWeightSumPackSet = null;
        for (Set<Pack> currPackSet : allPackageCombinations) {
            int currWeightSum = getCurrentPackSetWeightSum(currPackSet);
            if (currWeightSum > maxWeightSet && currWeightSum <= maxWeight) {
                maxWeightSumPackSet = currPackSet;
                maxWeightSet = currWeightSum;
            }
        }
        return maxWeightSumPackSet;
    }

    private int getCurrentPackSetWeightSum(Set<Pack> currPackSet) {
        int currWeightSum = 0;
        for (Pack pack : currPackSet) {
            currWeightSum += pack.getWeightKgs();
        }
        return currWeightSum;
    }


}
