package algorthims.InterviewBit;/*
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:

Si % Sj = 0 or Sj % Si = 0.

If there are multiple solutions, return any subset is fine.

Example 1:

Input: [1,2,3]
Output: [1,2] (of course, [1,3] will also be ok)
Example 2:

Input: [1,2,4,8]
Output: [1,2,4,8]
 */

import javax.print.attribute.standard.PresentationDirection;
import java.util.*;
public class  LargestDivisibleSubset {

    //O(n square) time complexity and O(n) space complexity
	public static List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        if(len == 0){
            return res;
        }

        int max = 0;
        int maxIndex = -1;
        Arrays.sort(nums);
        //to store len
        int[] arr = new int[nums.length];
        //to store the index's
        int[] index = new int[nums.length];
        Arrays.fill(arr, 1);
        Arrays.fill(index, -1);
        for (int i = 0; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if( nums[i] % nums[j] == 0 && arr[j] + 1 >= arr[i] ){
                    arr[i] = arr[j] + 1;
                    index[i] = j;
                }
            }

            if( max < arr[i] ){
                max = arr[i];
                maxIndex = i;
            }
        }

        while ( maxIndex >= 0 ){
            res.add(nums[maxIndex]);
            maxIndex = index[maxIndex];
        }

        return res;
    }
	public static void main (String[] args) {
        System.out.println( largestDivisibleSubset(new int[]{
                1,2,4,8,5,6
        }));
	}
}

