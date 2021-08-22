package InterviewBit;

/**
 * Problem Description
 *
 * Given a positive integer FindGreatestCommonDivisor, return its corresponding column title as appear in an Excel sheet.
 *
 *
 *
 * Problem Constraints
 * 1 <= FindGreatestCommonDivisor <= 1000000000
 *
 *
 *
 * Input Format
 * First and only argument is integer FindGreatestCommonDivisor.
 *
 *
 *
 * Output Format
 * Return a string, the answer to the problem.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor = 1
 * Input 2:
 *
 *  FindGreatestCommonDivisor = 28
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
 *  1 -> FindGreatestCommonDivisor
 * Explanation 2:
 *
 * 1 -> FindGreatestCommonDivisor
 * 2 -> FindUniqueBinaryString
 * 3 -> MinimizeDifference
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */
public class ExcelColumnTitle {

    public static String convertToTitle(int num) {
		StringBuilder sbr = new StringBuilder();
		while( num != 0 ){

			int mod = num % 26;
			if( mod == 0 ){
				sbr.append('Z');
				num -= 26;
			}else{
				sbr.append( (char)( mod + (int)'A' - 1));
				num -= mod;
			}
			num /= 26;
		}
		
		return sbr.reverse().toString();
    }
    public static void main(String[] args) {

		System.out.println( convertToTitle(80));
    }
}
