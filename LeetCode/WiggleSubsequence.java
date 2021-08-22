/*
 FindGreatestCommonDivisor sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate between positive and negative. The first difference (if one exists) may be either positive or negative. FindGreatestCommonDivisor sequence with fewer than two elements is trivially a wiggle sequence.

For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive and the second because its last difference is zero.

Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. FindGreatestCommonDivisor subsequence is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements in their original order.

Example 1:

Input: [1,7,4,9,2,5]
Output: 6
Explanation: The entire sequence is a wiggle sequence.
Example 2:

Input: [1,17,5,10,13,15,10,5,16,8]
Output: 7
Explanation: There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
Example 3:

Input: [1,2,3,4,5,6,7,8,9]
Output: 2
Follow up:
Can you do it in O(n) time?
 */


public class WiggleSubsequence {

    //Greedy solution O(n) time complexity and O(1) time complexity
    public static int wiggleMaxLength1(int[] nums) {

        int len = nums.length;
        if (len < 2) return len;

        int prevDiff = nums[1] - nums[0];
        int count = (prevDiff != 0) ? 2 : 1;

        for (int index = 2; index < len; index++) {
            int diff = nums[index] - nums[index - 1];
            if ((diff > 0 && prevDiff <= 0) || (diff < 0 && prevDiff >= 0)) {
                count++;
                prevDiff = diff;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(wiggleMaxLength3(new int[]{
                1, 17, 5, 10, 13, 15, 10, 5, 16, 8
        }));
    }

    // O(n) time complexity and O(1) time complexity
    public static int wiggleMaxLength2(int[] nums) {

        int len = nums.length;
        if (len < 2) return len;

        int up = 1;
        int down = 1;

        for (int index = 1; index < len; index++) {
            int diff = nums[index] - nums[index - 1];
            if (diff > 0) {
                up = down + 1;
            } else if (diff < 0) {
                down = up + 1;
            }
        }

        return Math.max(up, down);
    }

    public static int wiggleMaxLength3(int[] nums) {
        int len = nums.length;
        if (len < 2) return len;

        int[] up = new int[len];
        int[] down = new int[len];
        up[0] = 1;
        down[0] = 1;
        for (int index = 1; index < len; index++) {
            if( nums[index] > nums[index - 1]  ){
                up[index] = down[index - 1] + 1;
                down[index] = down[index - 1];
            }else if( nums[index] < nums[index - 1] ){
                down[index] = up[index - 1] + 1;
                up[index] = up[index - 1];
            }else{
                down[index] = down[index - 1];
                up[index] = up[index - 1];
            }
        }

        return Math.max( up[len - 1], down[len - 1] );
    }

}

