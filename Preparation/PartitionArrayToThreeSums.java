package Preparation;

import java.util.*;

/**
 * Given an array of integers arr, return true if we can partition the array into three non-empty parts with equal sums.
 *
 * Formally, we can partition the array if we can find indexes i + 1 < j with (arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [0,2,1,-6,6,-7,9,1,2,0,1]
 * Output: true
 * Explanation: 0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * Example 2:
 *
 * Input: arr = [0,2,1,-6,6,7,9,-1,2,0,1]
 * Output: false
 * Example 3:
 *
 * Input: arr = [3,3,6,5,-2,2,5,1,-9,4]
 * Output: true
 * Explanation: 3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 */
public class PartitionArrayToThreeSums {
    public boolean canThreePartsEqualSum(int[] arr) {
        int len = arr.length;
        int sum = Arrays.stream(arr).sum();
        if (sum % 3 != 0) return false;
        int currSum = 0;
        int parts = 0;
        int target = sum / 3;
        for (int i = 0; i < len; i++) {
            currSum += arr[i];
            if (currSum == target) {
                parts++;
                currSum = 0;
            }
        }
        return parts >= 3;
    }
}
