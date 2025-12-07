package com.example.second.b;
// 課題B.6
public class kuku2 {
    public static void main(String[] args) { // mainメソッド
        int y;
        y = 1;
        while (y < 10) {
            sub(y); // subメソッドの呼び出し（元のプログラムの6～12行に相当）
            System.out.println();
            y++;
        }
    }

    public static void sub(int y) { // subメソッド
        int x;
        for (x = 1; x < 10; x++) {
            if (x * y < 10) {
                System.out.print(" " + x * y + " ");
            } else {
                System.out.print(x * y + " ");
            }
        }
    }
}