package com.example.fiveth;
//課題M
public class ArgsTest6 {
    int grade;

    public static void main(String[] args) {
        int i = 60;
        ArgsTest6 kodai = new ArgsTest6();
        kodai.setGrade(i); // iを引数にして呼び出し iの値は変わらない
        System.out.println("1: grade = " + kodai.grade + " i = " + i);

        kodai.setGrade(70); // 値を直接引数にして呼び出し
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
    }

    public void setGrade(int g) {
        grade = g;
        g++; // 課題38で追加
    }
}
