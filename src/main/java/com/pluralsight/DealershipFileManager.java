package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {

    public Dealership getDealership() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));
        String line;

        String firstLine = bufferedReader.readLine();
        String[] dealershipInfo = firstLine.split("\\|");
        Dealership dealership = new Dealership(dealershipInfo[0], dealershipInfo[1], dealershipInfo[2]);

        while ((line = bufferedReader.readLine()) != null) {
            String[] cells = line.split("\\|");
            Vehicle vehicle = new Vehicle(Integer.parseInt(cells[0]), Integer.parseInt(cells[1]),
                    cells[2], cells [3], cells[4], cells[5],Integer.parseInt(cells[6]), Double.parseDouble(cells[7]));
            dealership.addVehicle(vehicle);
        }
        bufferedReader.close();
        return dealership;
    }

    public Dealership saveDealership(){
        return null;
    }

}
