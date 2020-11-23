package algorithms.InterviewBit;

import java.util.Arrays;
import java.util.List;

/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * 'n' vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 *
 * Your program should return an integer which corresponds to the maximum area of water that can be contained ( Yes, we know maximum area instead of maximum volume sounds weird. But this is 2D plane we are working with for simplicity ).
 *
 *  Note: You may not slant the container.
 * Example :
 *
 * Input : [1, 5, 4, 3]
 * Output : 6
 *
 * Explanation : 5 and 3 are distance 2 apart. So size of the base = 2. Height of container = min(5, 3) = 3.
 * So total area = 3 * 2 = 6
 * See Expected Output
 */


public class ContainerWithMostWater {

    public static int maxArea(List<Integer> lst) {
		
		int len = lst.size();
		if( len <= 1 ) return 0;
		
		int start = 0;
		int end = len - 1;
		int res = 0;
		
		while( start < end ){
			int wall1 = lst.get(start);
			int wall2 = lst.get(end);
			
			int area = (end - start) * Math.min(wall1, wall2);
			res = Math.max( area, res );
			
			if( wall1 < wall2 ){
				start++;
			}else{
				end--;
			}
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println( maxArea(Arrays.asList(1,8,6,2,5,4,8,3,7)));
    }
}
