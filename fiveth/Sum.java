package com.example.fiveth;

public class Sum {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("引数は2つ必要です。");
            return;
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int sum = a + b;

        System.out.println(a + " + " + b + " = " + sum);
    }
}
