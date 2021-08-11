package InterviewBit;

import java.util.*;
public class FirstMissingInteger {

    public static int firstMissingPositive(List<Integer> lst) {
		
		int len = lst.size();
		
		for( int index = 0; index < len; index++ ){
			if( lst.get(index) <= 0 ){
				lst.set(index, Integer.MAX_VALUE);
			}
		}
		for( int index = 0; index < len; index++ ){
			int num = Math.abs(lst.get(index));
			
			if( num <= len ){
				lst.set( num - 1, -lst.get( num - 1) );
			}
		}
		
		for( int index = 0; index < len; index++ ){
			if( lst.get(index) > 0 ){
				return index + 1;
			}
		}
		
		return len + 1;
    }
    public static void main(String[] args) {

		System.out.println( firstMissingPositive(Arrays.asList(1)));
    }
}
