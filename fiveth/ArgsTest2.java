package com.example.fiveth;

public class ArgsTest2 {
    public static void main(String[] args) {
        String topArg = "";
        if (args.length >= 1) topArg = args[0];  // 先頭オプション

        // 1文字ずつ表示
        for (int i = 0; i < topArg.length(); i++) {
            System.out.print("[" + topArg.charAt(i) + "]");
        }
        System.out.println();

        // 各オプションと比較
        for (int i = 0; i < args.length; i++) {
            System.out.print("arg " + i + " : " + args[i]);
            if (args[i].equals(topArg)) System.out.println("  same");
            else System.out.println("  diff");
        }
    }
}
