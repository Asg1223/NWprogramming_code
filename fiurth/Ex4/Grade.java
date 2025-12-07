package com.example.fourth.ex4;

public class Grade {
    // 各種点数
    private int high;     // 小テスト最高点
    private int low;      // 小テスト最低点
    private double ave;   // 小テスト平均
    private int total;    // 小テスト合計
    private int num;      // 小テスト受験回数

    private int report;   // レポート
    private int exam;     // 期末テスト

    // --- コンストラクタ ---
    public Grade() {
        high = -1;
        low = -1;
        ave = -1.0;
        total = -1;
        num = 0;
        report = -1;
        exam = -1;
    }

    // --- 小テスト関連 ---
    public void addScore(int score) {
        if (score < 0 || score > 100) {
            System.out.println("エラー: 点数が不正です。");
            return;
        }
        if (num == 0) {
            high = low = total = score;
            ave = score;
        } else {
            if (score > high) high = score;
            if (score < low) low = score;
            total += score;
            ave = (double) total / (num + 1);
        }
        num++;
    }

    public int getHigh() { return high; }
    public int getLow() { return low; }
    public double getAve() { return ave; }
    public int getTotal() { return total; }
    public int getNum() { return num; }

    // --- レポートと期末の設定 ---
    public void setReport(int r) {
        if (r < 0 || r > 100) {
            System.out.println("エラー: レポート点が不正です。");
            return;
        }
        report = r;
    }

    public void setExam(int e) {
        if (e < 0 || e > 100) {
            System.out.println("エラー: 期末点が不正です。");
            return;
        }
        exam = e;
    }

    public int getReport() { return report; }
    public int getExam() { return exam; }

    // --- 最終成績を算出 (期末60%, レポート30%, 小テスト10%) ---
    public double getFinalScore() {
        double s_test = (ave < 0 ? 0 : ave);
        double s_report = (report < 0 ? 0 : report);
        double s_exam = (exam < 0 ? 0 : exam);
        return s_exam * 0.6 + s_report * 0.3 + s_test * 0.1;
    }

    // --- 成績記号 (S/A/B/C/D) ---
    public String getGradeSymbol() {
        double fs = getFinalScore();
        if (fs >= 90) return "S";
        else if (fs >= 80) return "A";
        else if (fs >= 70) return "B";
        else if (fs >= 60) return "C";
        else return "D";
    }
}
