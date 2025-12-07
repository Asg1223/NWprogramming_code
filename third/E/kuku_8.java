package com.example.third.e;

public class kuku_8{
    public static void main(String[] args) {

        // 列番号
        System.out.print("       ");
        for (int x = 1; x <= 9; x++) {
            System.out.printf("%7d", x);
        }
        System.out.println();
        System.out.println("------------------------------------------------");

        // 九九本体
        for (int y = 1; y <= 9; y++) {
            System.out.printf("%2d |", y); // 行番号

            for (int x = 1; x <= 9; x++) {
                int v = x * y;
                System.out.print("0x");
                printHex3(v, 3); // 3桁表示
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static char printHex(int v) {
        if (v < 10)
            return (char) ('0' + v);
        else
            return (char) ('A' + (v - 10));
    }

    // n桁の16進数表示（デバッグ付き）
    public static void printHex3(int v, int n) {
        int i, x = 1, w;

        // 割る数を計算
        for (i = 1; i < n; i++) x = x * 16;

        // デバッグ出力：課題20指示
        System.out.println("[x = " + x + "]");

        for (i = 0; i < n; i++) {
            w = v / x;
            v = v % x;
            System.out.print(printHex(w));

            x = x / 16;

            // 各桁処理後の x を確認
            System.out.println("[x -> " + x + "]");
        }
    }
}
