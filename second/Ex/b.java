package com.example.second.Ex;
import java.util.*;

public class b {
static final double EPS = 1e-9;

    public static void main(String[] args) {
        // キャッシュ：ソート済み桁列（キー） -> 判定結果
        Map<String, Boolean> cache = new HashMap<>();

        int count = 0;
        List<Integer> okNumbers = new ArrayList<>();

        for (int n = 0; n <= 9999; n++) {
            String s = String.format("%04d", n);
            char[] arr = s.toCharArray();
            // key: ソートした桁列（マルチセットを表す）
            char[] sorted = arr.clone();
            Arrays.sort(sorted);
            String key = new String(sorted);

            Boolean possible = cache.get(key);
            if (possible == null) {
                // 新しいマルチセットについて判定
                int[] digits = new int[4];
                for (int i = 0; i < 4; i++) digits[i] = sorted[i] - '0';
                possible = reachableForMultiset(digits);
                cache.put(key, possible);
            }

            if (possible) {
                count++;
                okNumbers.add(n);
            }
        }

        System.out.println("\r\n" + //
                        "10 になる数字の数 (0..9999):" + count);
        // 必要ならリストを出力（コメント解除）
        // System.out.println(okNumbers);
    }

    // マルチセット（ソート済み桁配列）について、任意の順列で10を作れるか判定
    static boolean reachableForMultiset(int[] sortedDigits) {
        // 全ての順列（重複を避ける）を生成
        List<int[]> perms = generateUniquePermutations(sortedDigits);

        // すべての順列に対して、演算子の全組合せ・括弧形を試す
        for (int[] perm : perms) {
            double a = perm[0], b = perm[1], c = perm[2], d = perm[3];
            // 4^3 = 64 operator combinations
            for (int opMask = 0; opMask < 64; opMask++) {
                int op1 = (opMask >> 0) & 3;
                int op2 = (opMask >> 2) & 3;
                int op3 = (opMask >> 4) & 3;

                // 5つの括弧パターンを評価
                // 1. ((a op1 b) op2 c) op3 d
                Double r1 = applyOp(a, op1, b);
                if (r1 != null) {
                    Double r12 = applyOp(r1, op2, c);
                    if (r12 != null) {
                        Double r123 = applyOp(r12, op3, d);
                        if (r123 != null && closeTo10(r123)) return true;
                    }
                }

                // 2. (a op1 (b op2 c)) op3 d
                Double r2 = applyOp(b, op2, c);
                if (r2 != null) {
                    Double r21 = applyOp(a, op1, r2);
                    if (r21 != null) {
                        Double r211 = applyOp(r21, op3, d);
                        if (r211 != null && closeTo10(r211)) return true;
                    }
                }

                // 3. a op1 ((b op2 c) op3 d)
                if (r2 != null) {
                    Double r22 = applyOp(r2, op3, d);
                    if (r22 != null) {
                        Double r31 = applyOp(a, op1, r22);
                        if (r31 != null && closeTo10(r31)) return true;
                    }
                }

                // 4. a op1 (b op2 (c op3 d))
                Double r3 = applyOp(c, op3, d);
                if (r3 != null) {
                    Double r41 = applyOp(b, op2, r3);
                    if (r41 != null) {
                        Double r411 = applyOp(a, op1, r41);
                        if (r411 != null && closeTo10(r411)) return true;
                    }
                }

                // 5. (a op1 b) op2 (c op3 d)
                if (r1 == null) r1 = applyOp(a, op1, b);
                Double r5 = applyOp(c, op3, d);
                if (r1 != null && r5 != null) {
                    Double r51 = applyOp(r1, op2, r5);
                    if (r51 != null && closeTo10(r51)) return true;
                }
            }
        }
        return false;
    }

    // double 結果が 10 に近いか
    static boolean closeTo10(double v) {
        return Math.abs(v - 10.0) < EPS;
    }

