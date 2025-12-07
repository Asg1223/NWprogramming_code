package com.example.fourth.H;

public class CitStudent2 {
    String no;           // 学生番号
    String name;         // 氏名
    int year;            // 学年
    int grade;           // 成績
    int department;      // 学科 (1:情報工学科, 2:認知情報科学科, 3:高度応用情報科学科)

    // --- 学生番号を設定 ---
    public void setNo(String p_no) {
        no = p_no;

        // 学生番号から学科を自動判定（左から4文字目）
        int dep = Integer.parseInt("" + no.charAt(3));
        department = dep;
    }

    public String getNo() {
        return no;
    }

    // --- 氏名の設定と取得 ---
    public void setName(String p_name) {
        name = p_name;
    }

    public String getName() {
        return name;
    }

    // --- 学年の設定と取得 ---
    public void setYear(int p_year) {
        year = p_year;
    }

    public int getYear() {
        return year;
    }

    // --- 成績の設定と取得 ---
    public void setGrade(int p_grade) {
        grade = p_grade;
    }

    public int getGrade() {
        return grade;
    }

    // --- 学科名の取得（読み取り専用）---
    public String getDepartmentName() {
        switch (department) {
            case 1: return "情報工学科";
            case 2: return "認知情報科学科";
            case 3: return "高度応用情報科学科";
            default: return "不明";
        }
    }

    // --- 成績ランクを取得（S/A/B/C/D）---
    public String getGradeSymbol() {
        if (grade >= 90) return "S";
        else if (grade >= 80) return "A";
        else if (grade >= 70) return "B";
        else if (grade >= 60) return "C";
        else return "D";
    }
}
