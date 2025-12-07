package com.example.first;
//(3) 1から100までの3の倍数を全て表⽰するプログラムをつくりなさい．
public class Ex3 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.print(i + " ");
            }
        }

    }
}
