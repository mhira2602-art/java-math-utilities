package com.example;

import org.springframework.stereotype.Service;

@Service
public class Factorial {
    // Recursive method to calculate factorial
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is not defined for negative numbers.");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }
}
