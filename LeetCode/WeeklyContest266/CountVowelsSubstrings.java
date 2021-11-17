package LeetCode.WeeklyContest266;

import java.util.*;

public class CountVowelsSubstrings {
    public static void main(String[] args) {
        CountVowelsSubstrings o = new CountVowelsSubstrings();
        System.out.println(o.countVowelSubstrings("aeiouu"));
    }

    public int countVowelSubstrings(String word) {
        int len = word.length();
        int res = 0;
        int[] curr = new int[129];
        for (int i = 0; i < len; i++) {
            Arrays.fill(curr, 0);
            curr[word.charAt(i)]++;
            for (int j = i + 1; j < len; j++) {
                curr[word.charAt(j)]++;
                if (isValid(curr)) {
                    res++;
                }
            }
        }
        return res;
    }

    boolean isValid(int[] arr) {
        char[] cc = new char[]{'a', 'e', 'i', 'o', 'u'};
        for (char c : cc) {
            if (arr[c] == 0)
                return false;
        }

       outer: for (int i = 0; i < arr.length; i++) {
            for (int c : cc) {
                if(i == c){
                    continue outer;
                }
            }
            if (arr[i] > 0)
                return false;
        }
        return true;
    }
}
