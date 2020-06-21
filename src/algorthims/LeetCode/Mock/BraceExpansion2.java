package algorthims.LeetCode.Mock;

import java.util.*;

/**
 * Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.
 *
 * Grammar can best be understood through simple examples:
 *
 * Single letters represent a singleton set containing that word.
 * R("a") = {"a"}
 * R("w") = {"w"}
 * When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
 * R("{a,b,c}") = {"a","b","c"}
 * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
 * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the 3 rules for our grammar:
 *
 * For every lowercase letter x, we have R(x) = {x}
 * For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
 * Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.
 *
 *
 *
 * Example 1:
 *
 * Input: "{a,b}{c,{d,e}}"
 * Output: ["ac","ad","ae","bc","bd","be"]
 * Example 2:
 *
 * Input: "{{a,z},a{b,c},{ab,z}}"
 * Output: ["a","ab","ac","z"]
 * Explanation: Each distinct word is written only once in the final answer.
 */
public class BraceExpansion2 {


    public static void main(String[] args) {

        System.out.println( braceExpansionII("{a,b}{c,{d,e}}"));
    }

    public static List<String> braceExpansionII(String expression) {
        List<String> res = new ArrayList<>(expand( expression ));
        Collections.sort(res);
        return res;
    }

    private static Set<String> expand(String expression) {
        int left = expression.indexOf("{");
        if( left == -1 ){
            return new HashSet<>(Arrays.asList(expression.split(",")));
        }

        int right = left + 1;
        int count = 1;
        while ( right < expression.length() ){
            if( expression.charAt(right) == '{' ){
                count++;
            }else if( expression.charAt(right) == '}' ){
                count--;
                if( count == 0 ){//we need to evalute the statment
                    break;
                }
            }
            right++;
        }

        String leftPart = expression.substring(0, left);
        Set<String> middlPart = expand( expression.substring(left + 1, right) );
        String rightPart = expression.substring(right + 1);

        Set<String> res = new HashSet<>();
        for(String str: middlPart ){
            String temp = new StringBuilder().append(leftPart).append(str).append(rightPart).toString();
            res.addAll(expand(temp));
        }
        return res;

    }
}
