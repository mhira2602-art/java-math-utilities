package com.example;

import org.springframework.stereotype.Service;

@Service
public class ArrayFunctions {
    public int sum(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }

    public int max(int[] array) {
        int maximum = array[0];
        for (int value : array) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

    public int min(int[] array) {
        int minimum = array[0];
        for (int value : array) {
            if (value < minimum) {
                minimum = value;
            }
        }
        return minimum;
    }

    public double average(int[] array) {
        return (double) sum(array) / array.length;
    }
}