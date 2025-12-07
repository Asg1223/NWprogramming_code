package com.example.third.Ex;

public class Ex2 {

    // --- 8進1桁文字に変換 ---
    public static char toOctChar(int v) {
        return (char) ('0' + v);
    }

    // --- 8進 n 桁 ゼロ埋め文字列を返す ---
    public static String printOctNstr(int v, int n) {
        int x = 1;
        for (int i = 1; i < n; i++) x *= 8;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int w = v / x;
            v = v % x;
            sb.append(toOctChar(w));
            x /= 8;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        // --- 列番号表示（右揃え）---
        System.out.print("      ");
        for (int x = 1; x <= 9; x++) {
            System.out.printf("%4d", x); // ← 右揃え
        }
        System.out.println();
        
        System.out.println("-----------------------------------------------");
        // --- 本体（九九）---
        for (int y = 1; y <= 9; y++) {
            System.out.printf("%4d |", y); // 行番号も右揃え

            for (int x = 1; x <= 9; x++) {
                int v = x * y;

                // 8進3桁を取得 → 幅4で右揃え
                String oct = printOctNstr(v, 3);
                System.out.printf("%4s", oct);
            }
            System.out.println();
        }
    }
}
