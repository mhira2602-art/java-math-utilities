package com.example;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PracticeController {
    private final Calculator calculator = new Calculator();

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/calculator")
    public String calculatorPage() {
        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculate(
        @RequestParam double a,
        @RequestParam double b,
        @RequestParam String operation,
        Model model
    ) {
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("operation", operation);

        try {
            double result = switch (operation) {
                case "add" -> calculator.add(a, b);
                case "subtract" -> calculator.subtract(a, b);
                case "multiply" -> calculator.multiply(a, b);
                case "divide" -> calculator.divide(a, b);
                default -> throw new IllegalArgumentException("Unknown operation selected.");
            };
            model.addAttribute("result", result);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "calculator";
    }

    @GetMapping("/factorial")
    public String factorialPage() {
        return "factorial";
    }

    @PostMapping("/factorial")
    public String factorial(
        @RequestParam int number,
        Model model
    ) {
        model.addAttribute("number", number);

        try {
            long result = Factorial.factorial(number);
            model.addAttribute("result", result);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "factorial";
    }

    @GetMapping("/prime")
    public String primePage() {
        return "prime";
    }

    @PostMapping("/prime")
    public String prime(
        @RequestParam int number,
        Model model
    ) {
        model.addAttribute("number", number);
        model.addAttribute("result", PrimeNumber.isPrime(number));
        return "prime";
    }

    @GetMapping("/array")
    public String arrayPage() {
        return "array";
    }

    @PostMapping("/array")
    public String array(
        @RequestParam String numbers,
        @RequestParam String operation,
        Model model
    ) {
        model.addAttribute("numbers", numbers);
        model.addAttribute("operation", operation);

        try {
            int[] values = parseArray(numbers);
            model.addAttribute("parsed", Arrays.toString(values));

            Object result = switch (operation) {
                case "sum" -> ArrayFunctions.sum(values);
                case "max" -> ArrayFunctions.max(values);
                case "min" -> ArrayFunctions.min(values);
                case "average" -> ArrayFunctions.average(values);
                default -> throw new IllegalArgumentException("Unknown array operation selected.");
            };
            model.addAttribute("result", result);
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "array";
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
}
