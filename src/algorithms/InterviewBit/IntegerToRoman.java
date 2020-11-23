package algorithms.InterviewBit;

/**
 * Given an integer A, convert it to a roman numeral, and return a string corresponding to its roman numeral version
 *
 *  Note : This question has a lot of scope of clarification from the interviewer. Please take a moment to think of all the needed clarifications and see the expected response using “See Expected Output” For the purpose of this question, https://projecteuler.net/about=roman_numerals has very detailed explanations.
 *
 *
 * Input Format
 *
 * The only argument given is integer A.
 * Output Format
 *
 * Return a string denoting roman numeral version of A.
 * Constraints
 *
 * 1 <= A <= 3999
 * For Example
 *
 * Input 1:
 *     A = 5
 * Output 1:
 *     "V"
 *
 * Input 2:
 *     A = 14
 * Output 2:
 *     "XIV"
 */
public class IntegerToRoman {

    public static String intToRoman(int num) {

			String[] m = { "", "M", "MM", "MMM" };
			String[] c = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
			String[] x = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
			String[] i = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
			
			StringBuilder sbr = new StringBuilder();
			
			String thousands = m[ num / 1000 ];
			String hundereds = c[ (num % 1000)/ 100];
			String tens = x[ (num % 100)/ 10 ];
			String ones = i[ num % 10 ];
			
			sbr.append(thousands).append(hundereds).append(tens).append(ones);
			
			return sbr.toString();
    }


    public static void main(String[] args) {

		System.out.println( intToRoman2(3549));
    }

	public static String intToRoman2(int num) {

    	int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    	String[] romanLiterals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    	StringBuilder res = new StringBuilder();

		for (int index = 0; index < values.length; index++) {
			while ( num >= values[index]){
				num -= values[index];
				res.append(romanLiterals[index]);
			}
		}

		return res.toString();
	}
}
