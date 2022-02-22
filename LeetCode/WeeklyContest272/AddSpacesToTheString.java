package LeetCode.WeeklyContest272;

import java.util.*;

public class AddSpacesToTheString {
    public static void main(String[] args) {
        AddSpacesToTheString o = new AddSpacesToTheString();
    }

    public String addSpaces(String s, int[] spaces) {
        Set<Integer> set = new HashSet<>();
        for (int num : spaces) set.add(num);
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(i)) {
                sbr.append(" ");
            }
            sbr.append(s.charAt(i));
        }
        return sbr.toString();
    }
}