    // op: 0:+ 1:- 2:* 3:/
    static Double applyOp(Double a, int op, Double b) {
        if (a == null || b == null) return null;
        switch (op) {
            case 0: return a + b;
            case 1: return a - b;
            case 2: return a * b;
            case 3:
                if (Math.abs(b) < EPS) return null;
                return a / b;
        }
        return null;
    }

    // 重複を避けて順列を生成（ソート済み配列を入力）
    static List<int[]> generateUniquePermutations(int[] sorted) {
        List<int[]> res = new ArrayList<>();
        boolean[] used = new boolean[sorted.length];
        int[] temp = new int[sorted.length];
        permDfs(sorted, used, temp, 0, res);
        return res;
    }

    static void permDfs(int[] sorted, boolean[] used, int[] temp, int depth, List<int[]> res) {
        if (depth == sorted.length) {
            res.add(temp.clone());
            return;
        }
        for (int i = 0; i < sorted.length; i++) {
            if (used[i]) continue;
            // 同じ値の要素を先に使っていないならスキップ (重複回避の典型)
            if (i > 0 && sorted[i] == sorted[i-1] && !used[i-1]) continue;
            used[i] = true;
            temp[depth] = sorted[i];
            permDfs(sorted, used, temp, depth + 1, res);
            used[i] = false;
        }
    }

}
/*
解説
<全体の流れ>

- 0〜9999 の全ての数字について4 桁に分解（先頭ゼロあり）
- 数字の並び順をすべて生成（全 24 通り）
-括弧のつけ方（5 パターン）
-演算子の組み合わせ（64 通り）を総当りして「10 が作れるか」をチェックする。
-10 が作れるものだけ数える

< 関数と役割まとめ>
 canMake10(int a, int b, int c, int d)

4つの数字 a,b,c,d を使って 10 を作れるかどうか判定する関数
数字はすでに並び替え済み（メイン側で permutation して渡す）。

<この関数が行うこと>

4つの数字に対して以下すべてを試す
演算子 3 つを選ぶ（+, -, *, / の組）

<括弧の配置 5パターン>

((a op b) op c) op d
(a op (b op c)) op d
a op ((b op c) op d)
a op (b op (c op d))
(a op b) op (c op d)


計算中に 0 除算が起きたら除外

結果が 10 と誤差 1e-6 以内なら成功として true を返す

 apply(double x, double y, int op)

演算子 op（0:+, 1:-, 2:*, 3:/）を指定して
x op y を返す。

/ のときは y==0 をチェックして NaN を返す。

 permute(int[] nums, int index)

4 桁の数字を 全ての並び順（24通り）に並べ替える。再帰で実装。

すべての順列について canMake10(nums[0], nums[1], nums[2], nums[3]) を呼び出す。
solve10(int n)
n（0〜9999）を扱う。
1000,100,10,1 の4桁に分解
a = n / 1000
b = (n / 100) % 10
c = (n / 10) % 10
d = n % 10
4桁配列を作る
int[] nums = { a, b, c, d };

permute を呼び出して数字の順番を自由に並べ替えどれか 1 つでも 10 が作れたら true

メイン部分
int total = 0;
for (int i = 0; i <= 9999; i++) {
    if (solve10(i)) {
        total++;
    }
}
System.out.println(total);


0〜9999 のすべての数に対して solve10 を呼び出す

作れる数をカウントして表示する

<ポイント>
● 並び替え自由

例：1234 → 1,2,3,4 の並びだけでなく
4,3,2,1 や 2,4,1,3 などすべて使える。

● 四則演算を3回使う

数字と数字の間には必ず演算子を入れる。

● 括弧のつけ方は5通り

数学的に可能な二項演算の構造は5種類しかない。

● 割り算は危険

0 除算を防ぐためにチェック。

● double を使う

浮動小数点誤差を考慮し、
|value - 10| < 1e-6 で判定。
*/