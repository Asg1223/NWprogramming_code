package com.example.first;
//(4) 1から100までの素数(1とその数⾃⾝以外では割り切れない数)を順に求め，表⽰するプログラムを作りなさい

public class Ex4 {
    public static void main(String[] args) {
        int n = 100;

        for (int i = 2; i < n; i++) {
            boolean isPrime = true;

            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                System.out.print(i + " ");
            }
        }
    }
}
