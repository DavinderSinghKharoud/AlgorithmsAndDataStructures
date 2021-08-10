package Algorithms.LeetCode;

import java.util.*;

public class CheckIfStringPrefix {

    public static void main(String[] args) {
        CheckIfStringPrefix o = new CheckIfStringPrefix();
        System.out.println(o.isPrefixString("z", new String[]{"z"}));
    }

    public boolean isPrefixString(String s, String[] words) {
        for (String word : words) {
            if (!s.isEmpty()) {
                if (s.startsWith(word)) {
                    s = s.substring(word.length());
                } else
                    return false;
            } else {
                return true;
            }
        }
        return s.isEmpty();
    }
}
