package com.example.third.Ex;

public class Ex11 {
 public static boolean isPrime(long n) {
        if (n < 2) return false;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long[] examples = {9876543210L, 1023456789L, 1234567890L, 987654321L};
        boolean found = false;

        for (long num : examples) {
            for (long a = 2; a * a <= num; a++) {
                if (isPrime(a) && num % a == 0) {
                    long b = num / a;
                    if (isPrime(b)) {
                        System.out.println("半素数: " + num + " = " + a + " × " + b);
                        found = true;
                    }
                }
            }
        }

        if (!found) System.out.println("10桁パンデジタルの半素数は存在しません。");
    }
}
