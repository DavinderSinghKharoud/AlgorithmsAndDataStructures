package algorithms.LeetCode;
/*
You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:

Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:

Input: [1, 2, 1, 2]
Output: False
Note:

The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

 */


public class TwentyFourGame {

	//O( 1 ) time and space complexity because there is a hard limit of 9216 possibilities and we O(1) work for them
	//even the space complexity is bounded as arrays can have most 4 elements
	public static boolean judgePoint24(int[] nums) {
        double[] arr = new double[]{
			nums[0], nums[1], nums[2], nums[3]
		};
		
		return helper( arr );
    }
    
    public static boolean helper( double[] arr ){
		int len = arr.length;
		if( len == 1 ){
			return Math.abs( arr[0] - 24 ) < 0.001;
		}
		
		for(int start = 0; start < len; start++ ){
			for( int end = start + 1; end < len; end++ ){
				
				double[] remaining = new double[len - 1];
				
				for(int inBetween = 0, index = 0; inBetween < len; inBetween++ ){
					if( inBetween != start && inBetween != end ){
						remaining[index++] = arr[inBetween];
					}
				}
				
				for( double d: compute( arr[start], arr[end] ) ){
					remaining[ remaining.length - 1 ] = d;
					if( helper( remaining ) ) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static double[] compute( double x, double y ){
		return new double[]{
			x + y, x - y, y - x, x * y, x / y, y / x
		};
	}
	
	
	public static void main (String[] args) {
		
		System.out.println( judgePoint24( new int[]{
				1, 2, 1, 2
		}));
	}
}

