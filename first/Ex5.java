package com.example.first;
//(5) 10000までのフィボナッチ数列を表⽰するプログラムを作りなさい．
public class Ex5 {
    public static void main(String[] args) {
        int fib1 = 0;
        int fib2 = 1;
        System.out.print(fib1 + " " + fib2 + " ");
        for (int i = 2; i < 100000; i++) {
            int fibNext = fib1 + fib2;
            if (fibNext > 10000) {
                break;
            }
            System.out.print(fibNext + " ");
            fib1 = fib2;
            fib2 = fibNext;

        }
    }
}
