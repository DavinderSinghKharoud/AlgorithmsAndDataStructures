package InterviewBit;

public class ConvertToPalindrome {

    /**
     * Given a string A consisting only of lowercase characters, we need to check whether it is possible to make this string a palindrome after removing exactly one character from this.
     *
     * If it is possible then return 1 else return 0.
     *
     *
     *
     * Problem Constraints
     * 3 <= |A| <= 105
     *
     * A[i] is always a lowercase character.
     *
     *
     *
     * Input Format
     * First and only argument is an string A.
     *
     *
     *
     * Output Format
     * Return 1 if it is possible to convert A to palindrome by removing exactly one character else return 0.
     *
     *
     *
     * Example Input
     * Input 1:
     *
     *  A = "abcba"
     * Input 2:
     *
     *  A = "abecbea"
     *
     *
     * Example Output
     * Output 1:
     *
     *  1
     * Output 2:
     *
     *  0
     *
     *
     * Example Explanation
     * Explanation 1:
     *
     *  We can remove character ‘c’ to make string palindrome
     * Explanation 2:
     *
     *  It is not possible to make this string palindrome just by removing one character
     * @param s
     * @return
     */
    public static int solve(String s) {

        return (traverse(s, 0, s.length() - 1, false)) ? 1: 0;
    }

    private static boolean traverse(String s, int start, int end, boolean isPossible) {
        if( start >= end ) return true;
        
        if( s.charAt(start) == s.charAt(end) ){
			return traverse( s, start + 1, end - 1, isPossible);
		}
		
		if( isPossible ) return false;
		isPossible = true;
		
		return ( traverse( s, start + 1, end, isPossible) || traverse( s, start, end - 1, isPossible) );
    
	    }

    public static void main(String[] args) {

        System.out.println( solve("abecbea"));
    }
}
