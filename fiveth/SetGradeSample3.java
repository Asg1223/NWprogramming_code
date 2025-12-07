package com.example.fiveth;

public class SetGradeSample3 {
    public static void main(String[] args) {
        // 通常の成績
        CitStudent2 s1 = new CitStudent2("24G3999", "浅賀慎之助", 2);
        s1.addTestScore(80);
        s1.addTestScore(90);
        s1.setReport(85);
        s1.setExam(88);

        // 保留（String型で設定）
        CitStudent2 s2 = new CitStudent2("24G3005", "加賀山幸希", 1);
        s2.setGrade("保留");

        // 欠席（int型で設定: -2）
        CitStudent2 s3 = new CitStudent2("24A3999", "菅原直樹", 3);
        s3.setGrade(-2);

        // 直接点数を設定（String型）
        CitStudent2 s4 = new CitStudent2("24G1234", "田中太郎", 2);
        s4.setGrade("85");

        // 直接点数を設定（int型）
        CitStudent2 s5 = new CitStudent2("24G5678", "佐藤花子", 1);
        s5.setGrade(92);

        // 保留（int型で設定: -1）
        CitStudent2 s6 = new CitStudent2("24A1111", "鈴木次郎", 4);
        s6.setGrade(-1);

        // 結果表示
        System.out.println("=== 成績一覧（保留・欠席対応版） ===");
        showGrade(s1);
        showGrade(s2);
        showGrade(s3);
        showGrade(s4);
        showGrade(s5);
        showGrade(s6);
    }

    public static void showGrade(CitStudent2 data) {
        System.out.printf(
            "No:%s　%s　(%s)　学年:%d　平均:%.1f　最終:%.1f　成績:%s　成績:%s　GPA:%.1f%n",
            data.getNo(),
            data.getName(),
            data.getDepartmentName(),
            data.getYear(),
            data.getAve(),
            data.getFinalScore(),
            data.getGradeSymbol(),
            data.getStringGrade(),
            data.getGPA()
        );
    }
}
