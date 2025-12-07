package com.example.fiveth;
// 課題O(41)
public class ArgTest8 {
    int grade;

    public static void main(String[] args) {
        int i = 60;
        ArgTest8 kodai = new ArgTest8();
        ArgTest8.setGrade(kodai, i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i);

        ArgTest8.setGrade(kodai, 70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
    }

    public static void setGrade(ArgTest8 student, int grade) {
        student.grade = grade;
        student.grade++;
        student = new ArgTest8();
        student.grade = 80; // mainのkodaiには影響しない
    }
}
