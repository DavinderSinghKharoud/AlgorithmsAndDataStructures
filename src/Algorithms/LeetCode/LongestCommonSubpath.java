package Algorithms.LeetCode;

import java.util.*;

public class LongestCommonSubpath {
    public static void main(String[] args) {
        LongestCommonSubpath o = new LongestCommonSubpath();
        System.out.println(o.longestCommonSubpath(3, new int[][]{{1, 2, 0, 1}
                , {2, 0}, {2, 0}, {1, 2}, {0, 1, 0}}));
    }

    long mod1 = 999900004679L;
    long mod2 = 100000002359L;
    int minLen = Integer.MAX_VALUE;

    public int longestCommonSubpath(int n, int[][] paths) {
        for (int[] path : paths) {
            minLen = Math.min(minLen, path.length);
        }
        int res = 0;
        int start = 0, end = (int) 1e5;
        while (start <= end) {
            int mid = (end - start) >> 1;
            mid += start;
            if (isPoss(paths, mid)) {
                start = mid + 1;
                res = mid;
            } else
                end = mid - 1;
        }
        return res;
    }

    boolean isPoss(int[][] paths, int len) {
        if (len > minLen)
            return false;
        Map<String, Integer> map = new HashMap<>();
        int base1 = 101, base2 = 103;
        long h1 = hash(paths[0], len, base1, mod1);
        long h2 = hash(paths[0], len, base2, mod2);
        map.put(h1 + "" + h2, 1);
        long pow1 = 1;
        long pow2 = 1;
        for (int i = 0; i < len - 1; i++) {
            pow1 = mul(pow1, base1, mod1);
            pow2 = mul(pow2, base2, mod2);
        }

        for (int i = 1; i < paths[0].length - len + 1; i++) {
            // save all the hashes having length as "len"
            h1 = nextHash(pow1, h1, paths[0][i - 1], paths[0][i + len - 1], base1, mod1);
            h2 = nextHash(pow2, h2, paths[0][i - 1], paths[0][i + len - 1], base2, mod2);
            map.put(h1 + "" + h2, 1);
        }

        // Go through all other paths
        Set<String> used = new HashSet<>();
        for (int i = 1; i < paths.length; i++) {
            used.clear();
            int[] path = paths[i];
            h1 = hash(path, len, base1, mod1);
            h2 = hash(path, len, base2, mod2);
            String concat = h1 + "" + h2;
            if (!used.contains(concat)) {
                map.put(concat, map.getOrDefault(concat, 0) + 1);
                used.add(concat);
            }
            for (int j = 1; j < path.length - len + 1; j++) {
                h1 = nextHash(pow1, h1, paths[i][j - 1], paths[i][j + len - 1], base1, mod1);
                h2 = nextHash(pow2, h2, paths[i][j - 1], paths[i][j + len - 1], base2, mod2);
                concat = h1 + "" + h2;
                if (!used.contains(concat)) {
                    map.put(concat, map.getOrDefault(concat, 0) + 1);
                    used.add(concat);
                }
            }
        }

        // See if any path is common
        for (Map.Entry<String, Integer> count : map.entrySet()) {
            if (count.getValue() == paths.length)
                return true;
        }
        return false;
    }

    long nextHash(long pow, long h, int left, int right, int base, long mod) {
        return add(mul(mod(h - mul(pow, left, mod), mod), base, mod), right, mod);
        //return add(mul(h - mul(pow, left, mod), base, mod), right, mod);
    }

    long hash(int[] arr, int len, int base, long mod) {
        long hash = 0;
        long mul = 1;
        for (int i = len - 1; i >= 0; i--) {
            hash = add(hash, mul(arr[i], mul, mod), mod);
            mul = mul(mul, base, mod);
        }
        return hash;
    }

    long add(long a, long b, long mod) {
        return (a + b) % mod;
    }

    long mul(long a, long b, long mod) {
        // if (a > b) a = a ^ b ^ (b = a);
        // long al = a & (1L << 31) - 1, ah = a >> 31;
        // long bl = b & (1L << 31) - 1, bh = b >> 31;
        // long low = al * bl, mid = al * bh + bl * ah;
        // long high = ah * bh + (mid >> 31);
        // return mod(mod(2 * high + low) + ((mid & (1L << 31) - 1) << 31));
        // return ((a * b) % mod) ^ ((a * sec) % mod) ^ ((b * sec) % mod);
        return (a * b) % mod;
    }

    long mod(long a, long mod) {
        while (a > mod) a -= mod;
        while (a < 0) a += mod;
        return a;
    }
}
