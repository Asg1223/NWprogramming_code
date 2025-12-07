package com.example.fiveth;

import java.util.ArrayList;

public class Sum5 {
    public static void main(String[] args) {
        if (args.length == 0) return;
        if (args.length == 1) {
            System.out.println(args[0] + " = " + args[0]);
            return;
        }

        // 文字列リストにコピー
        ArrayList<String> list = new ArrayList<>();
        for (String s : args) list.add(s);

        // まず × と / を左から順に処理
        for (int i = 1; i < list.size() - 1; i += 2) {
            String op = list.get(i);
            if (op.equals("x") || op.equals("/")) {
                int a = Integer.parseInt(list.get(i - 1));
                int b = Integer.parseInt(list.get(i + 1));
                int r = (op.equals("x")) ? a * b : a / b;
                list.set(i - 1, Integer.toString(r));
                list.remove(i);   // 演算子削除
                list.remove(i);   // 次の数削除
                i -= 2;
            }
        }

        // 残りの + と - を順に処理
        int result = Integer.parseInt(list.get(0));
        for (int i = 1; i < list.size(); i += 2) {
            String op = list.get(i);
            int b = Integer.parseInt(list.get(i + 1));
            if (op.equals("+")) result += b;
            else if (op.equals("-")) result -= b;
        }

        for (String s : args) System.out.print(s + " ");
        System.out.println("= " + result);
    }
}
