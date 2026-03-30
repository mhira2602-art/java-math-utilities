package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PracticeService {
    private final Calculator calculator;
    private final Factorial factorialService;
    private final PrimeNumber primeNumberService;
    private final ArrayFunctions arrayFunctions;

    @Autowired
    public PracticeService(
        Calculator calculator,
        Factorial factorialService,
        PrimeNumber primeNumberService,
        ArrayFunctions arrayFunctions
    ) {
        this.calculator = calculator;
        this.factorialService = factorialService;
        this.primeNumberService = primeNumberService;
        this.arrayFunctions = arrayFunctions;
    }

    public double calculate(double a, double b, String operation) {
        return switch (operation) {
            case "add" -> calculator.add(a, b);
            case "subtract" -> calculator.subtract(a, b);
            case "multiply" -> calculator.multiply(a, b);
            case "divide" -> calculator.divide(a, b);
            default -> throw new IllegalArgumentException("Unknown operation selected.");
        };
    }

    public long factorial(int number) {
        return factorialService.factorial(number);
    }

    public boolean isPrime(int number) {
        return primeNumberService.isPrime(number);
    }

    public ArrayOperationResult executeArrayOperation(String numbers, String operation) {
        int[] values = parseArray(numbers);

        Object result = switch (operation) {
            case "sum" -> arrayFunctions.sum(values);
            case "max" -> arrayFunctions.max(values);
            case "min" -> arrayFunctions.min(values);
            case "average" -> arrayFunctions.average(values);
            default -> throw new IllegalArgumentException("Unknown array operation selected.");
        };

        return new ArrayOperationResult(values, result);
    }

    private int[] parseArray(String numbers) {
        if (numbers == null || numbers.trim().isEmpty()) {
            throw new IllegalArgumentException("Please enter at least one number.");
        }

        String[] tokens = numbers.split(",");
        int[] values = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();
            if (token.isEmpty()) {
                throw new IllegalArgumentException("Invalid list. Use comma-separated integers like 2, 4, 6");
            }

            try {
                values[i] = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number: " + token);
            }
        }

        return values;
    }

    public record ArrayOperationResult(int[] values, Object result) {
    }
}