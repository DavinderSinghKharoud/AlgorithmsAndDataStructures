package LeetCode.WeeklyContest274;

import java.util.*;

/**
 * CheckAllAsAppearsBs company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.
 *
 * The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.
 *
 * Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: favorite = [2,2,1,2]
 * Output: 3
 * Explanation:
 * The above figure shows how the company can invite employees 0, 1, and 2, and seat them at the round table.
 * All employees cannot be invited because employee 2 cannot sit beside employees 0, 1, and 3, simultaneously.
 * Note that the company can also invite employees 1, 2, and 3, and give them their desired seats.
 * The maximum number of employees that can be invited to the meeting is 3.
 * Example 2:
 *
 * Input: favorite = [1,2,0]
 * Output: 3
 * Explanation:
 * Each employee is the favorite person of at least one other employee, and the only way the company can invite them is if they invite every employee.
 * The seating arrangement will be the same as that in the figure given in example 1:
 * - Employee 0 will sit between employees 2 and 1.
 * - Employee 1 will sit between employees 0 and 2.
 * - Employee 2 will sit between employees 1 and 0.
 * The maximum number of employees that can be invited to the meeting is 3.
 * Example 3:
 *
 *
 * Input: favorite = [3,0,1,4,1]
 * Output: 4
 * Explanation:
 * The above figure shows how the company will invite employees 0, 1, 3, and 4, and seat them at the round table.
 * Employee 2 cannot be invited because the two spots next to their favorite employee 1 are taken.
 * So the company leaves them out of the meeting.
 * The maximum number of employees that can be invited to the meeting is 4.
 *
 *
 * Constraints:
 *
 * n == favorite.length
 * 2 <= n <= 105
 * 0 <= favorite[i] <= n - 1
 * favorite[i] != i
 */
public class EmployeesMeeting {
    public static void main(String[] args) {
        EmployeesMeeting o = new EmployeesMeeting();
        System.out.println(o.maximumInvitations(new int[]{ 1,0,3,2,5,6,7,4,9,8,11,10,11,12,10}));
    }

    int[] count;
    int len;
    int[] favorite;
    ArrayDeque<Integer>[] graph;

    public int maximumInvitations(int[] favorite) {
        this.favorite = favorite;
        len = favorite.length;
        count = new int[len];
        Arrays.fill(count, -1);

        // For case 1 where we don't have cycle which has only two nodes
        for (int i = 0; i < len; i++) {
            if (count[i] == -1) {
                dfs(i);
            }
        }

        int ans = Arrays.stream(count).max().getAsInt();

        graph = new ArrayDeque[len];
        for (int i = 0; i < len; i++) {
            if (graph[favorite[i]] == null)
                graph[favorite[i]] = new ArrayDeque<>();
            graph[favorite[i]].add(i);
        }

        Arrays.fill(count, -1);

        int sit = 0;
        for (int i = 0; i < len; i++) {
            if (count[i] == -1 && favorite[favorite[i]] == i) {
                count[i] = count[favorite[i]] = 0;
                int first = 0, second = 0;
                if (graph[i] != null) {
                    for (int child : graph[i]) {
                        if (child == favorite[i])
                            continue;
                        first = Math.max(first, dfs2(child));
                    }
                }
                if (graph[favorite[i]] != null) {
                    for (int child : graph[favorite[i]]) {
                        if (i == child)
                            continue;
                        second = Math.max(second, dfs2(child));
                    }
                }
                sit += first + second + 2;
            }
        }
        return Math.max(ans, sit);
    }

    int dfs2(int node) {
        if (count[node] != -1)
            return count[node];
        int max = 0;
        if (graph[node] != null) {
            for (int child : graph[node]) {
                max = Math.max(max, dfs2(child));
            }
        }
        return count[node] = 1 + max;
    }

    int[] dfs(int node) {
        if (count[node] == 0) {
            return new int[]{1, node};
        }
        if (count[node] != -1)
            return null;
        count[node] = 0;
        int[] next = dfs(favorite[node]);
        if (next != null) {
            if (next[1] == node) {
                count[node] = next[0];
                return null;
            } else {
                return new int[]{next[0] + 1, next[1]};
            }
        }
        count[node] = 1;
        return null;
    }
}
