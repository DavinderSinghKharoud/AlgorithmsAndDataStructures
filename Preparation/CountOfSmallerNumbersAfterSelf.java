package Preparation;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ou are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Example 2:
 * <p>
 * Input: nums = [-1]
 * Output: [0]
 * Example 3:
 * <p>
 * Input: nums = [-1,-1]
 * Output: [0,0]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class CountOfSmallerNumbersAfterSelf {
    public List<Integer> countSmaller(int[] nums) {
        TreeSet<Node> treeSet = new TreeSet<>();
        int len = nums.length;
        Node[] arr = new Node[len];
        for (int i = 0; i < len; i++) {
            arr[i] = new Node(nums[i], i);
        }

        mergeSort(arr, 0, len - 1);
        int[] ans = new int[len];
        for (Node node : arr) ans[node.index] = node.small;
        return Arrays.stream(ans).boxed().collect(Collectors.toList());
    }

    void mergeSort(Node[] nums, int start, int end) {
        if (end - start == 0) return;
        int mid = (end + start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        merge(nums, start, mid, end);
    }

    void merge(Node[] nums, int start, int mid, int end) {
        int len = end - start + 1;
        Node[] temp = new Node[len];

        int l = start, r = mid + 1;
        int smallRight = 0; //Otherwise it would be O(n) --> O(n ^ 2)
        int index = 0;
        while (l <= mid && r <= end) {
            if (nums[l].val <= nums[r].val) {
                temp[index++] = nums[l];
                nums[l++].small += smallRight;
            } else {
                smallRight++;
                temp[index++] = nums[r++];
            }
        }

        while (l <= mid) {
            temp[index++] = nums[l];
            nums[l++].small += smallRight;
        }

        while (r <= end) {
            temp[index++] = nums[r++];
        }

        //System.out.println(start + " " + end + " " + temp.length);
        for (int i = start; i <= end; i++) {
            nums[i] = temp[i - start];
        }
    }

    static class Node {
        int val, index;
        int small;

        public Node(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}
