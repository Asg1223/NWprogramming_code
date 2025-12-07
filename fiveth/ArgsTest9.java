package com.example.fiveth;
//課題P(42)
public class ArgsTest9 {
    int grade;
    static int count = 0;

    public static void setGrade(ArgsTest9 student, int g) {
        student.grade = g;
        student.grade++;
        student = new ArgsTest9();
        student.grade = 70;
    }

    ArgsTest9() {
        count++;
    }
}
