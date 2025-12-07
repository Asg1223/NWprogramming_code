package com.example.second.Ex;
import java.util.*;
import java.lang.Math;

/**
 * SemiprimeFinder
 *  - 10億 (1_000_000_000) から増加させ，
 *    半素数 (p*q, p,q は素数, p<=q で可) を数えて，
 *    10_000番目, 100_000番目, 1_000_000番目 を出力する。
 *
 * 注意:
 *  - 17世代 BigInteger は使わない（longで十分。対象値は 32-bit 範囲内）
 *  - sqrt(1e9 + 数百万) < 40000 程度なので，小さな素数テーブルで十分
 */
public class d {

    public static void main(String[] args) {
        final int START = 1_000_000_000;
        final int[] targets = {10_000, 100_000, 1_000_000};

        // 1) 小さめの素数をエラトステネスで用意（上限は 100000 で余裕）
        int sieveLimit = 100000;
        boolean[] isPrimeSieve = new boolean[sieveLimit + 1];
        Arrays.fill(isPrimeSieve, true);
        isPrimeSieve[0] = isPrimeSieve[1] = false;
        for (int i = 2; i * i <= sieveLimit; i++) {
            if (isPrimeSieve[i]) {
                for (int j = i * i; j <= sieveLimit; j += i) isPrimeSieve[j] = false;
            }
        }
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= sieveLimit; i++) if (isPrimeSieve[i]) primes.add(i);

        // 2) 検査用の小素数集合（試し割りで使う）
        int[] primeArr = primes.stream().mapToInt(Integer::intValue).toArray();

        // 3) 探索ループ
        int found = 0;
        int nextTargetIndex = 0;
        HashMap<Integer, Integer> answers = new HashMap<>();
        int n = START;
        int maxTarget = targets[targets.length - 1];

        System.out.println("開始: " + START + " から半素数を探索します...");

        while (found < maxTarget) {
            if (isSemiprime(n, primeArr)) {
                found++;
                // check if this count matches any target
                for (int t : targets) {
                    if (found == t) {
                        answers.put(t, n);
                        System.out.println("found " + t + "th semiprime: " + n);
                    }
                }
            }
            n++;
            // 進捗表示（任意）
            if ((n - START) % 1_000_000 == 0) {
                System.out.println("checked up to " + n + ", found = " + found);
            }
        }

        // 最終出力
        System.out.println("\n結果：");
        for (int t : targets) {
            System.out.println(t + " 番目の半素数 = " + answers.get(t));
        }
    }

    // n が半素数かどうかを判定する (long 範囲の n を想定)
    static boolean isSemiprime(long n, int[] primes) {
        if (n < 4) return false;
        long limit = (long) Math.sqrt(n);
        for (int p : primes) {
            if ((long)p * (long)p > n) break;
            if (n % p == 0) {
                long q = n / p;
                // q が素数か確認（q は大きくても long の範囲）
                return isPrimeMR(q, primes);
            }
        }
        // もし上の試し割りで割れなければ、n は素数か1でなく、また半素数でもない
        return false;
    }

    // primes[] は小さめの素数テーブル。大きな q の判定には Miller-Rabin を使う。
    static boolean isPrimeMR(long n, int[] smallPrimes) {
        if (n < 2) return false;
        for (int p : smallPrimes) {
            if ((long)p * (long)p > n) break;
            if (n % p == 0) return n == p;
        }
        // Miller-Rabin deterministic for 32-bit / 64-bit safe bases
        return millerRabin(n);
    }

    // Miller-Rabin deterministic for n < 2^32 (and safe for our usage n ~ 1e9.. few e9)
    static boolean millerRabin(long n) {
        if (n < 2) return false;
        long d = n - 1;
        int s = 0;
        while ((d & 1) == 0) {
            d >>= 1;
            s++;
        }
        long[] bases = {2,3,5,7,11};
        for (long a : bases) {
            if (a % n == 0) return true;
            if (!mrCompositeTest(n, a, d, s)) return false;
        }
        return true;
    }

    static boolean mrCompositeTest(long n, long a, long d, int s) {
        long x = modPow(a, d, n);
        if (x == 1 || x == n - 1) return true;
        for (int r = 1; r < s; r++) {
            x = mulMod(x, x, n);
            if (x == n - 1) return true;
        }
        return false;
    }

    // modular multiplication safe for long using long (n ~ few e9 so no overflow)
    static long mulMod(long a, long b, long mod) {
        return (a * b) % mod;
    }

    static long modPow(long a, long e, long mod) {
        long res = 1 % mod;
        long base = a % mod;
        while (e > 0) {
            if ((e & 1) == 1) res = mulMod(res, base, mod);
            base = mulMod(base, base, mod);
            e >>= 1;
        }
        return res;
    }
}

/*
1. コードの構造（大枠）
1. 小さな素数（10万以下）をエラトステネスで作る
2. 半素数かどうかの判定を作る
3. 大きな数（1,000,000,000〜）を順に調べる
4. 見つかった半素数の個数を数える
5. 指定の順位で記録して結果を出す

2. 各部分の詳細解説
① 小さな素数をエラトステネスで取得
int sieveLimit = 100000;
boolean[] isPrimeSieve = new boolean[sieveLimit + 1];
...


半素数判定には 最初の素因数 p を見つける必要がある。

p は最大でも √(探索範囲) で十分
→ √1,010,000,000 ≒ 31623
→ 余裕を見て 100,000 までの素数を全部用意する

これで、後で、

if (n % p == 0)


という形で高速に割り算できる。

② 半素数判定 isSemiprime(n)
static boolean isSemiprime(long n, int[] primes) {
    for (int p : primes) {
        if (p * p > n) break;
        if (n % p == 0) {
            long q = n / p;
            return isPrimeMR(q, primes);
        }
    }
    return false;
}


半素数とは
n = p × q（p, q は素数）

なので、

小さな素数 p を順に試す

p が最初に n を割り切ったら q = n/p を計算

q が素数なら半素数

どの p でも割り切れなければ半素数ではない

ポイント

素数 p は 1つ見つかれば十分（分解は一意）

q の素数判定は Miller-Rabin を使って高速化

③ 素数判定 isPrimeMR
static boolean isPrimeMR(long n, int[] smallPrimes) {
    if (n < 2) return false;

    // 小さな素数で割れるかチェック
    for (int p : smallPrimes) {
        if ((long)p * p > n) break;
        if (n % p == 0) return n == p;
    }

    // それでも分からなければ Miller-Rabin
    return millerRabin(n);
}

判定方法は2段階
Step 1: 小素数で割り算

100,000 以下の素数で全部割ってみて、割り切れたら素数でない。（あるいは素数と確定）

Step 2: Miller-Rabin（高速素数判定）
n が 10億〜数千万くらいなので、これで 完全に正確に素数か判定できる。

④ 半素数をカウントして目標位置で記録
while (found < maxTarget) {
    if (isSemiprime(n, primeArr)) {
        found++;
        if (found == t) answers.put(t, n);
    }
    n++;
}


n = 10億 からスタート

1つ見つかるたび found++

found が 10,000/100,000/1,000,000 のときに記録

最大100万個見つかるまで続ける

100万個の半素数を見つけるには
概ね 600万〜800万個程度の整数チェックで済むため
実行時間は 数秒〜数十秒 程度。
*/