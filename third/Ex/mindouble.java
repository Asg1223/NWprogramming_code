package com.example.third.Ex;

public class mindouble {
    public static void main(String[] args) {
        double x = 1.0;
        double y = 1.0;

        while (y / 2.0 > 0.0) {
            x = y / 2.0;
            y = y / 2.0;
        }

        System.out.println("doubleの最小正値 ≈ " + x);
    }
}