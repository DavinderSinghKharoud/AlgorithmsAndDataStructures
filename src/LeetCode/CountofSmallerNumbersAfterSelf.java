
/**
 * You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0] 
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.**/

import java.util.*;

public class CountofSmallerNumbersAfterSelf {
	
	//O( n square ) time complexity
	public static  List<Integer> countSmaller1(int[] nums) {
        
        int count = 0, len = nums.length;
        List<Integer> lst = new ArrayList<>();
        
        for( int i = 0; i<len; i++ ){
			count = 0;
			int num = nums[i];
			
			for( int j = i + 1; j<len; j++ ){
				if( nums[j] < num ){
					count++;
				}		
			}
			
			lst.add( count );
			
		}
        
        return lst;
    }
    
    
	public static void main (String[] args) {
		
		System.out.println( countSmaller2( new int[] {
			5,2,6,1
		}));
	}

	//O( n log(n) ) time complexity and O(n) space complexity
	public static List<Integer> countSmaller2(int[] nums) {
		
		int len = nums.length;
		Item[] Items = new Item[len];
		
		for( int i = 0; i<len; i++ ){
			Items[i] = new Item( nums[i], i );
		}
		
		int[] count = new int[len];
		mergeSort( Items, 0, len - 1, count );
		
		List<Integer> res = new ArrayList<>();
		for( int c: count ){
			res.add( c );
		}
		
		return res;
	}
	
	public static void mergeSort( Item[] Items, int low, int high, int[] count ){
		if( low >= high ) return;
		int mid = low + ( high - low )/2;
		mergeSort( Items, low, mid, count );
		mergeSort( Items, mid + 1, high, count );
		
		merge( Items, low, mid, mid + 1, high, count );
	}
	
	public static void merge( Item[] Items, int low, int lowEnd, int high, int highEnd, int[] count ){
		//len is inclusive to create new array ( count of integers)
		int len = highEnd - low + 1;
		Item[] sorted = new Item[len];
		int index = 0;
		int lowPtr = low, highPtr = high;
		
		int rightCounter = 0;
		
		while( lowPtr <= lowEnd && highPtr <= highEnd ){
			//as both the arrays are already sorted, if one number is less than the lowPrt then it is less than all other number after lowPtr in the Items
			if( Items[ highPtr ].val < Items[ lowPtr ].val ){
				rightCounter++;
				
				//this is only for sorting purposes
				sorted[index++] = Items[highPtr++];
			}else{
				
				count[ Items[ lowPtr ].index ] += rightCounter;
				
				sorted[index++] = Items[lowPtr++];
			}
			

		}

		//remaining numbers
		while( lowPtr <= lowEnd ){
			count[ Items[ lowPtr ].index ] += rightCounter;
			sorted[index++] = Items[lowPtr++];
		}

		while( highPtr <= highEnd ){
			sorted[ index++ ] = Items[ highPtr++ ];
		}

		//put the sorted array inside the Items array
		System.arraycopy( sorted, 0, Items, low, len );
	}
	static class Item{
		int val;
		int index;
		
		public Item( int v, int i ){
			val = v;
			index = i;
		}
	}
	
}

