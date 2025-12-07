package com.example.first;

//(2)下のような九九の表を表⽰するJavaプログラムを作成しなさい(右上半分だけ表⽰)．
/*
    1  2  3  4  5  6  7  8  9
----------------------------------
1|  1  2  3  4  5  6  7  8  9
2|     4  6  8 10 12 14 16 18
3|        9 12 15 18 21 24 27
4|          16 20 24 28 32 36
5|             25 30 35 40 45
6|                36 42 48 54
7|                   49 56 63
8|                      64 72
9|                         81
*/
public class Ex2 {
    public static void main(String[] args) {

        // 見出し
        System.out.print("  ");
        for (int x = 1; x <= 9; x++) {
            System.out.printf("%3d", x);
        }
        System.out.println();

        // 仕切り線
        System.out.println("----------------------------------");

        for (int y = 1; y <= 9; y++) {
            System.out.printf("%d|", y);
            for (int x = 1; x <= 9; x++) {

                if (x < y) {
                    // 下三角部分は空白
                    System.out.printf("   ");
                } else {
                    // 上三角部分だけ計算して表示
                    System.out.printf("%3d", x * y);
                }
            }
            System.out.println();
        }
    }
}
