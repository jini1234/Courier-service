package com.everest.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Delivery {
    private final int baseDeliveryCost;
    private final int noOfPackages;
    private List<Package> packageList;


}
