package com.everest.delivery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = bufferedReader.readLine().split(" ");
        Delivery delivery = new Delivery(Integer.parseInt(firstLine[0]), Integer.parseInt(firstLine[1]));
        String line;
        // Take all Packages input
        for (int i = 0; i < delivery.getNoOfPackages(); i++) {
            line = bufferedReader.readLine();
            String[] packageArgs = line.split(" ");
            delivery.addPackages(packageArgs[0], Integer.parseInt(packageArgs[1]), Integer.parseInt(packageArgs[2]), packageArgs[3]);
        }
        // Process Deliveries
        delivery.processDeliveries();
        // Print Output
        delivery.getPackageList().forEach(pack -> System.out.println(pack.getId() + " " + pack.getDiscount() + " " + pack.getCost()));

        bufferedReader.close();
    }
}