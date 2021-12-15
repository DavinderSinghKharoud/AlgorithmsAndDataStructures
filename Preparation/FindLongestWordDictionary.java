package Preparation;

import java.util.*;

public class FindLongestWordDictionary {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";

        for (String word : dictionary) {
            if (isEmbedded(word, s)) {
                System.out.println(word);
                if (word.length() > ans.length() && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }

        return ans;
    }

    boolean isEmbedded(String word, String s) {
        int start = 0;
        for (int i = 0; i < s.length() && start < word.length(); i++) {
            char c = s.charAt(i);
            if (c == word.charAt(start)) {
                start++;
            }
        }
        return start == word.length();
    }
}
