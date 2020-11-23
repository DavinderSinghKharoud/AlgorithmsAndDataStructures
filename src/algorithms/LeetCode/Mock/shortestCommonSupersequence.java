package algorithms.LeetCode.Mock;

public class shortestCommonSupersequence {

    public static String shortestCommonSupersequence(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        if( len1 == 0 || len2 == 0 ) return "";
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int row = 0; row <= len1; row++) {
            for (int col = 0; col <= len2; col++) {

                if( row == 0 || col == 0 ){
                    dp[row][col] = 0;
                }else if( str1.charAt(row - 1) == str2.charAt(col - 1) ){
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                }else{
                    dp[row][col] = Math.max( dp[row - 1][col], dp[row][col - 1] );
                }
            }
        }

        StringBuilder sbr = new StringBuilder();

        while ( len1 > 0 && len2 > 0 ){
            if( str1.charAt(len1 - 1) == str2.charAt(len2 - 1) ){
                sbr.append( str1.charAt(len1 - 1 ) );
                len1--;
                len2--;
            }else if( dp[len1 - 1][len2] > dp[len1][len2 - 1] ){
                sbr.append( str1.charAt(len1 - 1) );
                len1--;
            }else{
                sbr.append( str2.charAt( len2 - 1) );
                len2--;
            }
        }
        
        while ( len1 > 0 ){
            sbr.append(str1.charAt( len1 - 1 ) );
            len1--;
        }

        while ( len2 > 0 ){
            sbr.append(str2.charAt( len2 - 1 ) );
            len2--;
        }

        return sbr.reverse().toString();
    }



    public static void main(String[] args) {

        System.out.println( shortestCommonSupersequence("abac", "cab"));
    }
}
