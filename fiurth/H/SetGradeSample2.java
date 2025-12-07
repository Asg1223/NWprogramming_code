package com.example.fourth.H;

public class SetGradeSample2 {
    public static void main(String[] args) {
        // CitStudent2 クラスのオブジェクトを作成
        CitStudent2 kodai = new CitStudent2();

        // setメソッドを使って値を設定
        kodai.setNo("24G3999");
        kodai.setName("工大 太郎");
        kodai.setYear(2);
        kodai.setGrade(85);

        // 結果を表示
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

