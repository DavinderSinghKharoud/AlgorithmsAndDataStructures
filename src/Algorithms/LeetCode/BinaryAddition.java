package Algorithms.LeetCode;

/**
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 * Example 2:
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 *
 * Constraints:
 *
 * Each string consists only of '0' or '1' characters.
 * 1 <= a.length, b.length <= 10^4
 * Each string is either "0" or doesn't contain any leading zero.
 */
public class BinaryAddition {
    public static String addBinary(String a, String b) {

        int len1 = a.length();
        int len2 = b.length();

        if( len1 == 0 ){
            return b;
        }else if( len2 == 0 ){
            return a;
        }

        StringBuilder sbr = new StringBuilder();
        int carry = 0;
        int index1 = len1 - 1;
        int index2 = len2 - 1;
        while( index1 >= 0 && index2 >= 0 ){

            int num1 = a.charAt(index1) - '0';
            int num2 = b.charAt(index2) - '0';

            int sum = num1 + num2 + carry;

            if( sum < 2 ){
                sbr.append(sum);
                carry = 0;
            }else if( sum == 2 ){
                sbr.append(0);
                carry = 1;
            }else {
                sbr.append(1);
                carry = 1;
            }

            index1--;
            index2--;
        }

        while ( index1 != -1 ){
            int sum = a.charAt(index1) - '0' + carry;
            if( carry != 0 ){
                if( sum == 2 ){
                    sbr.append(0);
                    carry = 1;
                }else{
                    sbr.append(sum);
                    carry = 0;
                }
            }else{
                sbr.append(sum);
            }
            index1--;
        }

        while ( index2 != -1 ){
            int sum = b.charAt(index2) - '0' + carry;
            if( carry != 0 ){
                if( sum == 2 ){
                    sbr.append(0);
                    carry = 1;
                }else{
                    sbr.append(sum);
                    carry = 0;
                }
            }else{
                sbr.append(sum);
            }
            index2--;
        }
        if( carry == 1 ) sbr.append(1);

        return sbr.reverse().toString();
    }

    public static String addBinary2(String a, String b) {
        int index1 = a.length() - 1 ;
        int index2 = b.length() - 1;

        StringBuilder sbr = new StringBuilder();
        int sum = 0;
        int carry = 0;

        while ( index1 >= 0 && index2 >= 0 ){
            sum = a.charAt(index1--) - '0' + b.charAt(index2--) - '0' + carry;
            sbr.append( sum % 2);
            carry = sum/2;
        }

        while ( index1 >= 0 ){
            sum = a.charAt(index1--) - '0' + carry;
            sbr.append(sum%2);
            carry = sum/2;
        }

        while ( index2 >= 0 ){
            sum = b.charAt(index2--) - '0' + carry;
            sbr.append(sum%2);
            carry = sum/2;
        }

        if( carry != 0 ) sbr.append(carry);

        return sbr.reverse().toString();
    }

    public static void main(String[] args) {

        System.out.println( addBinary2("11", "1"));
    }
}
