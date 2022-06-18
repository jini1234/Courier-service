package com.everest.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Vehicle {
    private final int id, maxSpeed, maxWeight;
    private final double returningTime;
}
