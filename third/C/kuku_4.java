package com.example.third.c;
// 課題C.13,14
public class kuku_4 {
public static void main(String[] args) {

        // --- 列番号の表示 ---
        System.out.print("     "); // 左上の空白
        for (int x = 1; x <= 15; x++) {
            System.out.printf("%6s", x);
        }
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------------------");
        // --- 本体 ---
        for (int y = 1; y <= 15; y++) {
            System.out.printf("%3d |", y); // 行番号

            for (int x = 1; x <= 15; x++) {
                int v = x * y;

                String hex = toHex2(v); // 整形済みのHEX文字列（幅2）
                System.out.printf("%6s", hex);
            }
            System.out.println();
        }
    }

    // 16進数1～2桁を文字列で返す（1桁なら前にスペース）
    public static String toHex2(int v) {
        if (v < 16) {
            return "  " +"0x"+ toHex1(v); // 一桁 → 前にスペース
        } else {
            int w = v / 16;
            int r = v % 16;
            return " " +"0x" + toHex1(w) + toHex1(r);
        }
    }

    // 0〜15 を 0-9,A-F の字に変換
    public static char toHex1(int v) {
        if (v < 10)
            return (char) ('0' + v);
        else
            return (char) ('a' + (v - 10));
    }
}
