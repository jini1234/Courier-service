package com.everest.delivery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeliveryTest {

    @ParameterizedTest
    @CsvSource({"OFR001", "OFR002", "OFR003"})
    public void should_verify_packages_are_added_for_delivery(String offerId) {
        Delivery delivery = new Delivery(100, 1);
        delivery.addPackages("PKG1", 5, 5, offerId);
        assertEquals(1, delivery.getPackageList().size());
        assertEquals(100, delivery.getBaseDeliveryCost());
        assertEquals(1, delivery.getNoOfPackages());
    }

    @Test
    public void should_verify_cost_is_calculated_correctly() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1, 10, 60));

        Delivery delivery = new Delivery(100, 2);
        delivery.addPackages("PKG1", 15, 5, "OFR001");
        delivery.addPackages("PKG2", 50, 100, "OFR002");
        delivery.setVehicleList(vehicles);
        delivery.processDeliveries();

        assertEquals(0.0, delivery.getDeliveredPackagesList().get(0).getDiscount(), 0.0000001);
        assertEquals(1100.0, delivery.getDeliveredPackagesList().get(0).getCost(), 0.0000001);

        assertEquals(0.0, delivery.getDeliveredPackagesList().get(1).getDiscount(), 0.0000001);
        assertEquals(275.0, delivery.getDeliveredPackagesList().get(1).getCost(), 0.0000001);
    }

    @Test
    public void should_verify_max_no_of_package_are_delivered_first() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1, 10, 60));

        Delivery delivery = new Delivery(100, 4);
        delivery.addPackages("PKG1", 5, 5, "OFR001");
        delivery.addPackages("PKG2", 10, 10, "OFR002");
        delivery.addPackages("PKG3", 15, 20, "OFR008");
        delivery.addPackages("PKG4", 60, 100, "OFR003");

        delivery.setVehicleList(vehicles);
        delivery.processDeliveries();

        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG1", 0.5);
        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG2", 1.0);
        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG3", 2.0);
        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG4", 14.0);
        assertEquals(24.0, delivery.getCurrentTime());
    }

    @Test
    public void should_verify_max_package_are_delivered_first() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1, 10, 50));

        Delivery delivery = new Delivery(100, 4);
        delivery.addPackages("PKG1", 5, 5, "OFR001");
        delivery.addPackages("PKG2", 6, 30, "OFR002");
        delivery.addPackages("PKG3", 45, 40, "OFR002");
        delivery.addPackages("PKG4", 43, 15, "OFR003");

        delivery.setVehicleList(vehicles);
        delivery.processDeliveries();

        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG1", 0.5);
        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG2", 11.0);
        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG3", 4.0);
        checkDeliveryTimeForPackageId(delivery.getDeliveredPackagesList(), "PKG4", 9.5);
    }

    @Test
    public void should_verify_packages_with_higher_weight_than_max_vehicle_weight_is_not_delivered() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(1, 10, 50));

        Delivery delivery = new Delivery(100, 4);
        delivery.addPackages("PKG1", 55, 5, "OFR001");

        delivery.setVehicleList(vehicles);
        delivery.processDeliveries();

        assertTrue(delivery.getDeliveredPackagesList().isEmpty());

    }

    private void checkDeliveryTimeForPackageId(List<Package> deliveredPackages, String pkgId, double deliveryTime) {
        Package pack = deliveredPackages.stream().filter(pkg -> pkg.getId().equals(pkgId)).collect(Collectors.toList()).get(0);
        assertEquals(deliveryTime, pack.getDeliveryTimeHrs(), 0.0000001);
    }
}
