package Algorithms.InterviewBit;

import java.util.Arrays;
import java.util.List;

public class SingleNumber {

    public static int singleNumber(final List<Integer> lst) {
		
		int len = lst.size();
		if( len == 0 ) return 0;
		
		int res = lst.get(0);
		
		for( int index = 1; index < len; index++ ){
			res = ( res ^ lst.get(index) );
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println( singleNumber(Arrays.asList(1, 2, 2, 3, 1)));
    }
}
