package com.example.fourth.I;

public class SetGradeSample2 {
    public static void main(String[] args) {

        // --- コンストラクタ2を使用（学生番号と氏名を同時に設定）---
        CitStudent2 kodai = new CitStudent2("24G3999", "工大 太郎");

        // --- 学年と成績だけ追加で設定 ---
        kodai.setYear(2);
        kodai.setGrade(95);

        // --- 表示 ---
        showGrade(kodai);
    }

    public static void showGrade(CitStudent2 data) {
        System.out.println(
            "No: " + data.getNo() + "　" +
            data.getName() + "　" +
            "(" + data.getDepartmentName() + ")　" +
            "学年: " + data.getYear() + "　" +
            "成績: " + data.getGradeSymbol()
        );
    }
}
