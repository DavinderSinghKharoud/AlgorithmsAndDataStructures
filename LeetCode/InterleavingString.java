package LeetCode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
 * <p>
 * An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:
 * <p>
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * Example 2:
 * <p>
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * Example 3:
 * <p>
 * Input: s1 = "", s2 = "", s3 = ""
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1, s2, and s3 consist of lowercase English letters.
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(new InterleavingString().isInterleave("aabcc", "dbbca", "aadbbbaccc"));
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len1 + len2 != len3) return false;
        boolean[][] visited = new boolean[len1 + 1][len2 + 1];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (visited[curr[0]][curr[1]]) continue;
            if (curr[0] == len1 && curr[1] == len2) return true;
            if (curr[0] < len1 && s1.charAt(curr[0]) == s3.charAt(curr[0] + curr[1])) {
                queue.add(new int[]{curr[0] + 1, curr[1]});
            }
            if (curr[1] < len2 && s2.charAt(curr[1]) == s3.charAt(curr[0] + curr[1])) {
                queue.add(new int[]{curr[0], curr[1] + 1});
            }
            visited[curr[0]][curr[1]] = true;
        }
        return false;
    }
}
