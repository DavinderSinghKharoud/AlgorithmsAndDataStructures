package LeetCode;

import java.util.*;

public class DistinctSubsequences {

    public static void main(String[] args) {
        DistinctSubsequences o = new DistinctSubsequences();
        System.out.println(o.numDistinct("rabbbit", "rabbit"));
    }

    int[][] dp = new int[1001][1001];

    public int numDistinct(String s, String t) {
        if (t.length() > s.length())
            return 0;
      for (int[] d : dp) {
         Arrays.fill(d, -1);
      }
        return find(s, t, 0, 0);
    }

    int find(String s, String t, int is, int it) {
        if (it >= t.length())
            return 1;
        if (is == s.length())
            return 0;

        if (dp[is][it] != -1)
            return dp[is][it];

        int poss = 0;
        if (s.charAt(is) == t.charAt(it)) {
            poss += find(s, t, is + 1, it + 1);
        }
        poss += find(s, t, is + 1, it);


        return dp[is][it] = poss;
    }
}
