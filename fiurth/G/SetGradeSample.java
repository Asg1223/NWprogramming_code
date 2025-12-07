package com.example.fourth.G;

public class SetGradeSample {
    public static void main(String[] args) {
        CitStudent kodai = new CitStudent();

        kodai.no = "24G3999";
        kodai.name = "工大 太郎";
        kodai.year = 2;
        kodai.grade = 85;
        kodai.department = Integer.parseInt("" + kodai.no.charAt(3));

        showGrade(kodai);
    }

    public static void showGrade(CitStudent data) {
        String deptName;
        switch (data.department) {
            case 1: deptName = "情報工学科"; break;
            case 2: deptName = "認知情報科学科"; break;
            case 3: deptName = "高度応用情報科学科"; break;
            default: deptName = "不明";
        }

        String gradeSymbol;
        if (data.grade >= 90) gradeSymbol = "S";
        else if (data.grade >= 80) gradeSymbol = "A";
        else if (data.grade >= 70) gradeSymbol = "B";
        else if (data.grade >= 60) gradeSymbol = "C";
        else gradeSymbol = "D";

        System.out.println("No: " + data.no + " " + data.name + " (" + deptName + ") 成績: " + gradeSymbol);
    }
}

