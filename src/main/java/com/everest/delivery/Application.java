package com.everest.delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputData = bufferedReader.readLine().split(" ");
        int baseDeliveryCost = Integer.parseInt(inputData[0]);
        int noOfPackages = Integer.parseInt(inputData[1]);
        Delivery delivery = new Delivery(baseDeliveryCost, noOfPackages);
        String packageData;
        // Take all Packages input
        for (int i = 0; i < delivery.getNoOfPackages(); i++) {
            packageData = bufferedReader.readLine();
            String[] packageArgs = packageData.split(" ");
            String offerId = packageArgs[3];
            int distance = Integer.parseInt(packageArgs[2]);
            int weight = Integer.parseInt(packageArgs[1]);
            String id = packageArgs[0];
            delivery.addPackages(id, weight, distance, offerId);
        }
        String vehicleData = bufferedReader.readLine();
        String[] vehicleInfo = vehicleData.split(" ");
        int noOfVehicles = Integer.parseInt(vehicleInfo[0]);
        for (int i = 0; i < noOfVehicles; i++) {
            int maxSpeed = Integer.parseInt(vehicleInfo[1]);
            int maxWeight = Integer.parseInt(vehicleInfo[2]);
            delivery.getVehicleList().add(new Vehicle(i + 1, maxSpeed, maxWeight));
        }
        // Process Deliveries
        delivery.processDeliveries();
        // Print Output
        delivery.getDeliveredPackagesList().forEach(pack -> System.out.println(pack.getId() + " " + pack.getDiscount() + " " + pack.getCost() + " " + pack.getDeliveryTimeHrs()));

        bufferedReader.close();
    }
}