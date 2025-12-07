package com.example.fiveth;

public class Grade {
    // 各種点数
    private int high;     // 小テスト最高点
    private int low;      // 小テスト最低点
    private double ave;   // 小テスト平均
    private int total;    // 小テスト合計
    private int num;      // 小テスト受験回数

    private int report;   // レポート
    private int exam;     // 期末テスト
    private int grade;    // 成績（-2=欠席, -1=保留, 0～100=点数, -999=未設定）

    // --- コンストラクタ ---
    public Grade() {
        high = -1;
        low = -1;
        ave = -1.0;
        total = -1;
        num = 0;
        report = -1;
        exam = -1;
        grade = -999;  // 未設定
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
        // grade が設定されている場合は優先
        if (grade != -999) {
            if (grade == -1) return "保留";
            if (grade == -2) return "欠席";
            // 0～100 の場合は変換
            if (grade >= 90) return "S";
            else if (grade >= 80) return "A";
            else if (grade >= 70) return "B";
            else if (grade >= 60) return "C";
            else if (grade >= 0) return "D";
        }
        
        // 通常の最終成績から計算
        double fs = getFinalScore();
        if (fs >= 90) return "S";
        else if (fs >= 80) return "A";
        else if (fs >= 70) return "B";
        else if (fs >= 60) return "C";
        else return "D";
    }

    // --- 課題9/10: grade を String 型で設定（互換性のため） ---
    public void setGrade(String g) {
        if (g == null) {
            System.out.println("エラー: grade が null です。");
            return;
        }
        
        if (g.equals("保留")) {
            grade = -1;
            return;
        }
        
        if (g.equals("欠席")) {
            grade = -2;
            return;
        }
        
        // 数値の場合は 0～100 のチェック
        try {
            int val = Integer.parseInt(g);
            if (val >= 0 && val <= 100) {
                grade = val;
            } else {
                System.out.println("エラー: grade は 0～100 の範囲で指定してください。");
            }
        } catch (NumberFormatException e) {
            System.out.println("エラー: grade は数値、\"保留\"、\"欠席\" のいずれかを指定してください。");
        }
    }

    // --- 課題9/10: grade を int 型で設定 ---
    public void setGrade(int g) {
        if (g == -1) {
            grade = -1;  // 保留
        } else if (g == -2) {
            grade = -2;  // 欠席
        } else if (g >= 0 && g <= 100) {
            grade = g;
        } else {
            System.out.println("エラー: grade は 0～100、-1（保留）、-2（欠席）のいずれかを指定してください。");
        }
    }

    // --- 課題9: grade を文字列で返す ---
    public String getStringGrade() {
        if (grade == -999) return "未設定";
        if (grade == -1) return "保留";
        if (grade == -2) return "欠席";
        return String.valueOf(grade);
    }

    // --- GPA 計算（保留・欠席を考慮） ---
    public double getGPA() {
        String symbol = getGradeSymbol();
        switch (symbol) {
            case "S": return 4.0;
            case "A": return 3.0;
            case "B": return 2.0;
            case "C": return 1.0;
            case "保留": return -1.0;  // GPA 計算から除外する場合
            case "欠席": return 0.0;    // 0 として扱う
            default: return 0.0;
        }
    }
}