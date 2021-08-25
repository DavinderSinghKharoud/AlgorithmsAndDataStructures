package LeetCode;

import java.util.*;

public class ZigZagConversion {

    public static void main(String[] args) {
        ZigZagConversion o = new ZigZagConversion();
        System.out.println(o.convert("A", 1));
    }

    public String convert(String s, int numRows) {
        List<Character>[] rows = new List[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new ArrayList<>();
        }
        int start = 0;
        while (start < s.length()) {
            for (int i = start, row = 0; i < Math.min(start + numRows, s.length()); i++, row++) {
                rows[row].add(s.charAt(i));
            }

            start += numRows;
            for (int i = start, row = numRows - 2; i < Math.min(start + numRows - 2, s.length()); i++, row--) {
                rows[row].add(s.charAt(i));
            }
            start += Math.max(numRows - 2, 0);
        }

        StringBuilder sbr = new StringBuilder();
        for (List<Character> row : rows) {
            row.stream().forEach(o -> sbr.append(o));
        }
        return sbr.toString();
    }
}
