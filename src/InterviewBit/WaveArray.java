/*
Given an array of integers, sort the array into a wave like array and return it,
In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....

Example

Given [1, 2, 3, 4]

One possible answer : [2, 1, 4, 3]
Another possible answer : [4, 1, 3, 2]
 NOTE : If there are multiple answers possible, return t
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

