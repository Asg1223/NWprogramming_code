package com.example.fiveth;

import java.util.ArrayList;
import java.util.List;

/**
 * 学年・セメスター・科目・成績を総合的に管理する学生クラス
 */
public class CitStudent3 {
    private String no;              // 学生番号
    private String name;            // 氏名
    private int currentYear;        // 現在の学年
    private int firstYear;          // 入学年度（下2桁）
    private String departmentCode;  // 学科コード
    private String departmentName;  // 学科名
    
    // セメスター情報のリスト（各学年に前期・後期）
    private List<Semester> semesters;

    // --- コンストラクタ ---
    public CitStudent3() {
        this.no = "";
        this.name = "";
        this.currentYear = 1;
        this.firstYear = 0;
        this.departmentCode = "";
        this.departmentName = "不明";
        this.semesters = new ArrayList<>();
    }

    public CitStudent3(String no, String name, int currentYear) {
        this();
        this.no = no;
        this.name = name;
        this.currentYear = currentYear;
        
        // 学生番号から入学年度を推定
        if (no.length() >= 2) {
            try {
                this.firstYear = Integer.parseInt(no.substring(0, 2));
            } catch (NumberFormatException e) {
                this.firstYear = 0;
            }
        }
        
        // 学科情報を抽出（簡易版）
        if (no.length() >= 4) {
            this.departmentCode = "" + no.charAt(2) + no.charAt(3);
            this.departmentName = getDepartmentName(departmentCode);
        }
    }

    // --- 学科名の簡易マッピング ---
    private String getDepartmentName(String code) {
        switch (code) {
            case "G1": return "情報工学科";
            case "G2": return "認知情報科学科";
            case "G3": return "高度応用情報科学科";
            case "A1": return "機械工学科";
            case "A4": return "電気電子工学科";
            default: return "不明";
        }
    }

    // --- セメスター取得（なければ作成） ---
    public Semester getSemester(int year, String period) {
        for (Semester sem : semesters) {
            if (sem.getYear() == year && sem.getPeriod().equals(period)) {
                return sem;
            }
        }
        // 存在しなければ新規作成
        Semester newSem = new Semester(year, period);
        semesters.add(newSem);
        return newSem;
    }

    // --- 便利メソッド: 科目追加 ---
    public void addCourse(int year, String period, String courseName) {
        getSemester(year, period).addCourse(courseName);
    }

    // --- 便利メソッド: 成績設定 ---
    public void setCourseGrade(int year, String period, String courseName, int grade) {
        getSemester(year, period).setCourseGrade(courseName, grade);
    }

    public void setCourseGrade(int year, String period, String courseName, String grade) {
        getSemester(year, period).setCourseGrade(courseName, grade);
    }

    // --- 便利メソッド: 小テスト・レポート・期末設定 ---
    public void addTestScore(int year, String period, String courseName, int score) {
        getSemester(year, period).addTestScore(courseName, score);
    }

    public void setReport(int year, String period, String courseName, int report) {
        getSemester(year, period).setReport(courseName, report);
    }

    public void setExam(int year, String period, String courseName, int exam) {
        getSemester(year, period).setExam(courseName, exam);
    }

    // --- 全体の GPA 計算（全セメスターの平均） ---
    public double getOverallGPA() {
        if (semesters.isEmpty()) return 0.0;
        
        double totalGPA = 0.0;
        int validSemesterCount = 0;
        
        for (Semester sem : semesters) {
            double semGPA = sem.getGPA();
            if (semGPA > 0) {
                totalGPA += semGPA;
                validSemesterCount++;
            }
        }
        
        return validSemesterCount > 0 ? totalGPA / validSemesterCount : 0.0;
    }

    // --- 全取得単位数 ---
    public int getTotalCredits() {
        int total = 0;
        for (Semester sem : semesters) {
            total += sem.getEarnedCredits();
        }
        return total;
    }

    // --- 学生情報の表示 ---
    public void printStudentInfo() {
        System.out.println("=".repeat(60));
        System.out.printf("学生番号: %s (%s, %d年度入学)%n", no, departmentName, 2000 + firstYear);
        System.out.printf("氏名: %s, 現在学年: %d年%n", name, currentYear);
        System.out.printf("全体GPA: %.2f, 総取得単位数: %d%n", getOverallGPA(), getTotalCredits());
        System.out.println("=".repeat(60));
        
        // セメスターごとの情報表示
        for (Semester sem : semesters) {
            sem.printSemesterInfo();
        }
    }

    // --- getter / setter ---
    public String getNo() { return no; }
    public String getName() { return name; }
    public int getCurrentYear() { return currentYear; }
    public String getDepartmentName() { return departmentName; }
    public List<Semester> getSemesters() { return semesters; }
    
    public void setName(String name) { this.name = name; }
    public void setCurrentYear(int year) { this.currentYear = year; }
}
