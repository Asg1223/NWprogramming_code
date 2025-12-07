package com.example.third.Ex;

public class maxdouble {
    public static void main(String[] args) {
        double x = 1.0;
        double y = 1.0;

        while (!Double.isInfinite(y)) {
            x = y;
            y = y * 2.0;
        }

        System.out.println("doubleの最大値 ≈ " + x);
    }
}