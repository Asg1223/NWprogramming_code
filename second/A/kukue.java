package com.example.second.a;

public class kukue {
    public static void main(String[] args) {
        int x, y;
        y = 1; //ここをコメントアウトするとエラーになる
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

//起きたエラーは以下の通り
/*
Exception in thread "main" java.lang.Error: Unresolved compilation problems: 
        The local variable y may not have been initialized
        The local variable y may not have been initialized
        The local variable y may not have been initialized
        The local variable y may not have been initialized
        The local variable y may not have been initialized

        at com.example.second.kadaia.kukue.main(kukue.java:7)
PS D:\Java> 

【エラー名】
The local variable y may not have been initialized

【エラーの理由】
Javaでは、メソッドの中で宣言した変数（ローカル変数）は、必ず値を入れてから使わないといけない。
変数yが「宣言されただけで値が入っていない状態」でif文やwhile文の条件として使われているため、「yに値が入っていない可能性があります」というエラーになる。

【よくある例】
int y;
while (y <= 9) {  ← yにまだ値がないのに使っているのでエラー

【解決方法】
変数を宣言したら、必ず最初に値を入れる。

例：
int y = 1;

これでエラーは解消する。

*/