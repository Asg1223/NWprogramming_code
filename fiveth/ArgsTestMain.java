package com.example.fiveth;
//課題P
public class ArgsTestMain {
    public static void main(String[] args) {
        int i = 60;
        ArgsTest9 kodai = new ArgsTest9();

        ArgsTest9.setGrade(kodai, i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i + " count = " + ArgsTest9.count);

        ArgsTest9.setGrade(kodai, 70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i + " count = " + ArgsTest9.count);

        // 課題44: もう一度setGradeを呼び出す
        ArgsTest9.setGrade(kodai, 80);
        System.out.println("3: grade = " + kodai.grade + " i = " + i + " count = " + ArgsTest9.count);
    }
}
