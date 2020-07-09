/*
 * WaveArray.java
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

public class WaveArray {
	
	public static ArrayList<Integer> wave(ArrayList<Integer> A) {
		ArrayList<Integer> res = new ArrayList<>();
		int len = A.size();
		if( len == 0 ) return res;
		
		Collections.sort(A);
		
		int index = 0;
		while ( index < len ){
			if( index + 1 < len ){
				res.add(A.get(index + 1));
			}
			
			res.add( A.get(index) );
			
			index += 2;
		}

		
		return res;
    }
	public static void main (String[] args) {

		ArrayList<Integer> lst = new ArrayList<>();
		lst.add(1);
		lst.add(2);
		lst.add(3);
		lst.add(4);
		lst.add(5);
		lst.add(6);
		System.out.println( wave( lst ));
	}
}

