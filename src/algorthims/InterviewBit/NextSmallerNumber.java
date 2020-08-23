package algorthims.InterviewBit;

/**
 * Problem Description
 *
 * Given a number A in a form of string.
 *
 * You have to find the smallest number that has same set of digits as A and is greater than A.
 *
 * If A is the greatest possible number with its set of digits, then return -1.
 *
 *
 *
 * Problem Constraints
 * 1 <= A <= 10100000
 *
 * A doesn't contain leading zeroes.
 *
 *
 *
 * Input Format
 * First and only argument is an numeric string denoting the number A.
 *
 *
 *
 * Output Format
 * Return a string denoting the smallest number greater than A with same set of digits , if A is the largest possible then return -1.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = "218765"
 * Input 2:
 *
 *  A = "4321"
 *
 *
 * Example Output
 * Output 1:
 *
 *  "251678"
 * Output 2:
 *
 *  "-1"
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The smallest number greater then 218765 with same set of digits is 251678.
 * Explanation 2:
 *
 *  The given number is the largest possible number with given set of digits so we will retu
 */
public class NextSmallerNumber {

    public static String solve(String str) {
		
		int len = str.length();
		int replaceIndex = -1;
		
		for( int index = len - 1; index > 0; index-- ){
			if( Character.getNumericValue(str.charAt(index)) > Character.getNumericValue(str.charAt(index - 1) ) ){
				replaceIndex = index - 1;
				break;
			}
			
		}
		
		if( replaceIndex == -1 ) return "-1";
		
		int replaceNum = Character.getNumericValue( str.charAt(replaceIndex));
		int replacedIndex = Integer.MAX_VALUE;
		for( int index = len - 1; index > replaceIndex; index-- ){
			if( Character.getNumericValue(str.charAt(index) ) > replaceNum ){
				replacedIndex = index;
				break;
			}
		}
		
		StringBuilder sbr = new StringBuilder();
		for( int index = len - 1; index > replaceIndex; index-- ){
			if( index == replacedIndex ){
				sbr.append( replaceNum);
				continue;
			}
			sbr.append( str.charAt(index) );
		}
		
		return str.substring(0, replaceIndex) + str.charAt(replacedIndex) + sbr.toString();
    }
    public static void main(String[] args) {

		System.out.println( solve("534976"));
    }
}
