package com.example.fiveth;

public class SetGradeSample2 {
    public static void main(String[] args) {
        CitStudent2 s1 = new CitStudent2("24G3999", "浅賀慎之助", 2);
        s1.addTestScore(80);
        s1.addTestScore(90);
        s1.setReport(85);
        s1.setExam(88);

        CitStudent2 s2 = new CitStudent2("24G3005", " 加賀山幸希", 1);
        s2.addTestScore(70);
        s2.addTestScore(75);
        s2.setReport(80);
        s2.setExam(78);


        CitStudent2 s3 = new CitStudent2();
        s3.setNo("24A3999"); 
        s3.setName("菅原直樹");
        s3.setYear(3);

        // 結果表示
        showGrade(s1);
        showGrade(s2);
        showGrade(s3);
    }

    public static void showGrade(CitStudent2 data) {
        System.out.printf(
            "No:%s　%s　(%s)　学年:%d　平均:%.1f　最終:%.1f　成績:%s　GPA:%.1f%n",
            data.getNo(),
            data.getName(),
            data.getDepartmentName(),
            data.getYear(),
            data.getAve(),
            data.getFinalScore(),
            data.getGradeSymbol(),
            data.getGPA()
        );
    }
}
