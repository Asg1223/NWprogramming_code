package com.example.fiveth;

import java.util.HashMap;
import java.util.Map;

public class CitStudent2 {
    private String no;              // 学生番号（チェックディジット付き）
    private String name;            // 氏名
    private int year;               // 学年
    private int firstYear;          // 入学年度（下2桁）
    private String departmentCode;  // 学科コード（例: "G1"）
    private String departmentName;  // 学科名
    private Grade gradeData;        // 成績データ

    // --- 学科コードと名称の対応表 ---
    private static final Map<String, String> DEPT_MAP = new HashMap<>();
    static {
        DEPT_MAP.put("A1", "機械工学科");
        DEPT_MAP.put("A2", "機械電子創成工学科");
        DEPT_MAP.put("A3", "先端材料工学科");
        DEPT_MAP.put("A4", "電気電子工学科");
        DEPT_MAP.put("A5", "情報通信システム工学科");
        DEPT_MAP.put("A6", "応用化学科");
        DEPT_MAP.put("A7", "宇宙・半導体工学科");

        DEPT_MAP.put("B1", "建築学科");
        DEPT_MAP.put("B2", "都市環境工学科");
        DEPT_MAP.put("B3", "デザイン科学科");

        DEPT_MAP.put("C1", "未来ロボティクス学科");
        DEPT_MAP.put("C2", "生命科学科");
        DEPT_MAP.put("C3", "知能メディア工学科");

        DEPT_MAP.put("G1", "情報工学科（2024年度入学～）");
        DEPT_MAP.put("G2", "認知情報科学科");
        DEPT_MAP.put("G3", "高度応用情報科学科");

        DEPT_MAP.put("K1", "デジタル変革科学科");
        DEPT_MAP.put("K2", "経営デザイン科学科");

        DEPT_MAP.put("31", "情報工学科（～2023年度入学）");
        DEPT_MAP.put("32", "情報ネットワーク学科");
        DEPT_MAP.put("41", "経営情報科学科");
        DEPT_MAP.put("42", "プロジェクトマネジメント学科");
        DEPT_MAP.put("43", "金融・経営リスク科学科");
    }

    // --- コンストラクタ ---
    public CitStudent2() {
        this.no = "";
        this.name = "";
        this.year = 0;
        this.firstYear = 0;
        this.departmentCode = "";
        this.departmentName = "不明";
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
    // setNo(): 学生番号・チェックディジット検証・学科判定
    // ============================================================
    public void setNo(String p_no) {
        if (p_no == null || p_no.length() < 7) {
            System.out.println("エラー: 学生番号が短すぎます (" + p_no + ")");
            return;
        }

        // --- 入学年度（先頭2桁）推定 ---
        try {
            this.firstYear = Integer.parseInt(p_no.substring(0, 2));  // 例: "24" → 2024年度入学
        } catch (NumberFormatException e) {
            System.out.println("警告: 入学年度を取得できません (" + p_no + ")");
        }

        // --- チェックディジット処理 ---
        if (p_no.length() == 8) {
            String core = p_no.substring(0, 7);
            int cd = calcCheckDigit(core);
            int last = Character.getNumericValue(p_no.charAt(7));
            if (cd != last) {
                System.out.println("エラー: チェックディジットが不正です (" + p_no + ")");
                return;
            }
            this.no = p_no;
        } else if (p_no.length() == 7) {
            int cd = calcCheckDigit(p_no);
            this.no = p_no + cd;
            System.out.println("チェックディジットを追加: " + this.no);
        } else {
            System.out.println("エラー: 学生番号が不正な長さ (" + p_no + ")");
            return;
        }

        // --- 学科コード抽出 ---
        if (no.length() >= 4) {
            departmentCode = "" + no.charAt(2) + no.charAt(3);

            // --- 年度別マッピング ---
            if (firstYear >= 24) {
                if (departmentCode.equals("31")) departmentCode = "G1";
                else if (departmentCode.equals("32")) departmentCode = "G2";
            }
            if (firstYear >= 25) {
                if (departmentCode.equals("41")) departmentCode = "K1";
                else if (departmentCode.equals("42") || departmentCode.equals("43")) departmentCode = "K2";
            }
            if (firstYear <= 23) {
                if (departmentCode.equals("G1")) departmentCode = "31";
                else if (departmentCode.equals("G2")) departmentCode = "32";
            }
            if (firstYear <= 24) {
                if (departmentCode.equals("K1")) departmentCode = "41";
                else if (departmentCode.equals("K2")) departmentCode = "42";
            }

            departmentName = DEPT_MAP.getOrDefault(departmentCode, "不明");
        } else {
            departmentCode = "";
            departmentName = "不明";
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

    // --- 成績関連 ---
    public void addTestScore(int s) {
        if (gradeData == null) gradeData = new Grade();
        gradeData.addScore(s);
    }

    public void setReport(int r) {
        if (gradeData == null) gradeData = new Grade();
        gradeData.setReport(r);
    }

    public void setExam(int e) {
        if (gradeData == null) gradeData = new Grade();
        gradeData.setExam(e);
    }

    public double getAve() { return gradeData.getAve(); }
    public double getFinalScore() { return gradeData.getFinalScore(); }
    public String getGradeSymbol() { return gradeData.getGradeSymbol(); }
    
    // Grade に委譲（保留・欠席対応）
    public double getGPA() {
        return gradeData.getGPA();
    }
    
    // Grade の setGrade メソッドへのアクセス
    public void setGrade(String g) {
        if (gradeData == null) gradeData = new Grade();
        gradeData.setGrade(g);
    }
    
    public void setGrade(int g) {
        if (gradeData == null) gradeData = new Grade();
        gradeData.setGrade(g);
    }
    
    public String getStringGrade() {
        return gradeData.getStringGrade();
    }

    // --- getter ---
    public String getNo() { return no; }
    public String getName() { return name; }
    public String getDepartmentCode() { return departmentCode; }
    public String getDepartmentName() { return departmentName; }
    public int getYear() { return year; }
    public int getFirstYear() { return firstYear; }

    public void setName(String name) { this.name = name; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return String.format(
            "学生番号: %s (%s, %d年度入学), 氏名: %s, 学年: %d, GPA: %.2f",
            no, departmentName, 2000 + firstYear, name, year, getGPA()
        );
    }
}