package LeetCode;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.**/
 
 import java.util.*;

public class

LargestRectangleinHistogram {

	//O(n square) time complexity
	public static int largestRectangleArea1(int[] heights) {
		int max = 0;

		for( int i = 0; i<heights.length; i++ ){

			int len = 1;
			int left = i - 1;
			int right = i + 1;


			while( left >= 0 && heights[left] >= heights[i] ){
				len++;
				left--;
			}

			while( right < heights.length && heights[right] >= heights[i] ){
				len++;
				right++;
			}

			int area = heights[i] * len;
			max = Math.max( max, area );
		}

		return max;
	}
	public static void main (String[] args) {
		
		System.out.println( largestRectangleArea2( new int[]{
				2, 1, 2
		}));
	}

	//using stack
	public static int largestRectangleArea2(int[] heights) {

			Stack<Integer> stack = new Stack<>();
			int max = 0;
			
			
			for( int index = 0; index<=heights.length; index++ ){
				while ( !stack.isEmpty() && heights[ stack.peek() ] > ((index == heights.length) ? -1: heights[index]) ){
					int val = stack.pop();
					int range = (stack.isEmpty()) ? index: index - 1 - stack.peek();
					max = Math.max( max, range * heights[val] );
				}

				stack.push( index );
			}
			

			return max;
		
	}

	public static int largestRectangleArea4(int[] heights) {
		int len = heights.length;
		Stack<Integer> stack = new Stack<>();
		int ans = -1;
		for(int i = 0;i < len; i++){
			int curr = heights[i];
			while(!stack.isEmpty() && heights[stack.peek()] > curr){
				int pop = stack.pop();
				ans = Math.max(ans, findArea(pop, i, heights[pop]));
			}
			stack.add(i);
		}

		if(!stack.isEmpty()){
			int last = stack.peek() + 1;
			while(!stack.isEmpty()){
				int pop = stack.pop();
				ans = Math.max(ans, findArea(pop, last, heights[pop]));
			}
		}
		return ans;
	}

	static int findArea(int a, int b, int height){
		return ( height * (b - a));
	}

	public static int largestRectangleArea3(int[] a) {
		if(a==null || a.length==0)
			return 0;
		int n = a.length;
		int[] left = new int[n];
		left[0]= -1;
		int[] right = new int[n];
		right[n-1]= n;
		// calculate the next smallest from left
		for(int i =1;i<n;i++)
		{
			int p=i-1;
			while(p>=0 && a[p]>=a[i])
			{
				p = left[p];
			}
			left[i] = p;

		}
		//calculate the next smallest from right.
		for(int i =n-2;i>=0;i--)
		{
			int p=i+1;

			while(p<n && a[p]>=a[i])
			{
				p = right[p];
			}
			right[i] = p;

		}

		int maxArea= 0;
		for(int i =0; i<n;i++)
		{
			maxArea= Math.max(maxArea, a[i] *(right[i]-left[i]-1));
			//      System.out.println(maxArea +" "+ a[i] *(right[i]-left[i]-1));
		}
		return (maxArea);
	}

}

