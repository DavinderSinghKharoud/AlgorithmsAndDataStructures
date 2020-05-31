/*
Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

 

Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4
 */


public class MinimumCostTreeFromLeafValues {
	
	static int sum = 0;
	public static int mctFromLeafValues(int[] arr) {
		
		minSum( arr, 0, arr.length - 1);
		
		return sum;
	}
	
	public static int minSum( int[]arr, int start, int end ){
		if( start > end || start > arr.length || end < 0 ){
			return 0;
		}
		
		//only two numbers
		if( start == end - 1 ){
			sum += arr[start] * arr[end];
			return Math.max( arr[start], arr[end] );
		}
		
		if( start == end ){
			return arr[start];
		}
		
		int maxIndex = start;
		
		for( int i = start; i <= end; i++ ){
			if( arr[i] > arr[maxIndex] ){
				maxIndex = i;
			}
		}
		
		int maxLeft = minSum( arr, start, maxIndex - 1 );
		int maxRight = minSum( arr, maxIndex + 1, end );
		
		sum += maxLeft * arr[maxIndex];
		sum += maxRight * arr[maxIndex];
		
		return arr[maxIndex];
		
	}
	public static void main (String[] args) {

		System.out.println( mctFromLeafValues( new int[]{
				6, 2, 4
		}));
	}
}

