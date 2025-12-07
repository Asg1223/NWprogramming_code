package com.example.first;

//(1) 下のような九九の表を表⽰するJavaプログラムを作成せよ
/* 
   1  2  3  4  5  6  7  8  9 
-------------------------------
1| 1  2  3  4  5  6  7  8  9
2| 2  4  6  8 10 12 14 16 18
3| 3  6  9 12 15 18 21 24 27
4| 4  8 12 16 20 24 28 32 36
5| 5 10 15 20 25 30 35 40 45
6| 6 12 18 24 30 36 42 48 54
7| 7 14 21 28 35 42 49 56 63 
8| 8 16 24 32 40 48 56 64 72
9| 9 18 27 36 45 54 63 72 81
*/
public class Ex1 {
    public static void main(String[] args) {
        int x, y = 1;

        // 見出し
        System.out.print("  ");
        for (y = 1; y <= 9; y++) {
            System.out.print(" " + y + " ");
        }
        System.out.println();

        System.out.println("-------------------------------");
        for (y = 1; y <= 9; y++) {
            for (x = 1; x <= 9; x++) {
                if (x == 1) {
                    System.out.print(y + "|");
                }
                if (x * y < 10) {
                    System.out.print(" " + x * y + " ");
                } else {
                    System.out.print(x * y + " ");
                }
            }
            System.out.println();
        }
    }
}
