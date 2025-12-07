package com.example.fiveth;

public class kuku {
    public static void main(String[] args) {
        int x, y;
        y = 1;

        // 最上段の数字の行表示
        System.out.print("    ");
        for (x = 1; x < 10; x++) {
            System.out.print(" " + x + " ");
        }
        System.out.println(); // 改行

        // ----線の表示
        for (x = 0; x < 9 * 3 + 4; x++) {
            System.out.print("-");
        }
        System.out.println(); // 改行

        // 九九の表の表示
        while (y < 10) {
            System.out.print(y + "  |"); // 左端表示
            for (x = 1; x < 10; x++) {
                if (x >= y) { // 右上半分だけ表示
                    if (x * y < 10) {
                        System.out.print(" " + x * y + " ");
                    } else {
                        System.out.print(x * y + " ");
                    }
                } else {
                    System.out.print("   "); // 表の整形のための空白
                }
            }
            System.out.println();
            y++;
        }
    }
}
