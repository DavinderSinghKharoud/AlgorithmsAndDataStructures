package InterviewBit;

public class RegularExpressionMatching {

    //time limit exceeded ( did not checked, if it is working or not )
    public static int isMatch(final String a, final String b) {

        boolean check = helper( a, b );

        return check ? 1: 0;
    }

    private static boolean helper(String a, String b) {
        if( a.isEmpty() && b.isEmpty()){
            return true;
        }
        if( b.isEmpty() || a.isEmpty()){
            return false;
        }

        char c = b.charAt(0);

        if( c == '?' ){
            return helper( a.substring(1), b.substring(1) );
        }else if( c == '*' ){
            boolean check = helper( a.substring(1), b.substring(1) ) || helper( a.substring(1), b.substring(1) );
            return check;
        }else if( c == a.charAt(0) ){
            return helper(a.substring(1), b.substring(1) );
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println(isMatch2("aa", "aa*"));
    }

    //time limit exceeded ( did not checked, if it is working or not )
    public static int isMatch2(final String a, final String b) {
        int len1 = a.length();
        int len2 = b.length();

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        for (int col = 1; col <= len2; col++) {
            if(b.charAt(col - 1) == '*' ){
                dp[0][col] = dp[0][col - 1];
            }
        }

        for (int row = 1; row <= len1; row++) {
            for (int col = 1; col <= len2; col++) {

                if( b.charAt(col - 1) == '*' ){
                    dp[row][col] = dp[row][col - 1] || dp[row - 1][col];
                }else if( b.charAt(col - 1) == '?' || a.charAt(row - 1) == b.charAt(col - 1)){
                    dp[row][col] = dp[row - 1][col - 1];
                }
            }
        }

        return dp[len1][len2] ? 1: 0;
    }
}
