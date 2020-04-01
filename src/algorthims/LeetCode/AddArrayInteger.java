package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * For a non-negative integer X, the array-form of X is an array of
 * its digits in left to right order.  For example, if X = 1231, then the array form is [1,2,3,1].
 *
 * Given the array-form A of a non-negative integer X,
 * return the array-form of the integer X+K.
 */
public class AddArrayInteger {

    public static void main(String[] args) {
        System.out.println( addToArrayForm(new int[]{
                0
        },23) );
    }


    public static List<Integer> addToArrayForm(int[] A, int K) {

        StringBuilder result = new StringBuilder();

        String k = String.valueOf( K );
        int len1 = A.length - 1;
        int len2 = k.length() - 1;
        int value1 = 0;
        int value2 = 0;
        int remainder = 0;

        while( len1 >= 0 || len2 >= 0 ){
            value1 = len1>=0? A[len1]:0;
            char c = len2>=0? k.charAt(len2):'0';
            value2 = c - '0';
            int sum = value1 + value2 + remainder;

            int tempvalue = sum % 10;
            remainder = sum/10;

            result.append( Integer.valueOf( tempvalue ));
            len1 --; len2--;
        }

        if( remainder > 0 ){
            result.append( remainder );
        }

        String finalNum = result.reverse().toString();

        List<Integer> lst = new ArrayList<>();


        for( int i = 0; i<finalNum.length(); i++){

            int singleNum = finalNum.charAt(i) - '0';
            lst.add( singleNum );
        }

        return lst;
    }

//        public static List<Integer> addToArrayFormLeet(int[] A, int K) {
//            int N = A.length;
//            int cur = K;
//            List<Integer> ans = new ArrayList();
//
//            int i = N;
//            while (--i >= 0 || cur > 0) {
//                if (i >= 0)
//                    cur += A[i];
//                ans.add(cur % 10);
//                cur /= 10;
//            }
//
//            Collections.reverse(ans);
//            return ans;
//
//    }
}
