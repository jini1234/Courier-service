package com.everest.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Vehicle {
    private final int id, maxSpeed, maxWeight;
    @Setter
    private double returningTime;

    public Vehicle(int id, int maxSpeed, int maxWeight) {
        this.id = id;
        this.maxSpeed = maxSpeed;
        this.maxWeight = maxWeight;
    }
}
