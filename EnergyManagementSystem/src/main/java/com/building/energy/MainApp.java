package com.building.energy;

import com.building.energy.state.*;
import com.building.energy.strategy.*;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BuildingContext building = new BuildingContext();
        CostCalculator calculator = new CostCalculator(new StandardCostStrategy());

        boolean running = true;

        System.out.println("=== Smart Energy Management System ===");

        while (running) {
            System.out.println("\nSelect user type:");
            System.out.println("1. Manager");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Choice: ");

            int userType = -1;
            try {
                userType = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            switch (userType) {
                case 1:
                    System.out.println("\n--- Manager Menu ---");
                    System.out.println("1. Change system state (Active/Eco/Shutdown)");
                    System.out.println("2. Change cost policy (Standard/Peak/Green)");
                    System.out.print("Choice: ");
                    int mChoice = Integer.parseInt(scanner.nextLine());

                    if (mChoice == 1) {
                        System.out.println("Select state:");
                        System.out.println("1. Active");
                        System.out.println("2. Eco");
                        System.out.println("3. Shutdown");
                        System.out.print("Choice: ");
                        int st = Integer.parseInt(scanner.nextLine());
                        switch (st) {
                            case 1 -> building.setState(new ActiveState());
                            case 2 -> building.setState(new EcoState());
                            case 3 -> building.setState(new ShutdownState());
                            default -> System.out.println("Invalid state.");
                        }
                        System.out.println("System state updated: " + building.showStatus());
                    } else if (mChoice == 2) {
                        System.out.println("Select cost policy:");
                        System.out.println("1. Standard (500 per unit)");
                        System.out.println("2. Peak (1000 per unit)");
                        System.out.println("3. Green (300 per unit)");
                        System.out.print("Choice: ");
                        int pol = Integer.parseInt(scanner.nextLine());
                        switch (pol) {
                            case 1 -> calculator.setStrategy(new StandardCostStrategy());
                            case 2 -> calculator.setStrategy(new PeakCostStrategy());
                            case 3 -> calculator.setStrategy(new GreenCostStrategy());
                            default -> System.out.println("Invalid policy.");
                        }
                        System.out.println("Cost policy updated.");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- User Menu ---");
                    System.out.println("1. View current system status");
                    System.out.println("2. View total energy cost");
                    System.out.println("3. Simulate energy cost");
                    System.out.print("Choice: ");
                    int uChoice = Integer.parseInt(scanner.nextLine());

                    if (uChoice == 1) {
                        System.out.println("System status: " + building.showStatus());
                        System.out.println("Current consumption: " + building.getCurrentConsumption() + " units");
                    } else if (uChoice == 2) {
                        int consumption = building.getCurrentConsumption();
                        int cost = calculator.calculate(consumption);
                        System.out.println("Current consumption: " + consumption + " units");
                        System.out.println("Total cost: " + cost + " Toman");
                    } else if (uChoice == 3) {
                        System.out.print("Enter number of units: ");
                        int units = Integer.parseInt(scanner.nextLine());
                        System.out.println("Cost for " + units + " units: " + calculator.calculate(units) + " Toman");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;

                case 3:
                    running = false;
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid input.");
            }
        }

        scanner.close();
    }
}
