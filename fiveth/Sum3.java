package com.example.fiveth;

public class Sum3 {
    public static void main(String[] args) {
        // 期待する引数: <int> <op> <int>
        if (args.length != 3) {
            System.out.println("引数は3つ必要です: <数値1> <演算子> <数値2>");
            return;
        }

        int a, b;
        try {
            a = Integer.parseInt(args[0]);
            b = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("数値は整数を指定してください。");
            return;
        }

        String op = args[1];
        long resultLong; // 中間でオーバーフロー検知したい場合に備えて long を使用

        switch (op) {
            case "+":
                resultLong = (long) a + (long) b;
                break;
            case "-":
                resultLong = (long) a - (long) b;
                break;
            case "x":
                resultLong = (long) a * (long) b;
                break;
            case "/":
                if (b == 0) {
                    System.out.println("0 で割ることはできません。");
                    return;
                }
                resultLong = a / b; // 整数の切り捨て
                break;
            default:
                System.out.println("未知の演算子です: " + op + " (使用可能: +, -, x, /)");
                return;
        }

        // 結果表示（例に合わせる）
        System.out.println(a + " " + op + " " + b + " = " + resultLong);
    }
}
