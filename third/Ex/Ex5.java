package com.example.third.Ex;

public class Ex5 {
  public static void main(String[] args) {
        int x = 0;
        int y = 1;

        while (true) {
            if (y < x) break; 
            x = y;
            y = y + 1;
        }

        System.out.println("intの最大値 = " + x);
    }
}
/*
考え方
オバーフローをさせて最大値を超えると負になることを利用
*/