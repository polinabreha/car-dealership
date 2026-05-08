package com.pluralsight;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    static final Scanner scanner = new Scanner(System.in);

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
    public void display () throws IOException {
        init();
        boolean exit = true;
        greeting();
        while (exit) {
            System.out.println("Choose from the following options:");
            System.out.println("1 - Find vehicles within a price range\n" +
                    "2 - Find vehicles by make / model\n" +
                    "3 - Find vehicles by year range\n" +
                    "4 - Find vehicles by color\n" +
                    "5 - Find vehicles by mileage range\n" +
                    "6 - Find vehicles by type (car, truck, SUV, van)\n" +
                    "7 - List ALL vehicles\n" +
                    "8 - Add a vehicle\n" +
                    "9 - Remove a vehicle\n" +
                    "99 - Quit");
            System.out.print("Enter your choice: ");
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
                        end();
                        break;
                default:
                    System.out.println("Wrong choice");
            }

        }

    }

    public void processGetByPriceRequest(){
        System.out.print("Please enter the minimum price: ");
        double minPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Please enter the maximum price: ");
        double maxPrice = scanner.nextDouble();
        scanner.nextLine();
        ArrayList<Vehicle> results = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(results);
    }
    public void processGetByMakeModelRequest(){
        System.out.print("Please enter the make of the model: ");
        String makeModel = scanner.nextLine();
        System.out.print("Please enter the model: ");
        String model = scanner.nextLine();
        ArrayList <Vehicle> results = dealership.getVehiclesByMakeModel(makeModel, model);
        displayVehicles(results);
    }
    public void processGetByYearRequest(){
        System.out.print("Please enter the minimum year range: ");
        int minYearRange = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter the maximum year range: ");
        int maxYearRange = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Vehicle> result = dealership.getVehiclesByYear(minYearRange, maxYearRange);
        displayVehicles(result);
    }
    public void processGetByColorRequest(){
        System.out.print("Please enter the color: ");
        String color = scanner.nextLine();
        ArrayList <Vehicle> results = dealership.getVehiclesByColor(color);
        displayVehicles(results);
    }
    public void processGetByMileageRequest(){
        System.out.print("Please enter the minimum mileage range: ");
        int minMileageRange = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Please enter the maximum mileage range: ");
        int maxMileageRange = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Vehicle> result = dealership.getVehiclesByMileage(minMileageRange, maxMileageRange);
        displayVehicles(result);
    }
    public void processGetByVehicleTypeRequest(){
        System.out.print("Please enter the vehicle type: ");
        String vehicleType = scanner.nextLine();
        ArrayList<Vehicle> result =  dealership.getVehiclesByType(vehicleType);
        displayVehicles(result);
    }
    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }
    public void processAddVehicleRequest() {
        try {
            System.out.print("Enter vehicle vin: ");
            int vin = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the vehicle year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Please enter the vehicle make: ");
            String make = scanner.nextLine();
            System.out.print("Please enter the vehicle model: ");
            String model = scanner.nextLine();
            System.out.print("Enter the vehicle type: ");
            String vehicleType = scanner.nextLine();
            System.out.print("Please enter the vehicle color: ");
            String color = scanner.nextLine();
            System.out.print("Please enter the vehicle odometer: ");
            int odometer = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Please enter the vehicle price ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            DealershipFileManager fileManager = new DealershipFileManager();
            dealership.addVehicle(newVehicle);
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle added successfully");
        }catch (IOException e){
            System.out.println("Error" + e.getMessage());
        }

    }
    public void processRemoveVehicleRequest() {
        try {
            System.out.print("Please enter the vehicle vin: ");
            int vin = scanner.nextInt();
            scanner.nextLine();
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vin) {
                    dealership.removeVehicle(vehicle);
                    break;
                }
            }
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);
            System.out.println("Successfully removed vehicle");
        }catch (IOException e){
            System.out.println("Error" + e.getMessage());
        }
    }

    public static void greeting(){
        System.out.println(Design.PURPLE_BACKGROUND +"|---------------------------------------------------------|" + Design.RESET);
        System.out.println(Design.PURPLE_BACKGROUND +"|        Welcome to Dealership Management System          |" + Design.RESET);
        System.out.println(Design.PURPLE_BACKGROUND +"|---------------------------------------------------------|" + Design.RESET);
    }

    public static void end(){
        System.out.println(Design.PURPLE_BACKGROUND +"|---------------------------------------------------------|" + Design.RESET);
        System.out.println(Design.PURPLE_BACKGROUND +"|           Thank You for visiting us today               |" + Design.RESET);
        System.out.println(Design.PURPLE_BACKGROUND +"|---------------------------------------------------------|" + Design.RESET);
    }

}
