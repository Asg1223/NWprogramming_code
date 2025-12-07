package com.example.fiveth;
//課題O(40)
public class ArgsTest8 {
    int grade;

    public static void main(String[] args) {
        int i = 60;
        ArgsTest8 kodai = new ArgsTest8();
        kodai.setGrade(i);
        System.out.println("1: grade = " + kodai.grade + " i = " + i);

        kodai.setGrade(70);
        System.out.println("2: grade = " + kodai.grade + " i = " + i);
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
