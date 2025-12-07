package com.example.third.Ex;

public class Ex4 {
    public static void main(String[] args) {
        for (int y = 1; y <= 9; y++) {
            for (int x = 1; x <= 9; x++) {
                int v = x * y;
                System.out.print(v);
                if (x != 9) System.out.print(" "); // ← 最後の列以外にだけ空白
            }
            System.out.println("*"); // 改行
        }
    }
}