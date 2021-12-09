package LeetCode;

import java.util.*;

/**
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
 *
 * Return this maximum sum.
 *
 * Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 * Example 2:
 *
 * Example 1 Diagram
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 * Example 3:
 *
 *
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 */

public class TwoBestNonOverlappingIntevals {

    int[] maxs;

    public int maxTwoEvents(int[][] events) {
        int len = events.length;
        maxs = new int[len];
        Arrays.sort(events, (o1, o2) -> {
            if (o1[1] != o2[1]) return Integer.compare(o1[1], o2[1]);
            return Integer.compare(o1[2], o2[2]);
        });

        for (int i = 0; i < len; i++) {
            if (i == 0) maxs[i] = events[i][2];
            else maxs[i] = Math.max(maxs[i - 1], events[i][2]);

        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            int[] event = events[i];
            int best = search(event[0], events, i - 1);
            if (best != -1) {
                ans = Math.max(ans, event[2] + best);
            }
            ans = Math.max(ans, event[2]);
        }
        return ans;
    }

    int search(int target, int[][] events, int end) {
        if (end < 0) return -1;
        int start = 0;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            if (events[mid][1] < target) {
                start = mid;
            } else end = mid - 1;
        }
        //System.out.println(target + " " + start  + " " + maxs[start]);
        return (events[start][1] < target) ? maxs[start] : -1;
    }
}
