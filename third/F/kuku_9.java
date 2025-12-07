package com.example.third.f;

//課題E
public class kuku_9 {
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

                // 0x を付けて 2桁の16進表示（printHex2 の引数は2個）
                System.out.print(" 0x");
                printHex2(v, 2);  
                System.out.print(" ");
            }
            System.out.println();
        }

        // オーバーロードのテスト
        System.out.println("\n--- オーバーロード確認テスト ---");
        System.out.println( printHex2(26) );   // ← 引数1個 → String が返るほう
        printHex2(26, 2);                      // ← 引数2個 → n桁表示のほう
    }

    // --- 0〜15 を 0–9, A–F に変換 ---
    public static char printHex(int v) {
        if (v < 10)
            return (char) ('0' + v);
        else
            return (char) ('A' + (v - 10));
    }

    // ---（元の）0x付き2桁16進（String を返す）--- ★引数1個
    public static String printHex2(int v) {
        System.out.println("★ printHex2(int) が呼ばれました");

        int high = v / 16; 
        int low  = v % 16;

        if (high == 0) {
            return "0x0" + printHex(low);
        } else {
            return "0x" + printHex(high) + printHex(low);
        }
    }

    // ---（元printHex3）n桁の16進数を出力する --- ★引数2個
    public static void printHex2(int v, int n) {
        System.out.println("★ printHex2(int,int) が呼ばれました");

        int i, x = 1, w;

        // 割る数 = 16^(n-1)
        for (i = 1; i < n; i++)
            x = x * 16;

        // 上位桁から順に表示
        for (i = 0; i < n; i++) {
            w = v / x;
            v = v % x;
            System.out.print(printHex(w));
            x = x / 16;
        }
    }
}
