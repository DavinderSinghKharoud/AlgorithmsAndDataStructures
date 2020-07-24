package algorthims.LeetCode;

import java.util.*;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Follow up:
 * Could you solve it in linear time?
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 */
public class SlidingWindowMaximum {

    // O(n * k) time complexity
    public static int[] maxSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        int[] res = new int[len - k + 1];

        if (len < k) {
            return res;
        }

        int start = 0, end = k;
        int i = 0;

        while (end < len + 1) {
            int num = findMax(start, end, nums);
            res[i++] = num;
            start++;
            end++;
        }

        return res;
    }

    public static int findMax(int start, int end, int[] nums) {
        int max = nums[start];

        for (int index = start + 1; index < end; index++) {
            max = Math.max(max, nums[index]);
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println(Arrays.toString(maxSlidingWindow1(new int[]{
                648, 614, 490, 138, 657, 544, 745, 582, 738, 229, 775, 665, 876, 448, 4, 81, 807, 578, 712, 951, 867, 328, 308, 440, 542, 178, 637, 446, 882, 760, 354, 523, 935, 277, 158, 698, 536, 165, 892, 327, 574, 516, 36, 705, 900, 482, 558, 937, 207, 368
        }, 9)));

    }

    //O(k) space complexity and O(n) time complexity
    public static int[] maxSlidingWindow1(int[] nums, int k) {

        int len = nums.length;

        int[] res = new int[len - k + 1];
        if (len < k) {
            return res;
        }

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < k; i++) {
            while ( !deque.isEmpty() && nums[deque.getLast()] < nums[i] ){
                deque.removeLast();
            }
            deque.addLast( i );
        }

        int index = 0;
        res[index++] = nums[ deque.peekFirst() ];

        int start = 1;
        int end = k;

        while( end < len ){

            remove_extra( start, deque );
            while ( !deque.isEmpty() && nums[deque.getLast()] < nums[end] ){
                deque.removeLast();
            }

            deque.addLast( end );

            res[index++] = nums[ deque.peekFirst() ];
            start++;
            end++;
        }

        return res;
    }

    private static void remove_extra(int start, Deque<Integer> deque) {

        while ( !deque.isEmpty() && start > deque.peekFirst() ) {
            deque.removeFirst();
        }

    }

    //Most efficient
    public static int[] maxSlidingWindow3(int[] nums, int k) {

        int left = 0, right = 0, max = Integer.MIN_VALUE, mi = 0, len = nums.length;

        for(int i = 0; i < k;i++){
            if(nums[i] > max){
                max = nums[i];
                mi = i;
            }
            right = i;
        }

        int[] result = new int[len - k + 1];
        result[0] = max;
        for(int i = right ; i < len; i++){
            if(mi == left - 1){
                //findNextMax
                int t = left;
                max = Integer.MIN_VALUE;
                while(t <= i){
                    if(nums[t] > max){
                        max = nums[t];
                        mi = t;
                    }
                    t++;
                }

            } else if(nums[i] > max){
                max = nums[i];
                mi = i;
            }

            left++;
            result[i-k+1] = max;
        }
        return result;

    }
}

