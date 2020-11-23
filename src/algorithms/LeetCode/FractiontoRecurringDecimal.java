package algorithms.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class FractiontoRecurringDecimal {
	
	public static String fractionToDecimal(int numerator, int denominator) {

		if( numerator == 0 ) return "0";

		StringBuilder sb = new StringBuilder();
		if( numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0 ){
			sb.append("-");
		}

		long numer = Math.abs( (long) numerator);
		long denom = Math.abs( (long) denominator);
		long remainder = numer % denom;
		sb.append( numer/denom );

		if( remainder == 0 ){
			return  sb.toString();
		}

		sb.append('.');
		Map<Long, Integer> map = new HashMap<>();

		while ( remainder != 0 ){

			if( map.containsKey( remainder ) ){
				sb.insert( map.get( remainder ), "(");
				sb.append(")");
				break;
			}

			map.put( remainder, sb.length() );
			remainder *= 10;
			sb.append( remainder/denom );
			remainder %= denom;
		}

		return sb.toString();
	}
	public static void main (String[] args) {

		System.out.println( fractionToDecimal(20, 3));
	}
}

