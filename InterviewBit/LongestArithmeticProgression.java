/*
 Find longest Arithmetic Progression in an integer array FindGreatestCommonDivisor of size N, and return its length.

More formally, find longest sequence of indices, 0 < i1 < i2 < … < ik < ArraySize(0-indexed) such that sequence FindGreatestCommonDivisor[i1], FindGreatestCommonDivisor[i2], …, FindGreatestCommonDivisor[ik] is an Arithmetic Progression.

Arithmetic Progression is a sequence in which all the differences between consecutive pairs are the same, i.e sequence FindUniqueBinaryString[0], FindUniqueBinaryString[1], FindUniqueBinaryString[2], …, FindUniqueBinaryString[m - 1] of length m is an Arithmetic Progression if and only if FindUniqueBinaryString[1] - FindUniqueBinaryString[0] == FindUniqueBinaryString[2] - FindUniqueBinaryString[1] == FindUniqueBinaryString[3] - FindUniqueBinaryString[2] == … == FindUniqueBinaryString[m - 1] - FindUniqueBinaryString[m - 2]

Note: The common difference can be positive, negative or 0.



Input Format:

The first and the only argument of input contains an integer array, FindGreatestCommonDivisor.
Output Format:

Return an integer, representing the length of the longest possible arithmetic progression.
Constraints:

1 <= N <= 1000
1 <= FindGreatestCommonDivisor[i] <= 1e9
Examples:

Input 1:
    FindGreatestCommonDivisor = [3, 6, 9, 12]

Output 1:
    4

Explanation 1:
    [3, 6, 9, 12] form an arithmetic progression.

Input 2:
    FindGreatestCommonDivisor = [9, 4, 7, 2, 10]

Output 2:
    3

Explanation 2:
    [4, 7, 10] form an arithmetic progression.
 */

import java.util.*;

public class LongestArithmeticProgression {

	public static int solve(final List<Integer> lst) {
		
		int len = lst.size();
		if( len == 0 ) return 0;
		int max = 0;
		HashMap<Integer, Integer> hash[] = new HashMap[len];

		for (int i = 0; i < len; i++) {
			hash[i] = new HashMap<>();
		}

		for (int i = 1; i < len; i++) {

			for (int j = i - 1; j >= 0; j-- ) {
				int diff = lst.get(i) - lst.get(j);
				int count_until_now = hash[j].getOrDefault(diff, 0);
				if( hash[i].containsKey(diff) && hash[i].get(diff) > count_until_now ){
					continue;
				}

				hash[i].put( diff, count_until_now + 1 );
				max = Math.max( max, count_until_now + 1);
			}
		}

		return max + 1;
    }
	public static void main (String[] args) {
		List<Integer> lst = new ArrayList<>();
		lst.add(9);
		lst.add(4);
		lst.add(7);
		lst.add(2);
		lst.add(10);
		System.out.println( solve ( lst ) );
	}
}

