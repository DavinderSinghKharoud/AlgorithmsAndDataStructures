/*
 * ArrayThreePointers.java
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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
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

public class ArrayThreePointers {
	
	public static int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
		
		int diff = Integer.MAX_VALUE;
		
		//Traverse Array
		int index1 = 0, index2 = 0, index3 = 0;
		
		while( index1 < A.size() && index2 < B.size() && index3 < C.size() ){
			
			int max = Math.max( A.get(index1), Math.max( B.get(index2) , C.get(index3) ));
			int min = Math.min( A.get(index1), Math.min( B.get(index2) , C.get(index3) ));
			
			int curr = max - min;
			
			if( curr < diff ){
				diff = curr;
			}
			
			//if diff is zero, we cannot get less than that
			if( diff == 0 ) break;
			
			//increment the index at which value is minimum, because increasing the smaller value can decrease the difference
			if( A.get(index1) == min ) index1++;
			else if( B.get(index2) == min ) index2++;
			else index3++;
			
			
		}
		
		return diff;
    }
	public static void main (String[] args) {
		
		System.out.println( minimize(Arrays.asList(1, 4, 10), Arrays.asList(2, 15, 20), Arrays.asList(10, 12)));
	}
}

