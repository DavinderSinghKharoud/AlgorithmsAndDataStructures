/*
 * Permutations.java
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

public class Permutations {
	
	public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> lst) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if( lst.size() == 0 ) return res;	
		
		helper( lst, res, 0 );
		return res;
    }
    
    public static void helper( ArrayList<Integer> lst, ArrayList<ArrayList<Integer>> res, int start ){
		if( start == lst.size() ){
			res.add( new ArrayList<>(lst) );
			return;
		}
		
		for( int index = start; index < lst.size(); index++ ){
			swap(lst, index, start);
			helper( lst, res, start + 1);
			swap(lst, start, index);
		}
		
		
}

	public static void swap( ArrayList<Integer> lst, int first, int second ){
		int temp = lst.get(first);
		lst.set(first, lst.get(second) );
		lst.set(second, temp);
	}
	public static void main (String[] args) {
		ArrayList<Integer> lst = new ArrayList<>();
		lst.add(1);lst.add(2);lst.add(3);

		ArrayList<ArrayList<Integer>> res = permute(lst);

		for (ArrayList<Integer> curr : res) {
			System.out.println(curr);
		}
	}
}

