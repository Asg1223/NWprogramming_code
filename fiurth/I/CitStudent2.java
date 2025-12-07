package com.example.fourth.I;

public class CitStudent2 {
    String no;           // 学生番号
    String name;         // 氏名
    int year;            // 学年
    int grade;           // 成績
    int department;      // 学科 (1:情報工学科, 2:認知情報科学科, 3:高度応用情報科学科)

    // --- コンストラクタ1（引数なし）---
    CitStudent2() {
        no = "";
        name = "";
        year = 0;
        grade = 0;
        department = 0;
    }

    // --- コンストラクタ2（学生番号と氏名）---
    CitStudent2(String p_no, String p_name) {
        no = p_no;
        name = p_name;
        year = 0;
        grade = 0;
        // 学生番号から学科を自動設定
        department = Integer.parseInt("" + no.charAt(3));
    }

    // --- コンストラクタ3（学生番号・氏名・学年）---
    CitStudent2(String p_no, String p_name, int p_year) {
        no = p_no;
        name = p_name;
        year = p_year;
        grade = 0;
        department = Integer.parseInt("" + no.charAt(3));
    }

    // --- set/get メソッド ---
    public void setNo(String p_no) { no = p_no; }
    public String getNo() { return no; }

    public void setName(String p_name) { name = p_name; }
    public String getName() { return name; }

    public void setYear(int p_year) { year = p_year; }
    public int getYear() { return year; }

    public void setGrade(int p_grade) { grade = p_grade; }
    public int getGrade() { return grade; }

    // --- 学科名と成績記号 ---
    public String getDepartmentName() {
        switch (department) {
            case 1: return "情報工学科";
            case 2: return "認知情報科学科";
            case 3: return "高度応用情報科学科";
            default: return "不明";
        }
    }

    public String getGradeSymbol() {
        if (grade >= 90) return "S";
        else if (grade >= 80) return "A";
        else if (grade >= 70) return "B";
        else if (grade >= 60) return "C";
        else return "D";
    }
}
