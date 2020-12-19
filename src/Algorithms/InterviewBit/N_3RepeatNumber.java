package Algorithms.InterviewBit;

/**
 * You’re given a read only array of n integers. Find out if any integer occurs more than n/3 times in the array in linear time and constant additional space.
 *
 * If so, return the integer. If not, return -1.
 *
 * If there are multiple solutions, return any one.
 *
 * Example :
 *
 * Input : [1 2 3 1 1]
 * Output : 1
 * 1 occurs 3 times which is more than 5/3 times.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class N_3RepeatNumber {

    public static int repeatedNumber(final List<Integer> a) {
        
        int count = a.size()/3;
        
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int num: a ){
			
			map.put( num, map.getOrDefault( num, 0 ) + 1 );
			if( map.get(num) > count ){
				return num;
			}
		}
		
		return -1;
    }

    //The idea is based on Moore’s Voting algorithm.
    public static int repeatedNumber1(final List<Integer> a) {

        int minCount = a.size()/3;
        int count1 = 0, count2 = 0;

        int first = Integer.MIN_VALUE;
        int second = Integer.MAX_VALUE;

        for( int num: a ){
            if( first == num ){
                count1++;
            }else if( second == num ){
                count2++;
            }else if( count1 == 0 ){
                count1 = 1;
                first = num;
            }else if( count2 == 0 ){
                count2 = 1;
                second = num;
            }else {
                count1--;
                count2--;
            }

        }

        count1 = 0;
        count2 = 0;
        //Verify
        for(int num: a ){
            if( num == first ){
                count1++;
            }else if( num == second ){
                count2++;
            }

            if( count1 > minCount ){
                return first;
            }
            if( count2 > minCount ){
                return second;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println( repeatedNumber1(Arrays.asList(1, 2, 3, 1, 4, 1, 5, 6)));
    }
}
