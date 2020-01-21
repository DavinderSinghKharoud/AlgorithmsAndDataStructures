package ProjectEuler;

import java.util.ArrayList;
import java.util.List;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5,
 * we get 3, 5, 6 and 9. The sum of these multiples is 23.
 *
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class MultiplesOfNumber {

    static int getSum(int limit, int[] arr){
        int num1 = arr[0];
        int num2 = arr[1];

        List<Integer> list = new ArrayList<>();
        int sum = 0;
        int multiple = 1;
        int value  = 0;

        while( value < limit ){

            value = num1*multiple;

            if( value< limit){

                sum += value;
                list.add( value );
            }
            multiple++;
        }

        value=0;
        multiple = 0;

        while( value < limit){

            value = num2*multiple;

            if( value%num1 != 0 && value< limit){
                list.add( value );
                sum += value;
            }
            multiple++;
        }

        return sum;

    }
    public static void main(String[] args) {

        System.out.println( getSum( 1000,new int[] {3,5}) );
    }
}
