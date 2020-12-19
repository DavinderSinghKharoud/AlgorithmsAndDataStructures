package Algorithms.LeetCode;

import java.util.ArrayList;

public class PrimeSum {

    public static ArrayList<Integer> primesum(int A) {

        ArrayList<Integer> list = new ArrayList<>();


        for( int i = 2; i< A/2 + 1; i++ ){

            if( checkPrime(i) && checkPrime( A - i ) ){
                if( list.size() > 0 ){
                    int first = list.get(0);
                    int second =list.get(1);

                    if( i < first || i == first && A - i < second ){
                        list.clear();
                        list.add(i);
                        list.add( A - i );
                    }
                    continue;
                }
                list.add(i);
                list.add( A - i );

            }
        }


        return list;

    }

    public static boolean checkPrime( int num ){

        double sqr = Math.sqrt(num);
        if( sqr % 1 == 0 ){
            return false;
        }

        for( int i = 2; i< (int)sqr + 1; i++ ){

            if( num % i == 0 ){
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {

        System.out.println( primesum(10));
    }
}
