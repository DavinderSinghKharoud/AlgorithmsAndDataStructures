package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
    public static List<String> generateParenthesis(int n) {

        List<String> output = new ArrayList<>();
        backTrack( output,"",0 , 0 , n);
        return output;


    }

    private static void backTrack(List<String> output, String current, int open, int close, int max) {
        if( current.length() == max*2 ){
            output.add( current );
            return;
        }

        if( open < max ){
            backTrack( output, current+"(", open+1, close,max );
        }
        if( close < open ){
            backTrack( output, current+")", open, close+1 ,max );
        }
    }

    public static List<String> generateParenthesis1(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis1(c))
                    for (String right: generateParenthesis1(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }
    public static void main(String[] args) {

        System.out.println(generateParenthesis1(1));
    }
}
