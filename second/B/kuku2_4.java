package com.example.second.b;
// 課題B.9
public class kuku2_4 {
    public static void sub(String[] args) { // mainメソッド
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
        z=0;
    }
    //課題B.9
    /* 
    public static void main(String[] args) {
        sub(args);
    }
    */
   //課題B.9.a
   /* 
   public static void main(String[] args) {
    for(int i=0;i<2;i++){
        sub(args);
        System.out.println();
    }
   }
   */
   //課題B.9.b
    public static void main(String[] args) {

        // 上段：2個横に並べる
        for (int y = 1; y < 10; y++) {
            sub(y);            // 左の九九
            System.out.print("  "); // 間隔
            sub(y);            // 右の九九
            System.out.println();
        }
        System.out.println();

        // 下段：さらにもう1セット
        for (int y = 1; y < 10; y++) {
            sub(y);            // 左の九九
            System.out.print("  ");
            sub(y);            // 右の九九
            System.out.println();
        }
        
    }
}
/*
【sub(String[] args) と sub(int z) の違い】

役割の違い
・sub(String[] args) は九九全体を作るためのメイン処理。
　1〜9 の数字を順番に取り出し、sub(int z) を呼び出す。

・sub(int z) は九九の1つの段（例：3の段、7の段など）を出力する処理。

引数（パラメータ）の違い
・sub(String[] args) は文字列の配列（本来は main の引数）を受け取る。
・sub(int z) は整数 z を受け取り、z の段の九九を表示する。

呼び出し関係
・sub(String[] args) が sub(int z) を呼び出す立場。
・sub(int z) は渡された z を使って九九を出力する側。

分かりやすい例え
・sub(String[] args) ＝ 店長（段を指定して仕事を依頼する）
・sub(int z) ＝ 職人（指定された段の九九を実際に作る）

【まとめ】
sub(String[] args) は九九表全体を作るための制御方法。
sub(int z) は九九の1段だけを出力する処理。

*/