package Algorithms.LeetCode;

/**
 * Given two non-negative integers num1 and num2 represented as string,
 * return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs
 * to integer directly.
 */
public class AddStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("1", "9"));
    }
    public static String addStrings(String num1, String num2) {

        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int n1 = 0;
        int n2 = 0;
        int remainder = 0;
        StringBuilder result = new StringBuilder();

        while ( len1>=0 || len2>=0 ){
            char i = len1>=0? num1.charAt(len1):'0';
            char j = len2>=0? num2.charAt(len2):'0';
            n1 = i - '0';
            n2 = j - '0';
            int sum = n1 + n2 + remainder;

            int value = sum%10;

            remainder = sum/10;
            result.append( Integer.valueOf( value ));
            len1--;
            len2--;
        }

        if( remainder > 0 ){
            result.append(Integer.toString( remainder ));
        }
        return result.reverse().toString();
    }
}
