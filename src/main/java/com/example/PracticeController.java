package com.example;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PracticeController {
    private final PracticeService practiceService;

    @Autowired
    public PracticeController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }

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
            double result = practiceService.calculate(a, b, operation);
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
            long result = practiceService.factorial(number);
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
        model.addAttribute("result", practiceService.isPrime(number));
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
            PracticeService.ArrayOperationResult arrayResult = practiceService.executeArrayOperation(numbers, operation);
            model.addAttribute("parsed", Arrays.toString(arrayResult.values()));
            model.addAttribute("result", arrayResult.result());
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
        }

        return "array";
    }
}
