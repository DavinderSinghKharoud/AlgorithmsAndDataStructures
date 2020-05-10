package algorthims.LeetCode;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.**/
 

public class LargestRectangleinHistogram {
	
	public static int largestRectangleArea(int[] heights) {
		
		int max = 0;
		int len = heights.length;
		
		for( int i = 0; i < len; i++ ){
			int reach = i-1;
			int count = 1;
			int curr_height = heights[i];
			
			//see the left side
			while( reach >= 0 ){
				if( heights[ reach-- ] < curr_height ){
					break;
				}
				
				count ++;
			}
			
			reach = i+1;
			//see the right side
			while( reach <= len - 1 ){
				if( heights[ reach++ ] < curr_height ){
					break;
				}
				count++;
			}
			
			int area = count * curr_height;
			max = Math.max( area, max );
		}
        
        return max;
    }
    
	public static void main (String[] args) {
		
		System.out.println( largestRectangleArea( new int[]{
			2,1,5,6,2,3
		}));
	}
}

