/*
 * LengthOfLongestFibonacciSubsequence.java
 * 
 * Copyright 2020 Davinder singh kharoud <davindersinghkharoud@Davinders-MacBook-Pro.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR FindGreatestCommonDivisor PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
import java.util.*;
public class LengthOfLongestFibonacciSubsequence {
	public static int lenLongestFibSubseq(int[] arr) {
        int len = arr.length;
        if( len < 3 ) return 0;
        Map<Integer, Integer> indexes = new HashMap<>();
		for (int index = 0; index < len; index++) {
			indexes.put(arr[index], index);
		}

		int max = 0;

		Map<Integer, Integer> map = new HashMap<>();

		for(int index = 0; index < len; index++ ){
			
			for(int start = 0; start < index; start++){
				int i = indexes.getOrDefault(arr[index] - arr[start], -1);

				if( i >= 0 && i < start){
					int cand = map.getOrDefault(i * len + start, 2) + 1;
					map.put( start * len + index, cand);
					max = Math.max(max, cand);
				}
			}
			
		}


		return max;
    }
	public static void main (String[] args) {

		System.out.println( lenLongestFibSubseq2(new int[]{
				1,2,3,4,5,6,7,8
		}));
	}

	public static int lenLongestFibSubseq2(int[] A) {

		int len = A.length;
		if( len < 3 ) return 0;

		Set<Integer> set = new HashSet<>();
		for(int num: A ){
			set.add(num);
		}

		int temp = 2;
		for(int first = 0; first < len - 1; first++ ){
			for(int second = first + 1; second < len; second++ ){
				if( set.contains( A[first] + A[second] ) ){
					temp = findFibonacci( A[first], A[second], temp, set);
				}
			}
		}

		return temp > 2? temp: 0;
	}

	private static int findFibonacci(int first, int second, int len, Set<Integer> set) {

		int temp = 2;
		int next = first + second;

		while ( set.contains(next) ){
			first = second;
			second = next;
			next = first + second;
			temp++;
		}

		return Math.max(len, temp);
	}
}

