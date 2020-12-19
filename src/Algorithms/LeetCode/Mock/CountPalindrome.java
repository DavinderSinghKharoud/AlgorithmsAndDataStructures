package Algorithms.LeetCode.Mock;

public class CountPalindrome {

    public static void main(String[] args) {

        System.out.println( countSubstrings("aaa") );
    }

    public static int countSubstrings(String s) {
        int len = s.length();
        if( len == 0 ) return 0;
        int count = 0;
        for (int start = 0; start < len; start++) {
            for (int end = start + 1; end <= len; end++) {

                count += (check(s.substring(start, end))) ? 1: 0 ;
            }
        }

        return count;
    }

    private static boolean check(String substring) {
        int start = 0;
        int end = substring.length() - 1;

        while ( start <= end ){
            if( substring.charAt(start) == substring.charAt(end) ){
                start++; end--;
            }else{
                return false;
            }
        }
        return true;
    }
}
