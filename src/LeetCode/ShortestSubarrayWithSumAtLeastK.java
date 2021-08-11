package LeetCode;

import java.util.*;

public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {

        System.out.println(shortestSubarray(new int[]{84,-37,32,40,95}, 167));
    }

    public static int shortestSubarray(int[] arr, int k) {
        int len = arr.length;
        int res = len + 1;
        int[] prefix = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefix[i + 1] += prefix[i] + arr[i];

        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int index = 0; index < prefix.length; index++) {

            // Remove the number if sum comes out to be negative
            while (!queue.isEmpty() && prefix[index] <= prefix[queue.peekLast()]) {
                queue.pollLast();
            }

            while (!queue.isEmpty() && prefix[index] >= prefix[queue.peekFirst()] + k) {
                res = Math.min(res, index - queue.pollFirst());
            }
            queue.addLast(index);
        }


        return (res == len + 1) ? -1 : res;
    }
        
}
