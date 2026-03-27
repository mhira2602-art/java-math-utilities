package com.example;

public class ArrayFunctions {
    public static int sum(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }

    public static int max(int[] array) {
        int maximum = array[0];
        for (int value : array) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

    public static int min(int[] array) {
        int minimum = array[0];
        for (int value : array) {
            if (value < minimum) {
                minimum = value;
            }
        }
        return minimum;
    }

    public static double average(int[] array) {
        return (double) sum(array) / array.length;
    }
}