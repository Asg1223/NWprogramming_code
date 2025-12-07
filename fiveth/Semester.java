package com.example.fiveth;

import java.util.HashMap;
import java.util.Map;

/**
 * セメスター（前期・後期）の科目と成績を管理するクラス
 */
public class Semester {
    private int year;           // 学年（1～4）
    private String period;      // 期間（"前期" or "後期"）
    private Map<String, Grade> courses;  // 科目名 → Grade のマップ

    // --- コンストラクタ ---
    public Semester(int year, String period) {
        this.year = year;
        this.period = period;
        this.courses = new HashMap<>();
    }

    // --- 科目の追加 ---
    public void addCourse(String courseName) {
        if (!courses.containsKey(courseName)) {
            courses.put(courseName, new Grade());
        }
    }

    // --- 科目の Grade オブジェクトを取得 ---
    public Grade getGrade(String courseName) {
        if (!courses.containsKey(courseName)) {
            addCourse(courseName);
        }
        return courses.get(courseName);
    }

    // --- 科目に成績を設定 ---
    public void setCourseGrade(String courseName, int grade) {
        Grade g = getGrade(courseName);
        g.setGrade(grade);
    }

    public void setCourseGrade(String courseName, String grade) {
        Grade g = getGrade(courseName);
        g.setGrade(grade);
    }

    // --- 科目に小テスト・レポート・期末を設定 ---
    public void addTestScore(String courseName, int score) {
        getGrade(courseName).addScore(score);
    }

    public void setReport(String courseName, int report) {
        getGrade(courseName).setReport(report);
    }

    public void setExam(String courseName, int exam) {
        getGrade(courseName).setExam(exam);
    }

    // --- このセメスターの GPA 計算（保留は除外、欠席は 0） ---
    public double getGPA() {
        if (courses.isEmpty()) return 0.0;
        
        double totalGPA = 0.0;
        int count = 0;
        
        for (Grade g : courses.values()) {
            double gpa = g.getGPA();
            if (gpa >= 0) {  // 保留（-1.0）は除外
                totalGPA += gpa;
                count++;
            }
        }
        
        return count > 0 ? totalGPA / count : 0.0;
    }

    // --- 取得単位数（C 以上） ---
    public int getEarnedCredits() {
        int credits = 0;
        for (Grade g : courses.values()) {
            String symbol = g.getGradeSymbol();
            if (symbol.equals("S") || symbol.equals("A") || 
                symbol.equals("B") || symbol.equals("C")) {
                credits++;
            }
        }
        return credits;
    }

    // --- セメスター情報の表示 ---
    public void printSemesterInfo() {
        System.out.printf("【%d年次 %s】 GPA: %.2f, 取得単位: %d%n", 
                          year, period, getGPA(), getEarnedCredits());
        
        for (Map.Entry<String, Grade> entry : courses.entrySet()) {
            String courseName = entry.getKey();
            Grade grade = entry.getValue();
            System.out.printf("  %-20s : 成績=%s (点数=%s), GPA=%.1f%n",
                courseName, 
                grade.getGradeSymbol(), 
                grade.getStringGrade(),
                grade.getFinalScore(),
                grade.getGPA()
            );
        }
    }

    // --- getter ---
    public int getYear() { return year; }
    public String getPeriod() { return period; }
    public Map<String, Grade> getCourses() { return courses; }
}
