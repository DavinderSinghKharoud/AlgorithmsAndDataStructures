package InterviewBit;

import java.util.*;

/**
 * Given an integer array FindGreatestCommonDivisor of non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 *
 *
 * Problem Constraints
 * 1 <= |FindGreatestCommonDivisor| <= 100000
 *
 *
 *
 * Input Format
 * The only argument given is integer array FindGreatestCommonDivisor.
 *
 *
 *
 * Output Format
 * Return the total water it is able to trap after raining.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 * Input 2:
 *
 *  FindGreatestCommonDivisor = [1, 2]
 *
 *
 * Example Output
 * Output 1:
 *
 *  6
 * Output 2:
 *
 *  0
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *
 *  In this case, 6 units of rain water (blue section) are being trapped.
 * Explanation 2:
 *
 *  No water is trapped.
 */
public class RainWaterTrapped {

    public static int trap(final List<Integer> lst) {
		
		int len = lst.size();
		
		if( len == 0 ) return 0;
		
		int total = 0;
		int[] leftMax = new int[len];
		int[] rightMax = new int[len];
		
		leftMax[0] = lst.get(0);
		rightMax[len - 1] = lst.get(len - 1);
		for( int index = 1; index < len; index++ ){
			leftMax[index] = Math.max( lst.get(index), leftMax[index - 1] );
		}
		
		for( int index = len - 2; index >= 0; index-- ){
			rightMax[index] = Math.max( lst.get(index), rightMax[index + 1]);
		}
		
		for( int index = 0; index < len; index++ ){
			
			total += Math.min( leftMax[index], rightMax[index] ) - lst.get(index);
		}
		
		return total;
    }
    public static void main(String[] args) {

		System.out.println( trap3(Arrays.asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)));
		//System.out.println( trap2(Arrays.asList(5, 1, 4)));
    }

    // Time and Space Complexity O(n)
	public static int trap2(final List<Integer> lst) {
    	int len = lst.size();
    	int index = 0;
		int ans = 0;
    	Stack<Integer> stack = new Stack<>();

    	while ( index < len ){
    		while ( !stack.isEmpty() && lst.get(index) > lst.get(stack.peek()) ){
    			int previous = stack.pop();

    			if(stack.isEmpty()) break;

    			int distance = index - stack.peek() - 1 ;
    			int bounded_height = Math.min(lst.get(index), lst.get(stack.peek())) - lst.get(previous);
    			ans += distance * bounded_height;
			}
    		stack.push(index++);
		}

    	return ans;
	}

	//Time complexity O(n) and space complexity O(1)
	public static int trap3(final List<Integer> lst) {

    	int len = lst.size();
    	int left_max = 0;
    	int right_max = 0;
		int result = 0;
    	int low = 0, high = len - 1;

    	while ( low <= high ){
    		if( lst.get(low) < lst.get(high) ){
    			if( lst.get(low) > left_max ){
    				left_max = lst.get(low);
				}else{
    				result += left_max - lst.get(low);
				}
    			low++;
			}else{
    			if( lst.get(high) > right_max ){
    				right_max = lst.get(high);
				}else{
    				result += right_max - lst.get(high);
				}
    			high--;
			}
		}

    	return result;
	}

}
