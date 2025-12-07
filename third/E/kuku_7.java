package com.example.third.e;
//課題E.19
public class kuku_7 {
    public static void main(String[] args) {

        // ヘッダー（列番号）
        System.out.print("    "); // 左上空白
        for (int x = 1; x <= 9; x++) {
            System.out.printf("%4d", x);
        }
        System.out.println();
        System.out.println("-----------------------------");

        // 九九本体
        for (int y = 1; y <= 9; y++) {
            System.out.printf("%2d |", y); // 行番号

            for (int x = 1; x <= 9; x++) {
                int v = x * y;
                System.out.printf("%3d ", v); // 3桁表示
            }
            System.out.println();
        }
    }
}
