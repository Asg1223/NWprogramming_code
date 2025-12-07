package com.example.third.Ex;

public class EX1 {
    public static void main(String[] args) {

        // --- 列番号の表示 ---
        System.out.print("     "); // 左上の空白
        for (int x = 1; x <= 15; x++) {
            System.out.printf("%6d", x);
        }
        System.out.println();
        System.out.println(
                "----------------------------------------------------------------------------------------------------------");

        // --- 本体 ---
        for (int y = 1; y <= 15; y++) {
            System.out.printf("%3d |", y); // 行番号

            for (int x = 1; x <= 15; x++) {
                int v = x * y;

                // 0x を付けて printHex3(v,2) で2桁表示
                System.out.print(" 0x");
                printHex3(v, 2); // ← 指示どおり printHex3 を使用
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // --- 0〜15 を 0–9, A–F に変換 ---
    public static char printHex(int v) {
        if (v < 10)
            return (char) ('0' + v);
        else
            return (char) ('A' + (v - 10));
    }

    // --- 0x付き2桁16進（残しておく：課題指示） ---
    public static String printHex2(int v) {
        int high = v / 16; // 上位桁
        int low = v % 16; // 下位桁

        if (high == 0) {
            return "0x0" + printHex(low);
        } else {
            return "0x" + printHex(high) + printHex(low);
        }
    }

    // --- n 桁の16進をそのまま出力する（課題の printHex3）---
    public static void printHex3(int v, int n) {
        int i, x = 1, w;

        // 割る数 = 16^(n-1)
        for (i = 1; i < n; i++)
            x = x * 16;

        // 上位桁から順に表示
        for (i = 0; i < n; i++) {
            w = v / x;   // 上位桁を切り出し
            v = v % x;   // 残りを次へ回す
            System.out.print(printHex(w)); // 桁ごとに出力
            x = x / 16;  // 次の桁へ
        }
    }
}
