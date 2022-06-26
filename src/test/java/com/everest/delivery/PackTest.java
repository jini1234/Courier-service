package com.everest.delivery;

import com.everest.delivery.packs.NormalPack;
import com.everest.delivery.packs.Pack;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PackTest {

    @ParameterizedTest
    @CsvSource({
            "PKG1, 5, 5, 100, 175.0, 0.0",
            "PKG2, 70, 100, 100, 1170.0, 130.0",
            "PKG3, 250, 100, 50, 3050.0, 0.0",
    })
    public void should_verify_package_cost_with_Offer001(String id, int weight, int distance, int baseDeliveryCost, double packageCost, double discount) {
        Pack pack = new NormalPack(id, weight, distance, "OFR001");
        pack.calculateCost(baseDeliveryCost);
        assertEquals(packageCost, pack.getCost(), 0.000001);
        assertEquals(discount, pack.getDiscount(), 0.000001);
    }

    @ParameterizedTest
    @CsvSource({
            "PKG1, 100, 100, 100, 1488.0, 112.0",
            "PKG2, 9, 100, 100, 690.0, 0.0",
            "PKG3, 120, 100, 50, 1627.5, 122.5",
    })
    public void should_verify_package_cost_with_Offer002(String id, int weight, int distance, int baseDeliveryCost, double packageCost, double discount) {
        Pack pack = new NormalPack(id, weight, distance, "OFR002");
        pack.calculateCost(baseDeliveryCost);
        assertEquals(packageCost, pack.getCost(), 0.000001);
        assertEquals(discount, pack.getDiscount(), 0.000001);
    }

    @ParameterizedTest
    @CsvSource({
            "PKG1, 10, 100, 100, 665.0, 35.0",
            "PKG2, 9, 100, 100, 690.0, 0.0",
            "PKG3, 15, 50, 50, 427.5, 22.5",
    })
    public void should_verify_package_cost_with_Offer003(String id, int weight, int distance, int baseDeliveryCost, double packageCost, double discount) {
        Pack pack = new NormalPack(id, weight, distance, "OFR003");
        pack.calculateCost(baseDeliveryCost);
        assertEquals(packageCost, pack.getCost(), 0.000001);
        assertEquals(discount, pack.getDiscount(), 0.000001);
    }

    @ParameterizedTest
    @CsvSource({
            "PKG1, 10, 100, 100, 700.0, 0.0",
            "PKG2, 9, 100, 100, 690.0, 0.0",
            "PKG3, 15, 50, 50, 450.0, 0.0",
    })
    public void should_verify_package_cost_with_No_Offer(String id, int weight, int distance, int baseDeliveryCost, double packageCost, double discount) {
        Pack pack = new NormalPack(id, weight, distance, "");
        pack.calculateCost(baseDeliveryCost);
        assertEquals(packageCost, pack.getCost(), 0.000001);
        assertEquals(discount, pack.getDiscount(), 0.000001);
    }
}
