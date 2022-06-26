package com.everest.delivery;

import com.everest.delivery.packs.Pack;
import com.everest.delivery.service.NormalPackCourierService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NormalPackDeliveryTest {

    @ParameterizedTest
    @CsvSource({"OFR001", "OFR002", "OFR003"})
    public void should_verify_packages_are_added_for_delivery(String offerId) {
        NormalPackCourierService normalPackCourierService = new NormalPackCourierService(100);
        normalPackCourierService.addPackages("PKG1", 5, 5, offerId);
        assertEquals(1, normalPackCourierService.getPackList().size());
        assertEquals(100, normalPackCourierService.getBaseDeliveryCost());
    }

    @Test
    public void should_verify_cost_is_calculated_correctly() {
        NormalPackCourierService normalPackCourierService = new NormalPackCourierService(100);
        normalPackCourierService.addPackages("PKG1", 15, 5, "OFR001");
        normalPackCourierService.addPackages("PKG2", 50, 100, "OFR002");
        normalPackCourierService.addVehicles(1, 10, 60);
        normalPackCourierService.processDeliveries();

        assertEquals(0.0, normalPackCourierService.getDeliveredPackagesList().get(0).getDiscount(), 0.0000001);
        assertEquals(1100.0, normalPackCourierService.getDeliveredPackagesList().get(0).getCost(), 0.0000001);

        assertEquals(0.0, normalPackCourierService.getDeliveredPackagesList().get(1).getDiscount(), 0.0000001);
        assertEquals(275.0, normalPackCourierService.getDeliveredPackagesList().get(1).getCost(), 0.0000001);
    }

    @Test
    public void should_verify_max_no_of_package_are_delivered_first() {
        NormalPackCourierService normalPackCourierService = new NormalPackCourierService(100);
        normalPackCourierService.addPackages("PKG1", 5, 5, "OFR001");
        normalPackCourierService.addPackages("PKG2", 10, 10, "OFR002");
        normalPackCourierService.addPackages("PKG3", 15, 20, "OFR008");
        normalPackCourierService.addPackages("PKG4", 60, 100, "OFR003");

        normalPackCourierService.addVehicles(1, 10, 60);
        normalPackCourierService.processDeliveries();

        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG1", 0.5);
        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG2", 1.0);
        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG3", 2.0);
        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG4", 14.0);
    }

    @Test
    public void should_verify_max_package_are_delivered_first() {
        NormalPackCourierService normalPackCourierService = new NormalPackCourierService(100);
        normalPackCourierService.addPackages("PKG1", 5, 5, "OFR001");
        normalPackCourierService.addPackages("PKG2", 6, 30, "OFR002");
        normalPackCourierService.addPackages("PKG3", 45, 40, "OFR002");
        normalPackCourierService.addPackages("PKG4", 43, 15, "OFR003");

        normalPackCourierService.addVehicles(1, 10, 50);
        normalPackCourierService.processDeliveries();

        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG1", 0.5);
        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG2", 11.0);
        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG3", 4.0);
        checkDeliveryTimeForPackageId(normalPackCourierService.getDeliveredPackagesList(), "PKG4", 9.5);
    }

    @Test
    public void should_verify_packages_with_higher_weight_than_max_vehicle_weight_is_not_delivered() {
        NormalPackCourierService normalPackCourierService = new NormalPackCourierService(100);
        normalPackCourierService.addPackages("PKG1", 55, 5, "OFR001");

        normalPackCourierService.addVehicles(1, 10, 50);
        normalPackCourierService.processDeliveries();

        assertTrue(normalPackCourierService.getDeliveredPackagesList().isEmpty());

    }

    private void checkDeliveryTimeForPackageId(List<Pack> deliveredPacks, String pkgId, double deliveryTime) {
        Pack pack = deliveredPacks.stream().filter(pkg -> pkg.getId().equals(pkgId)).collect(Collectors.toList()).get(0);
        assertEquals(deliveryTime, pack.getDeliveryTimeHrs(), 0.0000001);
    }
}
