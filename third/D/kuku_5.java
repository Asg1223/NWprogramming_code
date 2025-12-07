package com.example.third.d;
// 課題D 15
public class kuku_5 {
    public static void main(String[] args) {

        // --- 列番号の表示 ---
        System.out.print("     "); // 左上の空白
        for (int x = 1; x <= 15; x++) {
            System.out.printf("%6d", x);
        }
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------");

        // --- 本体 ---
        for (int y = 1; y <= 15; y++) {
            System.out.printf("%3d |", y); // 行番号

            for (int x = 1; x <= 15; x++) {
                int v = x * y;

                // 0x付き16進数（必ず2桁）
                System.out.printf("%6s", printHex2(v));
            }
            System.out.println();
        }
    }

    // 0〜15 を 0–9, A–F に変換
    public static char printHex(int v) {
        if (v < 10)
            return (char) ('0' + v);
        else
            return (char) ('A' + (v - 10));
    }

    public static String printHex2(int v) {
        int high = v / 16;   // 上位桁
        int low  = v % 16;   // 下位桁

        // 1桁の場合は上位桁を0にする → "0x0A" 形式
        if (high == 0) {
            return "0x0" + printHex(low);
        } else {
            return "0x" + printHex(high) + printHex(low);
        }
    }
}
