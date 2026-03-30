package com.example;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PracticeServiceTest {

    private PracticeService practiceService;

    @BeforeEach
    void setUp() {
        practiceService = new PracticeService(
            new Calculator(),
            new Factorial(),
            new PrimeNumber(),
            new ArrayFunctions()
        );
    }

    @Test
    void calculateAddShouldReturnSum() {
        double result = practiceService.calculate(10, 5, "add");
        assertEquals(15, result);
    }

    @Test
    void calculateDivideByZeroShouldThrowException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> practiceService.calculate(10, 0, "divide")
        );
        assertEquals("Cannot divide by zero.", ex.getMessage());
    }

    @Test
    void calculateUnknownOperationShouldThrowException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> practiceService.calculate(1, 2, "mod")
        );
        assertEquals("Unknown operation selected.", ex.getMessage());
    }

    @Test
    void factorialShouldReturnExpectedValue() {
        assertEquals(120, practiceService.factorial(5));
    }

    @Test
    void factorialNegativeShouldThrowException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> practiceService.factorial(-1)
        );
        assertEquals("Factorial is not defined for negative numbers.", ex.getMessage());
    }

    @Test
    void isPrimeShouldReturnTrueForPrimeAndFalseForComposite() {
        assertTrue(practiceService.isPrime(13));
        assertFalse(practiceService.isPrime(15));
    }

    @Test
    void executeArrayOperationAverageShouldParseAndCalculate() {
        PracticeService.ArrayOperationResult result = practiceService.executeArrayOperation("2, 4, 6", "average");

        assertArrayEquals(new int[] {2, 4, 6}, result.values());
        assertEquals(4.0, (double) result.result());
    }

    @Test
    void executeArrayOperationInvalidTokenShouldThrowException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> practiceService.executeArrayOperation("1, a, 3", "sum")
        );
        assertEquals("Invalid number: a", ex.getMessage());
    }
}
