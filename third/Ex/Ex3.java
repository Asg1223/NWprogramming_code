package com.example.third.Ex;

public class Ex3 {

    // --- 1桁の変換（0〜31） ---
    static char toDigit(int v) {
        if (v < 10) return (char) ('0' + v);
        else        return (char) ('A' + (v - 10));  // 10→A, 11→B …
    }

    // --- v を base 進数で digits 桁の文字列として返す ---
    static String toBaseN(int v, int base, int digits) {
        char[] buf = new char[digits];
        for (int i = digits - 1; i >= 0; i--) {
            buf[i] = toDigit(v % base);
            v /= base;
        }
        return new String(buf);
    }

    public static void main(String[] args) {

        // --- 基数（n進数）を取得 ---
        if (args.length == 0) {
            System.out.println("基数 n を指定してください（2～32）");
            return;
        }

        int base = Integer.parseInt(args[0]);
        if (base < 2 || base > 32) {
            System.out.println("基数は 2～32 の範囲で指定してください。");
            return;
        }

        // --- 9×9 = 最大 81 を n進数にしたときの桁数を計算 ---
        int max = 81;
        int digits = 1;
        int t = base;
        while (t <= max) {
            digits++;
            t *= base;
        }

        // --- 列番号 ---
        System.out.print("      ");
        for (int x = 1; x <= 9; x++) {
            System.out.printf("%" + (digits + 1) + "d", x);
        }
        System.out.println();
        System.out.println("----------------------------------------------");

        // --- 九九本体 ---
        for (int y = 1; y <= 9; y++) {
            System.out.printf("%4d |", y);

            for (int x = 1; x <= 9; x++) {
                int v = x * y;
                String s = toBaseN(v, base, digits); // n進数へ変換
                System.out.printf("%" + (digits + 1) + "s", s);
            }
            System.out.println();
        }
    }
}

// 使用コマンド例）
//   d:; cd 'd:\Java'; & 'C:\Program Files\Eclipse Adoptium\jdk-21.0.8.9-hotspot\bin\java.exe' '-XX:+ShowCodeDetailsInExceptionMessages' '-cp' 'D:\Java\project2\target\classes' 'com.example.third.Ex.Ex3' 4
