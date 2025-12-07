package com.example.third.Ex;

public class Ex10 {
  public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int bestA = 0, bestB = 0, bestC = 0;
        int minDiff = Integer.MAX_VALUE;

        // a, b は素数で a < b
        for (int a = 2; a < 50000; a++) {
            if (!isPrime(a)) continue;
            for (int b = a + 1; b < 50000; b++) { // a ≠ b
                if (!isPrime(b)) continue;

                long c = (long) a * b;
                if (c > Integer.MAX_VALUE) break; // int範囲外なら中断

                int diff = b - a;
                if (diff < minDiff || (diff == minDiff && c > bestC)) {
                    minDiff = diff;
                    bestA = a;
                    bestB = b;
                    bestC = (int) c;
                }
            }
        }

        System.out.println("a=" + bestA + " b=" + bestB +
                           " c=" + bestC +
                           " |a-b|=" + minDiff);
    }

}
//方法として　双子素数＝差が最小であることを利用する方法　などがある