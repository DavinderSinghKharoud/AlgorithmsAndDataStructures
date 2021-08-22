/*
 * WordBreakPossible.java
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

public class WordBreakPossible {
	
	public static int wordBreak(String str, List<String> lst) {
		
		int len = str.length();
		if( len == 0 ) return 0;

		Map<Integer, Boolean> map = new HashMap<>();


		Set<String> set = new HashSet<>(lst);
		
		return (helper( str, set, map, 0 ) ) ? 1: 0;
    }
    
    public static boolean helper( String str, Set<String> set, Map<Integer, Boolean> map, int start ){
		
		if( start >= str.length() ){
			return true;
		}
		
		if( map.containsKey(start) ){
			return map.get(start);
		}

		StringBuilder sbr = new StringBuilder();
		for( int index = start; index < str.length(); index++ ){
			sbr.append( str.charAt(index) );
			
			if( set.contains(sbr.toString()) && helper( str, set, map, index + 1) ){
				return true;
			}
			
		}
		
		map.put(start, false);
		
		return false;
	}

	public static void main (String[] args) {

		System.out.println( wordBreak("a", Arrays.asList("trainer", "my", "interview")));
	}
}

