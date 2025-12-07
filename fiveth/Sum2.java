package com.example.fiveth;

public class Sum2 {
    public static void main(String[] args) {
        if (args.length == 0) return;

        int sum = 0;
        for (String s : args) {
            sum += Integer.parseInt(s);
        }

        // 式の表示
        for (int i = 0; i < args.length; i++) {
            System.out.print(args[i]);
            if (i < args.length - 1) System.out.print(" + ");
        }
        System.out.println(" = " + sum);
    }
}
