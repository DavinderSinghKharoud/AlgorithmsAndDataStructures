package InterviewBit;

import java.util.*;
public class LargestNumber {

    public static String largestNumber(final List<Integer> lst) {

		lst.sort((o1, o2) -> {
			String first = String.valueOf(o1);
			String second = String.valueOf(o2);

			return (second + first).compareTo(first + second);

		});
		
		StringBuilder sbr = new StringBuilder();
		
		for( int num: lst ){
			sbr.append(num);
		}

		if( sbr.charAt(0) == '0') return "0";
		return sbr.toString();
        
    }
    public static void main(String[] args) {

		System.out.println( largestNumber(Arrays.asList(9, 99, 999, 9999, 9998)));
    }
}
