package LeetCode;

/**
 * There are n tasks assigned to you. The task times are represented as an integer array tasks of length n, where the ith task takes tasks[i] hours to finish. CheckAllAsAppearsBs work session is when you work for at most sessionTime consecutive hours and then take a break.
 *
 * You should finish the given tasks in a way that satisfies the following conditions:
 *
 * If you start a task in a work session, you must complete it in the same work session.
 * You can start a new task immediately after finishing the previous one.
 * You may complete the tasks in any order.
 * Given tasks and sessionTime, return the minimum number of work sessions needed to finish all the tasks following the conditions above.
 *
 * The tests are generated such that sessionTime is greater than or equal to the maximum element in tasks[i].
 *
 *
 *
 * Example 1:
 *
 * Input: tasks = [1,2,3], sessionTime = 3
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish the first and the second tasks in 1 + 2 = 3 hours.
 * - Second work session: finish the third task in 3 hours.
 * Example 2:
 *
 * Input: tasks = [3,1,3,1,1], sessionTime = 8
 * Output: 2
 * Explanation: You can finish the tasks in two work sessions.
 * - First work session: finish all the tasks except the last one in 3 + 1 + 3 + 1 = 8 hours.
 * - Second work session: finish the last task in 1 hour.
 * Example 3:
 *
 * Input: tasks = [1,2,3,4,5], sessionTime = 15
 * Output: 1
 * Explanation: You can finish all the tasks in one work session.
 *
 *
 * Constraints:
 *
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 */
public class MinNumberOfWorkSessions {

    public static void main(String[] args) {
        System.out.println(new MinNumberOfWorkSessions().minSessions(
                new int[]{1, 2, 3}, 3
        ));
    }

    Pair<Integer, Integer>[] dp;
    int[] tasks;
    int len, session;
    int maskLen;

    /**
     * O(n * (2 ^ n))
     */
    public int minSessions(int[] tasks, int sessionTime) {
        len = tasks.length;
        maskLen = (1 << len);
        session = sessionTime;
        dp = new Pair[maskLen + 1];
        this.tasks = tasks;
        find(0);

        return dp[0].getKey();
    }

    Pair<Integer, Integer> find(int mask) {
        if (mask == maskLen - 1) return new Pair<>(0, 0);
        if (dp[mask] != null) return dp[mask];
        int ansSessions = len, ansRemain = 0;

        for (int i = 0; i < len; i++) {
            if ((mask & (1 << i)) == 0) {
                Pair<Integer, Integer> next = find((mask | (1 << i)));

                int curr = next.getKey(), remainSessions = next.getValue();
                if (tasks[i] <= remainSessions) {
                    remainSessions -= tasks[i];
                } else {
                    curr++;
                    remainSessions = session - tasks[i];
                }

                if (curr < ansSessions || (curr == ansSessions && remainSessions > ansRemain)) {
                    ansSessions = curr;
                    ansRemain = remainSessions;
                }
            }
        }

        return dp[mask] = new Pair<>(ansSessions, ansRemain);
    }

    static class Pair<T, I extends Number> {
        I first, second;

        public Pair(I first, I second) {
            this.first = first;
            this.second = second;
        }

        public I getKey() {
            return first;
        }

        public I getValue() {
            return second;
        }
    }
}
