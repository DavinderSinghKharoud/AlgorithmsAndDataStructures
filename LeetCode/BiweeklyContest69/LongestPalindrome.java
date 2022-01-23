package LeetCode.BiweeklyContest69;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalindrome {
    public static void main(String[] args) {
        LongestPalindrome o = new LongestPalindrome();
        System.out.println(o.longestPalindrome(
                new String[]{"qo", "fo", "fq", "qf", "fo", "ff", "qq", "qf", "of", "of", "oo", "of", "of", "qf", "qf", "of"}
        ));
    }

    public int longestPalindrome(String[] words) {
        Map<String, Integer> vis = new HashMap<>();
        int ans = 0;
        for (String word : words) {
            String rev = new StringBuilder(word).reverse().toString();
            if (vis.containsKey(rev)) {
                ans += 4;
                remove(vis, rev);
            } else
                vis.put(word, vis.getOrDefault(word, 0) + 1);

        }
        for (String word : vis.keySet()) {
            String rev = new StringBuilder(word).reverse().toString();
            if (rev.equals(word)) {
                ans += 2;
                break;
            }
        }
        return ans;
    }

    private void remove(Map<String, Integer> vis, String rev) {
        int count = vis.get(rev) - 1;
        if (count == 0) vis.remove(rev);
        else vis.put(rev, count);
    }
}
