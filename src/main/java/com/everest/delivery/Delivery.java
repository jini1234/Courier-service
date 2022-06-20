package com.everest.delivery;

import com.everest.delivery.offer.OfferMappings;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Delivery {
    private final int baseDeliveryCost;
    private final int noOfPackages;
    private final List<Package> packageList;
    private final List<Package> deliveredPackagesList;
    @Setter
    private List<Vehicle> vehicleList;
    private double currentTime;

    public Delivery(int baseDeliveryCost, int noOfPackages) {
        this.baseDeliveryCost = baseDeliveryCost;
        this.noOfPackages = noOfPackages;
        this.packageList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
        this.deliveredPackagesList = new ArrayList<>();
        this.currentTime = 0.0;
    }

    public void addPackages(String id, int weight, int distance, String offerId) throws IllegalArgumentException {
        this.packageList.add(new Package(id, weight, distance, OfferMappings.findOfferById(offerId)));
    }

    public void processDeliveries() {
        this.packageList.forEach(pack -> pack.calculateCost(this.baseDeliveryCost));
        while (deliveredPackagesList.size() < noOfPackages) {
            Vehicle availableVehicle = this.getVehicleList().stream().filter(v -> this.currentTime == v.getReturningTime()).collect(Collectors.toList()).get(0);
            Set<Package> deliverablePackagesSet = getDeliverablePackagesForWeight(availableVehicle.getMaxWeight());
            if (deliverablePackagesSet == null) {
                break;
            }
            updateDeliveryTimeForDeliveredPackages(availableVehicle, deliverablePackagesSet);
            updateReturningTimeForVehicle(availableVehicle, deliverablePackagesSet);
            currentTime = getMinReturnTime();
            deliveredPackagesList.addAll(deliverablePackagesSet);
            packageList.removeAll(deliverablePackagesSet);
        }
    }

    private void updateReturningTimeForVehicle(Vehicle availableVehicle, Set<Package> deliverablePackagesSet) {
        int maxDistance = getMaxDistance(deliverablePackagesSet);
        availableVehicle.setReturningTime(currentTime + 2 * calculateDeliveryTime(availableVehicle, maxDistance));
    }

    private void updateDeliveryTimeForDeliveredPackages(Vehicle availableVehicle, Set<Package> deliverablePackagesSet) {
        for (Package deliverablePackage : deliverablePackagesSet) {
            deliverablePackage.setDeliveryTimeHrs(currentTime + calculateDeliveryTime(availableVehicle, deliverablePackage.getDistanceKms()));
        }
    }

    private double calculateDeliveryTime(Vehicle availableVehicle, int maxDistance) {
        return maxDistance / (double) availableVehicle.getMaxSpeed();
    }

    private double getMinReturnTime() {
        return this.vehicleList.stream().map(Vehicle::getReturningTime).min(Double::compareTo).orElse(0.0);
    }

    private int getMaxDistance(Set<Package> deliverablePackages) {
        return deliverablePackages.stream().map(Package::getDistanceKms).max(Integer::compareTo).orElse(0);
    }

    private Set<Package> getDeliverablePackagesForWeight(int maxWeight) {
        this.packageList.sort(Comparator.comparingInt(Package::getWeightKgs));
        int maxLength = 0;
        int currWeightSum = 0;
        for (Package pack : this.packageList) {
            if (isWeightLimitExceed(maxWeight, currWeightSum, pack)) {
                currWeightSum += pack.getWeightKgs();
                maxLength++;
            }
        }
        return getBestPackagesForWeight(maxLength, maxWeight);
    }

    private boolean isWeightLimitExceed(int maxWeight, int currWeightSum, Package pack) {
        return (currWeightSum + pack.getWeightKgs()) <= maxWeight;
    }

    private Set<Package> getBestPackagesForWeight(int maxLength, int maxWeight) {
        Set<Set<Package>> allPackageCombinations = Sets.combinations(new HashSet<>(this.packageList), maxLength);
        int maxWeightSet = 0;
        Set<Package> maxWeightSumPackageSet = null;
        for (Set<Package> currPackageSet : allPackageCombinations) {
            int currWeightSum = 0;
            currWeightSum = getCurrWeightSum(currPackageSet, currWeightSum);
            if (currWeightSum > maxWeightSet && currWeightSum <= maxWeight) {
                maxWeightSumPackageSet = currPackageSet;
                maxWeightSet = currWeightSum;
            }
        }
        return maxWeightSumPackageSet;
    }

    private int getCurrWeightSum(Set<Package> currPackageSet, int currWeightSum) {
        for (Package pack : currPackageSet) {
            currWeightSum += pack.getWeightKgs();
        }
        return currWeightSum;
    }


}
