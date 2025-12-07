package com.example.fiveth;

public class ArgsTest5 {
    public static void main(String[] args) {
        for (String s : args) {
            for (int i = s.length() - 1; i >= 0; i--) {
                System.out.print(s.charAt(i));
            }
            System.out.print(" ");
        }
        System.out.println();
    }
}
