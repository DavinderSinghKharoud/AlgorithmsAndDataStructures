/*
 * GreatestCommonDivisor.java
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


public class GreatestCommonDivisor {
	
	public static int gcd(int A, int B) {
		
		int first = Math.max( A, B );
		int second = Math.min( A, B );
		
		while( second > 0 ){
			int mod = first % second;
			first = second;
			second = mod;
		}
		
		return first;
    }
	public static void main (String[] args) {
		
		System.out.println( gcd( 8, 14 ) );
	}
}

