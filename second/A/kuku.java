package com.example.second.a;

// 課題A(1)
public class kuku {
    public static void main(String[] args) {
        int x, y;
        y = 1;
        while (y < 10) { // while文行(縦方向)の変数を変化させる
            for (x = 1; x < 10; x++) { // for文列(横方向)の変数を変化させる
                if (x * y < 10) { // if文1桁の数の場合前に空白を一つ表示
                    System.out.print(" " + x * y + " ");
                } else { // else文2桁の数の場合
                    System.out.print(x * y + " ");
                }
            }
            System.out.println(); // 改行表示
            y++;
        }
    }
}