package com.example.fourth.ex4;

public class SetGradeSample2 {
    public static void main(String[] args) {
        CitStudent2 s1 = new CitStudent2("24G1999", "Asg", 2); 
        s1.addTestScore(90);
        s1.addTestScore(85);
        s1.setReport(88);
        s1.setExam(92);

        CitStudent2 s2 = new CitStudent2("24A4999", "Shin", 1); 
        s2.addTestScore(70);
        s2.addTestScore(65);
        s2.setReport(72);
        s2.setExam(68);

        CitStudent2 s3 = new CitStudent2("2431999", "Tas", 3); 
        s3.addTestScore(90);
        s3.setReport(85);
        s3.setExam(93);

        show(s1);
        show(s2);
        show(s3);
    }

    public static void show(CitStudent2 s) {
        System.out.printf(
            "No:%s　%s　(%s)　学年:%d　平均:%.1f　最終:%.1f　成績:%s　GPA:%.1f%n",
            s.getNo(),
            s.getName(),
            s.getDepartmentName(),
            s.getYear(),
            s.getAve(),
            s.getFinalScore(),
            s.getGradeSymbol(),
            s.getGPA()
        );
    }
}
