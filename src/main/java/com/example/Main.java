package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nChoose an operation:");
                System.out.println("1. Add");
                System.out.println("2. Subtract");
                System.out.println("3. Multiply");
                System.out.println("4. Divide");
                System.out.println("5. Exit");
                System.out.print("Enter choice (1-5): ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    scanner.nextLine();
                    continue;
                }

                int choice = scanner.nextInt();
                if (choice == 5) {
                    System.out.println("Goodbye!");
                    break;
                }

                if (choice < 1 || choice > 5) {
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    continue;
                }

                System.out.print("Enter first number: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    continue;
                }
                double first = scanner.nextDouble();

                System.out.print("Enter second number: ");
                if (!scanner.hasNextDouble()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.nextLine();
                    continue;
                }
                double second = scanner.nextDouble();

                try {
                    double result;
                    switch (choice) {
                        case 1:
                            result = calculator.add(first, second);
                            break;
                        case 2:
                            result = calculator.subtract(first, second);
                            break;
                        case 3:
                            result = calculator.multiply(first, second);
                            break;
                        case 4:
                            result = calculator.divide(first, second);
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                            continue;
                    }

                    System.out.println("Result: " + result);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}