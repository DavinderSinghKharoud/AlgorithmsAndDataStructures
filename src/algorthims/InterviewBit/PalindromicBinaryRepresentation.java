package algorthims.InterviewBit;

/**
 * Given an integer A find the Ath number whose binary representation is a palindrome.
 *
 * NOTE:
 *
 * Consider the 1st number whose binary representation is palindrome as 1, instead of 0
 * Do not consider the leading zeros, while considering the binary representation.
 *
 *
 * Problem Constraints
 * 1 <= A <= 2104
 *
 *
 *
 * Input Format
 * First and only argument is an integer A.
 *
 *
 *
 * Output Format
 * Return an integer denoting the Ath number whose binary representation is a palindrome.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = 1
 * Input 1:
 *
 *  A = 9
 *
 *
 * Example Output
 * Output 1:
 *
 *  1
 * Output 2:
 *
 *  27
 *
 *
 * Example Explanation*
 * Explanation 1:
 *
 *  1st Number whose binary representation is palindrome is 1
 * Explanation 2
 *
 *  9th Number whose binary representation is palindrome is 27 (11011)
 */
public class PalindromicBinaryRepresentation {

    //Time complexity of this solution is O(x) where x is resultant number. Note that value of x is generally much larger than n.
    public static int solve(int n) {

        int count = 0;


        int num = 0;
        for (num = 1; true; num++) {

            if( isPalindrome(num) ){
                count++;
            }
            if( count == n ) return num;
        }

    }

    private static boolean isPalindrome(int num) {
        int l = leftMostBit(num);
        int r = 1;

        while ( l > r ){
            if( isKthBit(num, l) != isKthBit(num, r) ){
                return false;
            }
            l--;
            r++;
        }
        return true;
    }

    private static int isKthBit(int num, int position) {

        return ( (num & ( 1 << (position - 1))) > 0 ) ? 1: 0;
    }

    private static int leftMostBit(int num) {

        int count = 0;
        while ( num > 0 ){
            count++;
            num >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println( solve(9));
    }
}
