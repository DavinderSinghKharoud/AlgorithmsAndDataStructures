package Algorithms.LeetCode;

import java.util.Arrays;

/**
 * We sampled integers between 0 and 255, and stored the results in an array count:  count[k] is the number of integers we sampled equal to k.
 *
 * Return the minimum, maximum, mean, median, and mode of the sample respectively, as an array of floating point numbers.  The mode is guaranteed to be unique.
 *
 * (Recall that the median of a sample is:
 *
 * The middle element, if the elements of the sample were sorted and the number of elements is odd;
 * The average of the middle two elements, if the elements of the sample were sorted and the number of elements is even.)
 *
 *
 * Example 1:
 *
 * Input: count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,3.00000,2.37500,2.50000,3.00000]
 * Example 2:
 *
 * Input: count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
 * Output: [1.00000,4.00000,2.18182,2.00000,1.00000]
 *
 *
 * Constraints:
 *
 * count.length == 256
 * 1 <= sum(count) <= 10^9
 * The mode of the sample that count represents is unique.
 * Answers within 10^-5 of the true value will be accepted as correct.
 */
public class StatisticsFromALargeSample {

    public static double[] sampleStats(int[] count) {
			
		int len = count.length;
		double[] ans = new double[5];
		
		if( len == 0 ) return ans;
		double minIndex = -1;
		double maxIndex = 0;
		double sum = 0;
		double mode = 0;
		double maxModeRef = 0;
		int totalDuplicates = 0;
		
		for(int index = 0; index < len; index++ ){
			if( count[index] != 0 ){
				if( minIndex == -1 ) minIndex = index;
				maxIndex = index;
				
				sum += index * count[index];
				
				if( maxModeRef < count[index] ){
					maxModeRef = count[index];
					mode = index;
				}
				totalDuplicates += count[index];
			}
		}
		
		ans[0] = minIndex;
		ans[1] = maxIndex;
		ans[2] = sum/totalDuplicates;
		ans[4] = mode;
		
		//To find median
		ans[3] = findMedian( count, totalDuplicates/2, (int)minIndex, (int)maxIndex );
		if( totalDuplicates % 2 == 0 ){
			ans[3] = (ans[3] + findMedian(count, totalDuplicates/2 + 1, (int)minIndex, (int)maxIndex )) / 2;
		}
		
		return ans;
    }
    
    static int findMedian( int[] count, long get, int minIndex, int maxIndex ){
		
		while( get > count[minIndex] ){
			get -= count[minIndex++];
		}
		
		return minIndex;
	}
    public static void main(String[] args) {

		System.out.println(Arrays.toString(sampleStats(new int[]{0, 1, 3, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0})));
    }
}
