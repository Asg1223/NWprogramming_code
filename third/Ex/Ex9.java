package com.example.third.Ex;

public class Ex9 {
    public static boolean isPrime(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int maxA = 0, maxB = 0, maxC = 0, maxSum = 0;

        for (int a = 2; a < 50000; a++) {
            if (!isPrime(a))
                continue;
            for (int b = a; b < 50000; b++) {
                if (!isPrime(b))
                    continue;
                long c = (long) a * b;
                if (c > Integer.MAX_VALUE)
                    break;

                int sum = a + b;
                if (sum > maxSum) {
                    maxSum = sum;
                    maxA = a;
                    maxB = b;
                    maxC = (int) c;
                }
            }
        }
        System.out.println("a=" + maxA + " b=" + maxB + " c=" + maxC + " a+b=" + maxSum);
    }
}
