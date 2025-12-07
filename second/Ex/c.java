
package com.example.second.Ex;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

public class c {

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("学生番号を入力（17進数として解釈します, 0-9,A-G）: ");
        String input = sc.next().trim();
        sc.close();

        // 大文字化して余分な空白を除去
        input = input.toUpperCase(Locale.ROOT);

        // 入力検査（17進で許される文字か）
        if (!input.matches("[0-9A-G]+")) {
            System.err.println("入力に 0-9 または A-G 以外の文字が含まれています。");
            return;
        }

        // BigIntegerで17進数としてパース
        BigInteger value;
        try {
            value = new BigInteger(input, 17);
        } catch (Exception e) {
            System.err.println("17進数として解析できませんでした: " + e.getMessage());
            return;
        }

        System.out.println("17進数としての値 = " + value.toString());

        if (value.compareTo(BigInteger.ONE) <= 0) {
            System.out.println("値が1以下のため素因数分解はありません。");
            return;
        }

        // 素因数分解
        Map<BigInteger, Integer> factors = factor(value);

        // 出力（昇順に整えて表示）
        List<BigInteger> keys = new ArrayList<>(factors.keySet());
        keys.sort(Comparator.naturalOrder());

        System.out.print("素因数分解: " + value + " = ");
        boolean first = true;
        for (BigInteger p : keys) {
            if (!first)
                System.out.print(" × ");
            first = false;
            int exp = factors.get(p);
            System.out.print(p);
            if (exp > 1)
                System.out.print("^" + exp);
        }
        System.out.println();
    }

    // factor: Pollard Rho による分解（戻り値は素因数→指数）
    public static Map<BigInteger, Integer> factor(BigInteger n) {
        Map<BigInteger, Integer> res = new HashMap<>();
        if (n.compareTo(BigInteger.ONE) <= 0)
            return res;
        if (isPrime(n)) {
            res.put(n, 1);
            return res;
        }

        // 試し割りで小さい素因数を取り除く（高速化）
        BigInteger two = BigInteger.valueOf(2);
        for (int p : new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
                89, 97 }) {
            BigInteger bp = BigInteger.valueOf(p);
            while (n.mod(bp).equals(BigInteger.ZERO)) {
                res.merge(bp, 1, Integer::sum);
                n = n.divide(bp);
            }
        }
        if (n.equals(BigInteger.ONE))
            return res;

        // 再帰的に Pollard Rho で残りを分解
        Deque<BigInteger> stack = new ArrayDeque<>();
        stack.push(n);
        while (!stack.isEmpty()) {
            BigInteger m = stack.pop();
            if (m.equals(BigInteger.ONE))
                continue;
            if (isPrime(m)) {
                res.merge(m, 1, Integer::sum);
                continue;
            }
            BigInteger d = pollardsRho(m);
            if (d == null) {
                // 万が一失敗したら試行を増やして再度
                d = pollardsRho(m);
                if (d == null) {
                    // 最終的に素とみなす（非常にまれ）
                    res.merge(m, 1, Integer::sum);
                    continue;
                }
            }
            stack.push(d);
            stack.push(m.divide(d));
        }

        return res;
    }

    // ミラー・ラビン素数判定
    public static boolean isPrime(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0)
            return false;
        if (n.equals(BigInteger.TWO))
            return true;
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            return false;

        // use built-in MillerRabin with high certainty
        int certainty = 12; // 2^-12 の誤判別確率
        return n.isProbablePrime(certainty);
    }

    public static BigInteger pollardsRho(BigInteger n) {
        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO))
            return BigInteger.TWO;
        BigInteger x, y, c, d;
        int attempts = 0;
        while (attempts < 10) { // 試行回数
            attempts++;
            // pick random x in [2, n-2], random c in [1, n-1]
            x = uniformRandom(BigInteger.TWO, n.subtract(BigInteger.TWO));
            y = x;
            c = uniformRandom(BigInteger.ONE, n.subtract(BigInteger.ONE));
            d = BigInteger.ONE;

            while (d.equals(BigInteger.ONE)) {
                x = f(x, c, n);
                y = f(f(y, c, n), c, n);
                BigInteger absDiff = x.subtract(y).abs();
                d = absDiff.gcd(n);
                if (d.equals(n))
                    break;
            }
            if (!d.equals(n) && !d.equals(BigInteger.ONE)) {
                return d;
            }
        }
        return null; // 失敗
    }

    private static BigInteger f(BigInteger x, BigInteger c, BigInteger mod) {
        // f(x) = x*x + c (mod mod)
        return x.multiply(x).mod(mod).add(c).mod(mod);
    }

    private static BigInteger uniformRandom(BigInteger min, BigInteger max) {
        BigInteger range = max.subtract(min).add(BigInteger.ONE); // range >= 1
        int bitLength = range.bitLength();
        BigInteger r;
        do {
            r = new BigInteger(bitLength, random);
        } while (r.compareTo(range) >= 0);
        return r.add(min);
    }
}
/*
1. 処理の全体の流れ
入力 → 17進として解釈 → BigInteger化 → 素因数分解 → 出力

2. main メソッドの説明
学生番号の入力を受ける
Scanner sc = new Scanner(System.in);
System.out.print("学生番号を入力（17進数として解釈します, 0-9,A-G）: ");
String input = sc.next().trim();
sc.close();

空白を除いた文字列として取得

17進数として解釈できるかチェック
if (!input.matches("[0-9A-G]+")) {
    System.err.println("入力に 0-9 または A-G 以外の文字が含まれています。");
    return;
}
→ 17進に使えない文字（H, Z, ! など）が入っていたらエラー終了

 BigInteger で17進数を10進に変換
value = new BigInteger(input, 17);
Java の BigInteger は好きな進数から変換して扱える

17進 → 自動で大きな10進数に変換される

素因数分解の実行
Map<BigInteger, Integer> factors = factor(value);

factor 関数は BigInteger の大きい値でも分解できるようになっている

Pollard's Rho + Miller–Rabin を使った高速アルゴリズム
 結果をきれいに表示
p × q × r^2  …

3. 素因数分解の関数 factor の説明
まず小さな素数で割れるだけ割る
int primes[] = {2,3,5,7,11,13,17,19,...,97};

ここで簡単に取り除ける因子は全部取り除いておく

これで計算がかなり速くなる

 その後に Pollard Rho で分解
d = pollardsRho(m);
Pollard Rho は高速で大きな数を因数分解できる確率アルゴリズム
失敗したら何回か繰り返して成功を狙う

Miller–Rabin で素数判定
n.isProbablePrime(certainty);
非常に高速な確率的素数判定

certainty＝12 → 誤判定率 ≈ 2^-12 と非常に低い

4. Pollard Rho の概要（簡単版）
次のような周期検出を利用した高速因数分解法

関数 f(x) = x² + c (mod n) を使って数列を作る

ある場所で巡回（ループ）が起きる

そのずれを gcd を取ることで因数が見つかる

5. uniformRandom の役割
uniformRandom(min, max)

-Pollard Rho で乱数 x
-乱数 cを作るために使用

-大きい数に対しても衝突しないようにランダム生成する
*/