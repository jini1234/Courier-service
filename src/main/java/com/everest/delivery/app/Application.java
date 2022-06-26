package com.everest.delivery.app;

import com.everest.delivery.service.NormalPackCourierService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Application {
    final static DecimalFormat df = new DecimalFormat("#0.0000");

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputData = bufferedReader.readLine().split(" ");
        int baseDeliveryCost = Integer.parseInt(inputData[0]);
        int noOfPackages = Integer.parseInt(inputData[1]);
        NormalPackCourierService normalPackCourierService = new NormalPackCourierService(baseDeliveryCost);
        String packageData;
        // Take all Packages input
        for (int i = 0; i < noOfPackages; i++) {
            packageData = bufferedReader.readLine();
            String[] packageArgs = packageData.split(" ");
            String offerId = packageArgs[3];
            int distance = Integer.parseInt(packageArgs[2]);
            int weight = Integer.parseInt(packageArgs[1]);
            String id = packageArgs[0];
            normalPackCourierService.addPackages(id, weight, distance, offerId);
        }
        // Take all Vehicles input
        String vehicleData = bufferedReader.readLine();
        String[] vehicleInfo = vehicleData.split(" ");
        int noOfVehicles = Integer.parseInt(vehicleInfo[0]);
        for (int i = 0; i < noOfVehicles; i++) {
            int maxSpeed = Integer.parseInt(vehicleInfo[1]);
            int maxWeight = Integer.parseInt(vehicleInfo[2]);
            normalPackCourierService.addVehicles(i + 1, maxSpeed, maxWeight);
        }
        // Process Deliveries
        normalPackCourierService.processDeliveries();
        // Print Output
        normalPackCourierService.getDeliveredPackagesList().forEach(pack -> System.out.println(pack.getId() + " " + df.format(pack.getDiscount()) + " " + df.format(pack.getCost()) + " " + df.format(pack.getDeliveryTimeHrs())));

        bufferedReader.close();
    }
}

