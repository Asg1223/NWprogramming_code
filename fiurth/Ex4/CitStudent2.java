package com.example.fourth.ex4;

public class CitStudent2 {
    private String no;         // 学生番号（チェックディジット付き8桁）
    private String name;       // 氏名
    private int year;          // 学年
    private int department;    // 学科 (1:情報工学科, 2:認知情報科学科, 3:高度応用情報科学科)
    private Grade gradeData;   // 成績データ

    // --- コンストラクタ ---
    public CitStudent2() {
        this.no = "";
        this.name = "";
        this.year = 0;
        this.department = 0;
        this.gradeData = new Grade();
    }

    public CitStudent2(String p_no, String p_name) {
        this();
        setNo(p_no);
        this.name = p_name;
    }

    public CitStudent2(String p_no, String p_name, int p_year) {
        this(p_no, p_name);
        this.year = p_year;
    }

    // ============================================================
    // setNo() チェックディジット付き
    // ============================================================
    public void setNo(String p_no) {
        // 形式チェック
        if (p_no.length() < 7) {
            System.out.println("エラー: 学生番号が短すぎます (" + p_no + ")");
            return;
        }

        // チェックディジットの有無を確認
        if (p_no.length() == 8) {
            // 検証付き番号
            String core = p_no.substring(0, 7);
            int checkDigit = calcCheckDigit(core);
            int lastDigit = Character.getNumericValue(p_no.charAt(7));
            if (checkDigit != lastDigit) {
                System.out.println("エラー: チェックディジットが不正 (" + p_no + ")");
                return;
            }
            this.no = p_no;
        } else if (p_no.length() == 7) {
            // チェックディジットなし → 自動付加
            int cd = calcCheckDigit(p_no);
            this.no = p_no + cd;
            System.out.println("チェックディジットを追加: " + this.no);
        } else {
            System.out.println("エラー: 学生番号が不正な長さ (" + p_no + ")");
            return;
        }

        // 学科を設定（3文字目から）
        char c = this.no.charAt(2);
        if (Character.isDigit(c)) {
            department = Character.getNumericValue(c);
        } else if (Character.isLetter(c)) {
            // A〜H → 10〜17 に対応
            department = Character.toUpperCase(c) - 'A' + 10;
        } else {
            department = 0;
        }
    }

    // --- チェックディジット計算 ---
    private int calcCheckDigit(String core) {
        int sum = 0;
        for (int i = 0; i < core.length(); i++) {
            char ch = core.charAt(i);
            int val;
            if (Character.isDigit(ch)) val = ch - '0';
            else val = Character.toUpperCase(ch) - 'A' + 10;
            sum += val;
        }
        return sum % 10;
    }

    // ============================================================
    // その他のメソッド
    // ============================================================

    public String getNo() { return no; }
    public void setName(String p_name) { name = p_name; }
    public String getName() { return name; }
    public void setYear(int p_year) { year = p_year; }
    public int getYear() { return year; }

    public String getDepartmentName() {
        switch (department) {
            case 1: return "情報工学科";
            case 2: return "認知情報科学科";
            case 3: return "高度応用情報科学科";
            default: return "その他";
        }
    }

    public void addTestScore(int s) { gradeData.addScore(s); }
    public void setReport(int r) { gradeData.setReport(r); }
    public void setExam(int e) { gradeData.setExam(e); }

    public double getAve() { return gradeData.getAve(); }
    public double getFinalScore() { return gradeData.getFinalScore(); }
    public String getGradeSymbol() { return gradeData.getGradeSymbol(); }

    // GPA（1科目分）
    public double getGPA() {
        switch (getGradeSymbol()) {
            case "S": return 4.0;
            case "A": return 3.0;
            case "B": return 2.0;
            case "C": return 1.0;
            default:  return 0.0;
        }
    }
}
