package com.example.fiveth;
//課題N
public class ArgsTest7 {
    int grade;

    public static void main(String[] args) {
        int i = 60;
        ArgsTest7 kodai = new ArgsTest7();
        setGrade(kodai, i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i);

        setGrade(kodai, 70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
    }

    public static void setGrade(ArgsTest7 student, int g) {
        student.grade = g;
        student.grade++;
        student = new ArgsTest7(); // 新しいインスタンスを生成
        student.grade = 70; // main内のkodaiには影響しない
    }
}

