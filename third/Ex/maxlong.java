package com.example.third.Ex;
//Ex6
public class maxlong {
    public static void main(String[] args) {
        long x = 0;
        long y = 1;

        while (y > x) {
            x = y;
            y = y * 2;
        }

        System.out.println("longの最大値 = " + x);
    }
}
