package Algorithms.LeetCode;

/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
* **/

public class TrappingRainWater {
	
	//O(n) time and space complexity
	public static int trap(int[] height) {
        
        int ans = 0;
        int len = height.length;
        int[]left = new int[ len ];
        int[] right = new int[ len ];
        
		if( len <= 1 ){
			return 0;
		}
		
		//calculating max from left side
		left[0] = height[0];
		for( int index = 1; index<len; index++ ){
			left[index] = Math.max( left[index - 1], height[index] );
		}
		
		//calculating max from right side
		right[ len - 1 ] = height[ len - 1 ];
		for( int index = len - 2; index >= 0; index -- ){
			right[ index ] = Math.max( right[ index + 1 ], height[ index ]);
		}
		
		for( int index = 0; index < len; index ++ ){
			ans += Math.min( left[index], right[index]) - height[index];
		}
		
		return ans;
        
    }
	public static void main (String[] args) {
		System.out.println( trap2( new int[]{
			0,1,0,2,1,0,1,3,2,1,2,1
		}) );
	}
	
	public static int trap2(int[] height) {
		int len = height.length;
		int res = 0, left = 0, right = len - 1, level = 0;
		
		while( left < right ){
			//lower wall
			int lower = ( height[left] < height[right] ) ? height[left++] : height[right--];
			level = Math.max( level, lower );
			res += level - lower;
		}
		
		return res;
	}
}

