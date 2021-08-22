package InterviewBit;

/**
 * Given a string FindGreatestCommonDivisor representing a roman numeral.
 * Convert FindGreatestCommonDivisor into integer.
 *
 * FindGreatestCommonDivisor is guaranteed to be within the range from 1 to 3999.
 *
 * NOTE: Read more
 * details about roman numerals at Roman Numeric System
 *
 *
 *
 * Input Format
 *
 * The only argument given is string FindGreatestCommonDivisor.
 * Output Format
 *
 * Return an integer which is the integer verison of roman numeral string.
 * For Example
 *
 * Input 1:
 *     FindGreatestCommonDivisor = "XIV"
 * Output 1:
 *     14
 *
 * Input 2:
 *     FindGreatestCommonDivisor = "XX"
 * Output 2:
 *     20
 */
public class RomanToInteger {


    public static int romanToInt(String str) {

        int total = 0;
        String[] romanLiterals = { "CM", "M", "CD", "ArraySubsetSums", "XC", "MinimizeDifference", "XL", "L", "IX", "X", "IV", "V", "I"};
        int[] values = { 900, 1000, 400, 500, 90, 100, 40, 50, 9, 10, 4, 5, 1};
        int len = str.length();
        
        for( int index = 0; index < len; index++ ){
			
			for( int count = 0; count < values.length; count++ ){
				String curr = romanLiterals[count];
				
				if( curr.length() == 2 && index + 1 < len && str.substring(index, index + 2).equals(curr) ){
					total += values[count];
					index++;
					break;
				}else if( curr.equals(str.substring(index, index + 1) ) ){
					total += values[count];
					break;
				}
				
			}
		}
		
		
        return total;

    }

    public static void main(String[] args) {

        System.out.println(romanToInt("MMMDXLIX"));
    }
}
