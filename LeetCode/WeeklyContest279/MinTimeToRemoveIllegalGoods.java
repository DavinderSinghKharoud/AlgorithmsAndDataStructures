package LeetCode.WeeklyContest279;

/**
 * You are given a 0-indexed binary string s which represents a sequence of train cars. s[i] = '0' denotes that the ith car does not contain illegal goods and s[i] = '1' denotes that the ith car does contain illegal goods.
 *
 * As the train conductor, you would like to get rid of all the cars containing illegal goods. You can do any of the following three operations any number of times:
 *
 * Remove a train car from the left end (i.e., remove s[0]) which takes 1 unit of time.
 * Remove a train car from the right end (i.e., remove s[s.length - 1]) which takes 1 unit of time.
 * Remove a train car from anywhere in the sequence which takes 2 units of time.
 * Return the minimum time to remove all the cars containing illegal goods.
 *
 * Note that an empty sequence of cars is considered to have no cars containing illegal goods.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1100101"
 * Output: 5
 * Explanation:
 * One way to remove all the cars containing illegal goods from the sequence is to
 * - remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
 * - remove a car from the right end. Time taken is 1.
 * - remove the car containing illegal goods found in the middle. Time taken is 2.
 * This obtains a total time of 2 + 1 + 2 = 5.
 *
 * An alternative way is to
 * - remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
 * - remove a car from the right end 3 times. Time taken is 3 * 1 = 3.
 * This also obtains a total time of 2 + 3 = 5.
 *
 * 5 is the minimum time taken to remove all the cars containing illegal goods.
 * There are no other ways to remove them with less time.
 * Example 2:
 *
 * Input: s = "0010"
 * Output: 2
 * Explanation:
 * One way to remove all the cars containing illegal goods from the sequence is to
 * - remove a car from the left end 3 times. Time taken is 3 * 1 = 3.
 * This obtains a total time of 3.
 *
 * Another way to remove all the cars containing illegal goods from the sequence is to
 * - remove the car containing illegal goods found in the middle. Time taken is 2.
 * This obtains a total time of 2.
 *
 * Another way to remove all the cars containing illegal goods from the sequence is to
 * - remove a car from the right end 2 times. Time taken is 2 * 1 = 2.
 * This obtains a total time of 2.
 *
 * 2 is the minimum time taken to remove all the cars containing illegal goods.
 * There are no other ways to remove them with less time.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 105
 * s[i] is either '0' or '1'.
 */
public class MinTimeToRemoveIllegalGoods {
    public static void main(String[] args) {
        MinTimeToRemoveIllegalGoods o = new MinTimeToRemoveIllegalGoods();
        System.out.println(o.minimumTime("011001111111101001010000001010011"));
    }

    public int minimumTime(String s) {
        int len = s.length();
        int[] left = new int[len], right = new int[len];
        int onesCount = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) - '0' == 1) onesCount++;
        }
        for (int i = 0; i < len; i++) {
            int curr = s.charAt(i) - '0';
            int prev = (i - 1 >= 0) ? left[i - 1] : 0;
            left[i] = prev + ((curr == 0) ? -1 : 1);
        }
        for (int i = len - 1; i >= 0; i--) {
            int curr = s.charAt(i) - '0';
            int prev = (i + 1 < len) ? right[i + 1] : 0;
            right[i] = prev + ((curr == 0) ? -1 : 1);
        }

        int[] leftMax = new int[len], rightMax = new int[len];
        for (int i = 0; i < len; i++) {
            leftMax[i] = left[i];
            if (i - 1 >= 0) {
                leftMax[i] = Math.max(leftMax[i - 1], leftMax[i]);
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            rightMax[i] = right[i];
            if (i + 1 < len) {
                rightMax[i] = Math.max(rightMax[i], rightMax[i + 1]);
            }
        }

        int reduceBy = getNonNegative(leftMax[len - 1]);
        for (int i = 0; i < len - 1; i++) {
            reduceBy = Math.max(reduceBy, getNonNegative(leftMax[i]) + getNonNegative(rightMax[i + 1]));
        }
        return onesCount * 2 - reduceBy;
    }

    int getNonNegative(int num) {
        return Math.max(num, 0);
    }
}
