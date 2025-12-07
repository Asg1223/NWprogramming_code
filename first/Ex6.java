package com.example.first;

//(6) 10億から20億までに素数がいくつあるか求め，表⽰するプログラムを作りなさい．
import java.util.*;
public class Ex6 {

    // エラトステネスの篩で√Rまでの素数を求める
    private static List<Integer> simpleSieve(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i])
                primes.add(i);
        }
        return primes;
    }

    // 区間 [L, R] の素数をカウントする
    public static long countPrimesInRange(long L, long R) {
        int limit = (int) Math.sqrt(R) + 1;
        List<Integer> primes = simpleSieve(limit);

        boolean[] isPrime = new boolean[(int) (R - L + 1)];
        Arrays.fill(isPrime, true);

        for (int p : primes) {
            long start = Math.max((long) p * p, ((L + p - 1) / p) * (long) p);
            for (long j = start; j <= R; j += p) {
                isPrime[(int) (j - L)] = false;
            }
        }

        long count = 0;
        for (int i = 0; i <= R - L; i++) {
            if (isPrime[i] && (L + i) > 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        long L = 1_000_000_000L;
        long R = 2_000_000_000L;

        System.out.println("Counting primes between " + L + " and " + R + "...");
        long start = System.currentTimeMillis();
        long count = countPrimesInRange(L, R);
        long end = System.currentTimeMillis();

        System.out.println("Number of primes: " + count);
        System.out.println("Time: " + (end - start) + " ms");
    }
}