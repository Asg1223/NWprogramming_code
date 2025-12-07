package com.example.fiveth;

public class Sum4 {
    public static void main(String[] args) {
        if (args.length == 0) return;
        if (args.length == 1) {
            System.out.println(args[0] + " = " + args[0]);
            return;
        }

        if (args.length % 2 == 0) {
            System.out.println("式が不正です。");
            return;
        }

        int result = Integer.parseInt(args[0]);
        for (int i = 1; i < args.length; i += 2) {
            String op = args[i];
            int value = Integer.parseInt(args[i + 1]);
            switch (op) {
                case "+": result += value; break;
                case "-": result -= value; break;
                case "x": result *= value; break;
                case "/": result /= value; break;
                default:
                    System.out.println("不明な演算子: " + op);
                    return;
            }
        }
        // 式全体と結果の表示
        for (String s : args) System.out.print(s + " ");
        System.out.println("= " + result);
    }
}