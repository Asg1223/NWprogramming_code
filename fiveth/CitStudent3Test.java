package com.example.fiveth;

public class CitStudent3Test {
    public static void main(String[] args) {
        // 学生データ作成
        CitStudent3 student = new CitStudent3("24G3999", "浅賀慎之助", 2);
        
        System.out.println("=== CitStudent3 クラス動作確認 ===\n");
        
        // --- 1年次前期の成績 ---
        student.addCourse(1, "前期", "ICT基礎");
        student.addTestScore(1, "前期", "ICT基礎", 85);
        student.addTestScore(1, "前期", "ICT基礎", 90);
        student.setReport(1, "前期", "ICT基礎", 88);
        student.setExam(1, "前期", "ICT基礎", 92);

        student.addCourse(1, "前期", "線形代数");
        student.setCourseGrade(1, "前期", "線形代数", 85);
        
        student.addCourse(1, "前期", "微分積分");
        student.setCourseGrade(1, "前期", "微分積分", 78);
        
        // --- 1年次後期の成績 ---
        student.addCourse(1, "後期", "データ構造とアルゴリズム");
        student.addTestScore(1, "後期", "データ構造とアルゴリズム", 80);
        student.addTestScore(1, "後期", "データ構造とアルゴリズム", 88);
        student.setReport(1, "後期", "データ構造とアルゴリズム", 85);
        student.setExam(1, "後期", "データ構造とアルゴリズム", 90);

        student.addCourse(1, "後期", "離散数学");
        student.setCourseGrade(1, "後期", "離散数学", 92);
        
        student.addCourse(1, "後期", "数理モデリング");
        student.setCourseGrade(1, "後期", "数理モデリング", "欠席");  // String型で欠席
        
        // --- 2年次前期の成績 ---
        student.addCourse(2, "前期", "情報数学");
        student.addTestScore(2, "前期", "情報数学", 90);
        student.addTestScore(2, "前期", "情報数学", 95);
        student.setReport(2, "前期", "情報数学", 92);
        student.setExam(2, "前期", "情報数学", 94);

        student.addCourse(2, "前期", "データベース");
        student.setCourseGrade(2, "前期", "データベース", 88);
        
        student.addCourse(2, "前期", "ネットワーク");
        student.setCourseGrade(2, "前期", "ネットワーク", -1);  // int型で保留
        
        // --- 2年次後期の成績 ---
        student.addCourse(2, "後期", "オペレーティングシステム");
        student.setCourseGrade(2, "後期", "オペレーティングシステム", 82);
        
        student.addCourse(2, "後期", "データサイエンス演習");
        student.addTestScore(2, "後期", "データサイエンス演習", 85);
        student.addTestScore(2, "後期", "データサイエンス演習", 88);
        student.setReport(2, "後期", "データサイエンス演習", 90);
        student.setExam(2, "後期", "データサイエンス演習", 87);

        // --- 学生情報の表示 ---
        student.printStudentInfo();
        
        // --- 追加: 複数学生のGPA比較 ---
        System.out.println("\n=== 複数学生のGPA比較 ===");
        
        CitStudent3 student2 = new CitStudent3("24G3005", "加賀山幸希", 1);
        student2.addCourse(1, "前期", "ICT基礎");
        student2.setCourseGrade(1, "前期", "ICT基礎", 95);
        student2.addCourse(1, "前期", "線形代数");
        student2.setCourseGrade(1, "前期", "線形代数", 90);
        
        CitStudent3 student3 = new CitStudent3("24A3999", "菅原直樹", 3);
        student3.addCourse(3, "前期", "機械力学");
        student3.setCourseGrade(3, "前期", "機械力学", 70);
        student3.addCourse(3, "前期", "熱力学");
        student3.setCourseGrade(3, "前期", "熱力学", "保留");
        
        showSimpleInfo(student);
        showSimpleInfo(student2);
        showSimpleInfo(student3);
        System.out.println();
    }
    
    private static void showSimpleInfo(CitStudent3 s) {
        System.out.printf("%s (%s) - 学年:%d年, 全体GPA:%.2f, 取得単位:%d%n",
            s.getName(), s.getNo(), s.getCurrentYear(), 
            s.getOverallGPA(), s.getTotalCredits());
    }
}
