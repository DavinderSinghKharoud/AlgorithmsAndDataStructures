package algorithms.LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaximumNumberOfOccurrencesOfaSubstring {

    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {

        int len = s.length();
        if (len == 0) return 0;

        int res = 0;
        int start = 0;
        int unique = 0;
        int[] arr = new int[26];

        Map<String, Integer> map = new HashMap<>();
        for (int end = 0; end < len; end++) {

            if (arr[s.charAt(end) - 'a']++ == 0) unique++;

            while ((unique > maxLetters && end - start + 1 > minSize) || (end - start + 1 > maxSize) || end - start + 1 > minSize) {
                if (--arr[s.charAt(start++) - 'a'] == 0) {
                    unique--;
                }
            }

            if (unique <= maxLetters && end - start + 1 >= minSize) {
                String sub = s.substring(start, end + 1);
                map.put(sub, map.getOrDefault(sub, 0) + 1);
                res = Math.max(res, map.get(sub));
            }

        }

        return res;
    }

    public static void main(String[] args) {
//
//        System.out.println(maxFreq("aababcaab", 2, 3, 4));
//        System.out.println(maxFreq("aaaa", 1, 3, 3));
        System.out.println(maxFreq("aabcabcab", 2, 2, 3));
//        System.out.print(maxFreq("abcde", 2, 3, 3));
//        System.out.println(maxFreq("dbaaacbcbb", 3, 2, 6));


    }

}
