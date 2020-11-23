package algorithms.InterviewBit;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example :
 * <p>
 * Given numerator = 1, denominator = 2, return "0.5"
 * Given numerator = 2, denominator = 1, return "2"
 * Given numerator = 2, denominator = 3, return "0.(6)"
 */

import java.util.*;

public class Fraction {

    public static String fractionToDecimal(int numerator, int denominator) {
			if (numerator == 0) return "0";

			StringBuilder res = new StringBuilder();
			if (numerator < 0 && denominator > 0 || (numerator > 0 && denominator < 0)) {
				res.append("-");
			}
			long num = Math.abs( (long) numerator);
			long den = Math.abs( (long) denominator);

			long remainder = num % den;
			res.append(num / den);

			if (remainder == 0) return res.toString();

			res.append(".");
			Map<Long, Integer> map = new HashMap<>();

			while (remainder != 0) {
				if (map.containsKey(remainder)) {
					res.insert(map.get(remainder), "(");
					res.append(")");
					break;
				}

				map.put(remainder, res.length());
				remainder *= 10;
				res.append(remainder / den);
				remainder %= den;
			}

			return res.toString();
    }

    public static void main(String[] args) {

        System.out.println(fractionToDecimal(2, 3));
    }
}
