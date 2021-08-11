/*
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */

import java.util.*;
public class MinSumPathinTriangle {
	
	public static int minimumTotal(ArrayList<ArrayList<Integer>> a) {
		int n = a.size();
		
		if( n == 0 ){
			return -1;
		}
		int max = Integer.MAX_VALUE;
		
		int[] previous = new int[n];
		previous[0] = a.get(0).get(0);
		
		for(int row = 1; row < n; row++ ){
			
			int colLen = a.get(row).size();
			int[] curr = new int[n];
			
			for( int col = 0, index = 0; col < colLen; col++ ){
				int num = a.get(row).get(col);
				
				if( col == 0 ){
					curr[index++] = num + previous[0];
				}else if( col == colLen - 1 ){
					curr[index] = num + previous[ colLen - 2 ]; 
				}else{
					
					curr[index++] = num + Math.min( previous[col - 1], previous[col] );
				}
			}
			
			previous = curr;
		}
		
		for(int index = 0; index < n; index++ ){
			max = Math.min( previous[index], max );
		}
		return max;
	}
	public static void main (String[] args) {

		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();

		ArrayList<Integer> temp1 = new ArrayList<>();
		temp1.add(2);

		ArrayList<Integer> temp2 = new ArrayList<>();
		temp2.add(3);temp2.add(4);
		ArrayList<Integer> temp3 = new ArrayList<>();
		temp3.add(6);temp3.add(5);temp3.add(7);
		ArrayList<Integer> temp4 = new ArrayList<>();
		temp4.add(4);temp4.add(1);temp4.add(8);temp4.add(3);

		lst.add(temp1);
		lst.add(temp2);
		lst.add(temp3);
		lst.add(temp4);

		System.out.println( minimumTotal( lst ));
	}
}

