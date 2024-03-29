package LeetCode.BiweeklyContest65;

import java.util.*;

/**
 * You have n tasks and m workers. Each task has a strength requirement stored in a 0-indexed integer array tasks, with the ith task requiring tasks[i] strength to complete. The strength of each worker is stored in a 0-indexed integer array workers, with the jth worker having workers[j] strength. Each worker can only be assigned to a single task and must have a strength greater than or equal to the task's strength requirement (i.e., workers[j] >= tasks[i]).
 *
 * Additionally, you have pills magical pills that will increase a worker's strength by strength. You can decide which workers receive the magical pills, however, you may only give each worker at most one magical pill.
 *
 * Given the 0-indexed integer arrays tasks and workers and the integers pills and strength, return the maximum number of tasks that can be completed.
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 2 (0 + 1 >= 1)
 * - Assign worker 1 to task 1 (3 >= 2)
 * - Assign worker 2 to task 0 (3 >= 3)
 * Example 2:
 *
 * Input: tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5
 * Output: 1
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 0.
 * - Assign worker 0 to task 0 (0 + 5 >= 5)
 * Example 3:
 *
 * Input: tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10
 * Output: 2
 * Explanation:
 * We can assign the magical pills and tasks as follows:
 * - Give the magical pill to worker 0 and worker 1.
 * - Assign worker 0 to task 0 (0 + 10 >= 10)
 * - Assign worker 1 to task 1 (10 + 10 >= 15)
 * Example 4:
 *
 * Input: tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5
 * Output: 3
 * Explanation:
 * We can assign the magical pill and tasks as follows:
 * - Give the magical pill to worker 2.
 * - Assign worker 1 to task 0 (6 >= 5)
 * - Assign worker 2 to task 2 (4 + 5 >= 8)
 * - Assign worker 4 to task 3 (6 >= 5)
 *
 *
 * Constraints:
 *
 * n == tasks.length
 * m == workers.length
 * 1 <= n, m <= 5 * 104
 * 0 <= pills <= m
 * 0 <= tasks[i], workers[j], strength <= 109
 */
public class MaximumTasksYouCanAsign {
    public static void main(String[] args) {
        MaximumTasksYouCanAsign o = new MaximumTasksYouCanAsign();
    }

    TreeMap<Integer, Integer> mapWorkers = new TreeMap<>();

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int len = tasks.length;
        int start = 0, end = len;
        Arrays.sort(tasks);
        for (int worker : workers) {
            mapWorkers.put(worker, mapWorkers.getOrDefault(worker, 0) + 1);
        }
        while (start < end) {
            int mid = (end + start + 1) / 2;
            //System.out.println(start + " " + mid + " " + end);
            if (isSatisfied(mid, tasks, workers, pills, strength)) {
                start = mid;
            } else end = mid - 1;
        }
        //System.out.println(start + " "  + end);
        return start;
    }


    boolean isSatisfied(int m, int[] tasks, int[] workers, int pills, int strength) {
        int end = m - 1;
        TreeMap<Integer, Integer> map = (TreeMap) mapWorkers.clone();


        while (!map.isEmpty() && end >= 0) {
            int currTask = tasks[end];
            Integer strongest = map.ceilingKey(currTask);
            if (strongest == null) {
                //try to see if any weakest can do the work with the pill
                if (pills == 0) return false;
                Integer weakest = map.ceilingKey(currTask - strength);
                if (weakest == null) return false;
                int count = map.get(weakest) - 1;
                if (count == 0) map.remove(weakest);
                else map.put(weakest, count);
                pills--;
            } else {
                int count = map.get(strongest) - 1;
                if (count == 0) map.remove(strongest);
                else map.put(strongest, count);
            }
            end--;
        }

        return end < 0;
    }
}
