package com.pluralsight;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles){
        for (Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    private  void init (){
        try {
            DealershipFileManager dealershipFileManager = new DealershipFileManager();
            this.dealership = dealershipFileManager.getDealership();
        }catch (IOException  e){
            System.out.println("Error" + e.getMessage());
        }
    }
    public void display (){
        init();
        Scanner scanner = new Scanner(System.in);
        boolean exit = true;
        while (exit) {
            System.out.println("Choose from the following options:");
            System.out.print("1 - Find vehicles within a price range\n" +
                    "2 - Find vehicles by make / model\n" +
                    "3 - Find vehicles by year range\n" +
                    "4 - Find vehicles by color\n" +
                    "5 - Find vehicles by mileage range\n" +
                    "6 - Find vehicles by type (car, truck, SUV, van)\n" +
                    "7 - List ALL vehicles\n" +
                    "8 - Add a vehicle\n" +
                    "9 - Remove a vehicle\n" +
                    "99 - Quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 99:
                        exit = false;
                        break;
                default:
                    System.out.println("Wrong choice");
            }

        }

    }

    public void processGetByPriceRequest(){}
    public void processGetByMakeModelRequest(){}
    public void processGetByYearRequest(){}
    public void processGetByColorRequest(){}
    public void processGetByMileageRequest(){}
    public void processGetByVehicleTypeRequest(){}
    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }
    public void processAddVehicleRequest(){}
    public void processRemoveVehicleRequest(){}

}
