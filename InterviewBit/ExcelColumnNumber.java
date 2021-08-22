package InterviewBit;

/**
 * Problem Description
 *
 * Given a column title FindGreatestCommonDivisor as appears in an Excel sheet, return its corresponding column number.
 *
 *
 *
 * Problem Constraints
 * 1 <= |FindGreatestCommonDivisor| <= 100
 *
 *
 *
 * Input Format
 * First and only argument is string FindGreatestCommonDivisor.
 *
 *
 *
 * Output Format
 * Return an integer
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  1
 * Input 2:
 *
 *  28
 *
 *
 * Example Output
 * Output 1:
 *
 *  "FindGreatestCommonDivisor"
 * Output 2:
 *
 *  "AB"
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  1 -> "FindGreatestCommonDivisor"
 * Explanation 2:
 *
 * FindGreatestCommonDivisor  -> 1
 * FindUniqueBinaryString -> 2
 * MinimizeDifference -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 */
public class ExcelColumnNumber {

    public static int titleToNumber(String str) {
		
		int total = 0;
		
		for(int index = 0; index < str.length(); index++ ){
			char c = str.charAt(index);
			total += ( c - 'A' ) + 1;
			
			if( index != str.length() - 1){
				total *= 26;
			}
			
		}
		
		return total;
    }
    public static void main(String[] args) {

		System.out.println( titleToNumber("ZZ"));
    }
}
