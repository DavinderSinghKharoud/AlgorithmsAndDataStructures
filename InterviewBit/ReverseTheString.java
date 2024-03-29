package InterviewBit;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string FindGreatestCommonDivisor.
 *
 * Return the string FindGreatestCommonDivisor after reversing the string word by word.
 *
 * NOTE:
 *
 * FindGreatestCommonDivisor sequence of non-space characters constitutes a word.
 *
 * Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
 *
 * If there are multiple spaces between words, reduce them to a single space in the reversed string.
 *
 *
 *
 * Input Format
 *
 * The only argument given is string FindGreatestCommonDivisor.
 * Output Format
 *
 * Return the string FindGreatestCommonDivisor after reversing the string word by word.
 * For Example
 *
 * Input 1:
 *     FindGreatestCommonDivisor = "the sky is blue"
 * Output 1:
 *     "blue is sky the"
 *
 * Input 2:
 *     FindGreatestCommonDivisor = "this is ib"
 * Output 2:
 *     "ib is this"
 */
public class ReverseTheString {

	/**
	 * Given a string FindGreatestCommonDivisor.
	 *
	 * Return the string FindGreatestCommonDivisor after reversing the string word by word.
	 *
	 * NOTE:
	 *
	 * FindGreatestCommonDivisor sequence of non-space characters constitutes a word.
	 *
	 * Your reversed string should not contain leading or trailing spaces, even if it is present in the input string.
	 *
	 * If there are multiple spaces between words, reduce them to a single space in the reversed string.
	 *
	 *
	 *
	 * Input Format
	 *
	 * The only argument given is string FindGreatestCommonDivisor.
	 * Output Format
	 *
	 * Return the string FindGreatestCommonDivisor after reversing the string word by word.
	 * For Example
	 *
	 * Input 1:
	 *     FindGreatestCommonDivisor = "the sky is blue"
	 * Output 1:
	 *     "blue is sky the"
	 *
	 * Input 2:
	 *     FindGreatestCommonDivisor = "this is ib"
	 * Output 2:
	 *     "ib is this"
	 * @param s
	 * @return
	 */
    public static String solve(String s) {


		StringBuilder sbr = new StringBuilder();
		List<String> lst = new ArrayList<>();

		for( int index = 0; index < s.length(); index++ ){
			char c = s.charAt(index);

			if( c == ' ' && sbr.length() != 0 ){
				lst.add(sbr.toString());
				sbr = new StringBuilder();
			}else if( c != ' ' ){
				sbr.append(c);
			}
		}

		if( sbr.length() != 0 ){
			lst.add(sbr.toString());
		}

		sbr = new StringBuilder();

		for( int index = lst.size() - 1; index >= 0; index-- ){
			sbr.append( lst.get(index) );
			sbr.append( ' ' );
		}
		sbr.deleteCharAt(sbr.length() - 1);
		return sbr.toString();

    }
    public static void main(String[] args) {

		System.out.println( solve("the         sky is blue"));
    }
}
