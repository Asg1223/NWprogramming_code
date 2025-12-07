package com.example.second.Ex;

public class a {
public static void main(String[] args) {
      for (int y = 1; y <= 9; y++) {        // 行（y）
            for (int x = 1; x <= 9; x++) {    // 列（x）
                int val = x * y;              // 現在の値 x*y

                // 左隣の値
                int left = (x == 1) ? 0 : ( (x - 1) * y );

                // 上隣の値
                int up = (y == 1) ? 0 : ( x * (y - 1) );

                // 左隣+1 〜 x*y の素数の数
                int a = countPrime(left + 1, val);

                // 上隣+1 〜 x*y の素数の数
                int b = countPrime(up + 1, val);

                // a/b を幅揃えで出力
                System.out.printf("%2d/%-2d ", a, b);
            }
            System.out.println();
        }
    }

    // 素数の個数を数える関数
    public static int countPrime(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) count++;
        }
        return count;
    }

    // 素数判定
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

/*
-解説-
1.  mainメソッド ・九九のように 1〜9 の y（行）と x（列）をループする。
    ・各マスで value = x*y を計算する。 ・左隣の値 left、上隣の値 up
    を求める。

    -   x = 1 の時は左隣が無い → left = 0
    -   y = 1 の時は上隣が無い → up = 0 ・left+1 〜 xy の素数の数を a
        ・up+1 〜 xy の素数の数を b とし、a/b の形式で出力。

2.  countPrime(start, end) ・start 〜 end
    の間の整数を調べて素数の個数を数える。 ・x*y が素数なら end
    を含む（問題文の条件）。

3.  isPrime(n) ・n が 2 未満 → 素数ではない。 ・2 〜 √n
    まで割ってみて、割り切れなければ素数。

*/
