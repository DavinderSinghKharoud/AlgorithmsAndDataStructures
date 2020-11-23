package algorithms.HackerRank;

/**
 * You are given a list of  numbers . For each element at position  (), we define  and  as:
 *  = closest index j such that j < i and . If no such j exists then  = 0.
 *  = closest index k such that k > i and . If no such k exists then  = 0.
 * We define  =  * . You need to find out the maximum  among all i.
 * Input Format
 * The first line contains an integer , the number of integers. The next line contains the  integers describing the list a[1..N].
 * Constraints
 *
 *
 * Output Format
 * Output the maximum  among all indices from  to .
 * Sample Input
 * 5
 * 5 4 3 4 5
 * Sample Output
 * 8
 * Explanation
 * We can compute the following:
 *
 *
 *
 *
 *
 * The largest of these is 8, so it is the answer.
 */
 
 import java.util.*;
public class FindMaximumIndexProduct {

	//O(n square ) time complexity
    static int solve(int[] arr) {
        
        int len = arr.length;
        int res = -1;
        
        for(int index = 0; index < len; index++ ){
			
			int left = getLeft( index, arr, arr[index] );
			int right = getRight( index, arr, arr[index] );
			
			res = Math.max( res, left * right );
		}
		
		return res;
    }
    
    public static int getLeft( int currIndex, int[] arr, int num ){
		
		for(int index = currIndex - 1; index >= 0; index-- ){
			if( arr[index] > num ) return index + 1;
		}
		
		return 0;
	}
	
	public static int getRight( int currIndex, int[] arr, int num ){
		
		for(int index = currIndex + 1; index < arr.length; index++ ){
			if( arr[index] > num ) return index + 1;
		}
		
		return 0;
	}

    public static void main(String[] args) {

		System.out.println( solve2( new int[]{5, 4, 3, 4, 5}));
    }

    //O(n) time and space complexity
	static long solve2(int[] arr) {
		
		long res = 0;
		int len = arr.length;
		if( len == 0 ) return 0;
		
		Stack<Integer> stack = new Stack<>();
		stack.add(0);
		
		for( int index = 1; index < len; index++ ){
			
			int num = arr[index];
			
			while( !stack.isEmpty() && arr[stack.peek()] < num ){
				int curr = arr[stack.pop()];
				
				if( !stack.isEmpty() ){
					if( curr == arr[stack.peek()] ) continue;
					else{
						res = Math.max( res, (long) (stack.peek() + 1) * (index + 1) );
					}
				}
				
			}
			
			stack.add(index);
		}
		
		return (long)res;

	}
}
