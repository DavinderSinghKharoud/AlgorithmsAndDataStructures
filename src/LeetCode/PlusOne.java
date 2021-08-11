package LeetCode;

import java.math.BigInteger;
import java.util.ArrayList;

/*
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
 */
public class PlusOne {

    public static int[] plusOne(int[] digits) {
//
//        int[] result = new int[digits.length];
//        int carry = 0;
//        if (digits[digits.length - 1] == 9) {
//
//            for (int i = digits.length - 1; i >= 0; i--) {
//                if (digits[i] == 9) {
//                    result[i] = 0;
//                    carry = 1;
//                } else {
//                    if( carry == 1 ){
//                        result[i] = digits[i] + carry;
//                        carry = 0;
//                    }else{
//                        result[i] = digits[i];
//                    }
//                }
//
//            }
//            if( carry == 1 ){
//                result[0] = 1;
//            }
//        } else {
//
//            for (int i = 0; i < digits.length - 1; i++) {
//                result[i] = digits[i];
//            }
//            result[digits.length - 1] = digits[digits.length - 1] + 1;
//        }
//
//        return result;

        StringBuilder str = new StringBuilder();
        for (int num : digits) {
            str.append(num);
        }
        BigInteger realNum = new BigInteger(str.toString());
        realNum = realNum.add(new BigInteger("1"));
        String string = String.valueOf(realNum);

        int result[] = new int[string.length()];
        for (int i = 0; i < string.length(); i++) {

            result[i] = Character.getNumericValue(string.charAt(i));
        }
        return result;
    }

    public static int[] plusOne2(int[] digits) {
        int lastDigit = digits[digits.length - 1];
        boolean carry = false;

        if (lastDigit < 9) {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
        } else {

            for (int i = digits.length - 1; i >= 0; i--) {

                if (digits[i] >= 9) {

                    if (i == 0) {
                        digits = new int[digits.length + 1];
                        digits[i] = 1;
                        return digits;
                    }
                    digits[i] = 0;
                    carry = true;
                    continue;
                }

                if (carry) {
                    digits[i] = digits[i] + 1;
                    carry = false;
                    break;
                }

            }
        }

        return digits;


    }

    public static void main(String[] args) {

        int[] result = plusOne2(new int[]{
                0, 3, 7, 6, 4, 0, 5, 5, 5
        });

        for (int num : result) {
            System.out.println(num);
        }

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(3);
        arr.add(7);
        arr.add(6);
        arr.add(4);
        System.out.println( plusOne3(arr));
    }

    public static ArrayList<Integer> plusOne3(ArrayList<Integer> A) {

        int len = A.size();
        int lastDigit = A.get( len - 1 );
        boolean carry = false;



        if( lastDigit < 9 ){
            A.set( len - 1, lastDigit + 1 );
            removeZeroes(A);
            return A;
        }else{

            for( int i = len - 1; i>= 0; i-- ){

                if( A.get(i) >= 9 ){

                    if( i == 0 ){
                        A.set(0, 0);
                        A.add(0, 1);
                        removeZeroes(A);
                        return A;
                    }

                    A.set(i, 0);
                    carry = true;

                }else{

                    if( carry ){
                        A.set( i, A.get(i) + 1 );
                        removeZeroes(A);
                        break;
                    }
                }


            }
        }

        return A;
    }

    public static void removeZeroes( ArrayList<Integer> A ){

        while (A.size() > 0 && A.get(0) == 0) {
            A.remove(0);
        }
    }
}
