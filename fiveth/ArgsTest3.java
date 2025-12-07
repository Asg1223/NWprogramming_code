package com.example.fiveth;

public class ArgsTest3 {
    public static void main(String[] args) {
        if (args.length == 0) return;

        char first = args[0].charAt(0);

        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                char c = args[i].charAt(j);
                if (c != first) System.out.print(c);
            }
            System.out.print(" ");
        }
        System.out.println();
    }
}
