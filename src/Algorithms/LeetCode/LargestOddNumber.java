package Algorithms.LeetCode;

import java.util.*;

public class LargestOddNumber {

    public static void main(String[] args) {
        LargestOddNumber o = new LargestOddNumber();
        System.out.println(o.largestOddNumber("35427234232"));
    }

    public String largestOddNumber(String num) {
        int res_end = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if ((c - '0') % 2 != 0) {
                res_end = i + 1;
            }
        }
        if ((num.charAt(num.length() - 1) - '0') % 2 != 0) {
            return num;
        }
        return num.substring(0, res_end);
    }
}
