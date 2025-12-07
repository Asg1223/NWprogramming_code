package com.example.second.b;
// 課題B.7
public class kuku2_2 {
    public static void main(String[] args) { // mainメソッド
        int y;
        y = 1;
        while (y < 10) {
            sub(y); // subメソッドの呼び出し（元のプログラムの6～12行に相当）
            System.out.println();
            y++;
        }
    }

    public static void sub(int z) { // subメソッド
        int x;
        for (x = 1; x < 10; x++) {
            if (x * z < 10) {
                System.out.print(" " + x * z + " ");
            } else {
                System.out.print(x * z + " ");
            }
        }
    }
}