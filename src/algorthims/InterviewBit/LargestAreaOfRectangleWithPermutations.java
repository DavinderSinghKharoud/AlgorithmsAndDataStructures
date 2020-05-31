/*
Given a binary grid i.e. a 2D grid only consisting of 0’s and 1’s, find the area of the largest rectangle inside the grid such that all the cells inside the chosen rectangle should have 1 in them. You are allowed to permutate the columns matrix i.e. you can arrange each of the column in any order in the final grid. Please follow the below example for more clarity.

Lets say we are given a binary grid of 3 * 3 size.

1 0 1

0 1 0

1 0 0

At present we can see that max rectangle satisfying the criteria mentioned in the problem is of 1 * 1 = 1 area i.e either of the 4 cells which contain 1 in it. Now since we are allowed to permutate the columns of the given matrix, we can take column 1 and column 3 and make them neighbours. One of the possible configuration of the grid can be:

1 1 0

0 0 1

1 0 0

Now In this grid, first column is column 1, second column is column 3 and third column is column 2 from the original given grid. Now, we can see that if we calculate the max area rectangle, we get max area as 1 * 2 = 2 which is bigger than the earlier case. Hence 2 will be the answer in this case.
 */

import java.util.*;

public class LargestAreaOfRectangleWithPermutations {

	public static void main (String[] args) {
		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
		ArrayList<Integer> inside1 = new ArrayList<>();
		inside1.add(1); inside1.add(0); inside1.add(1);

		ArrayList<Integer> inside2 = new ArrayList<>();
		inside2.add(1); inside2.add(0); inside2.add(1);

		ArrayList<Integer> inside3 = new ArrayList<>();
		inside3.add(1); inside3.add(0); inside3.add(1);

		lst.add(inside1); lst.add(inside2); lst.add( inside3);

		System.out.println( solve2( lst ));
	}
	
	 public static int solve2(ArrayList<ArrayList<Integer>> lst) {
		int len1 = lst.size();
		if( len1 == 0 ) return 0;
		int len2 = lst.get(0).size();
		
		int[][] hist = new int[len1][len2];
		
		//creating the dp 
		for( int col = 0; col < len2; col++ ){
			
			hist[0][col] = lst.get(0).get(col);
			for( int row = 1; row < len1; row++ ){
				hist[row][col] = ( lst.get(row).get(col) == 0 ) ? 0 : hist[row - 1][col] + 1;
			}
		}
		
		//sorting the list using counting sort
		
		for( int row = 0; row < len1; row++ ){
			int count[] = new int[len1 + 1];
			
			//counting occurance
			for( int col = 0; col < len2; col++ ){
				count[ hist[row][col] ] ++;
			}
			
			//traverse the count from right side to sort in decreasing order
			int col_no = 0;
			for( int i = len1; i >= 0; i-- ){
				
				if( count[i] > 0 ){
					
					//we need to insert the values
					for( int j = 0; j < count[i]; j++ ){
						hist[row][col_no++] = i;
					}
				}
			}
			
		}
		
		// Traverse the sorted array to find the maximum area
		int max = 0;
		
		for( int row = 0; row < len1; row++ ){
			
			for( int col = 0; col < len2; col++ ){
				max = Math.max( (col + 1) * hist[row][col], max );
			}
		}
		
		return max;
		
	}
	
	
}

