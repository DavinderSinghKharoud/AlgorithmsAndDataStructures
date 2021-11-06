package Preparation;

import java.util.ArrayList;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2*n.
 *
 * For example, given n = 3, a solution set is:
 *
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * Make sure the returned list of strings are sorted.
 */
public class GenerateAllParenthesesII {
    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();

        generate(res, new StringBuilder(), 0, n);
        return res;
    }

    void generate(ArrayList<String> res, StringBuilder sbr, int open, int n) {
        if (sbr.length() == 2 * n) {
            if (open == 0) {
                res.add(sbr.toString());
            }
        } else {
            if (open < n) {
                sbr.append("(");
                generate(res, sbr, open + 1, n);
                sbr.deleteCharAt(sbr.length() - 1);
            }
            if (open > 0) {
                sbr.append(")");
                generate(res, sbr, open - 1, n);
                sbr.deleteCharAt(sbr.length() - 1);
            }
        }
    }
}
