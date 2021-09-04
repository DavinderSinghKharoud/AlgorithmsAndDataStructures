package LeetCode.WeeklyContest256;

import java.util.*;

public class MinimumNumberOfWorkSessions {
    public static void main(String[] args) {
        MinimumNumberOfWorkSessions o = new MinimumNumberOfWorkSessions();
        System.out.println(o.minSessions(new int[]{2, 3, 3, 4, 4, 4, 5, 6, 7, 10}, 12));
    }

    int sessionTime;
    int highest = Integer.MIN_VALUE;
    int best = 0;

    public int minSessions(int[] tasks, int sessionTime) {
        this.sessionTime = sessionTime;
        if (sessionTime == 1)
            return tasks.length;
        int count = 0;
        // an array of reverse sorted ints
        tasks = Arrays.stream(tasks).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        while (tasks.length != 0) {
            count++;
            best = 0;
            highest = Integer.MIN_VALUE;
            find(tasks, 0, 0, 0);
            List<Integer> arr = new ArrayList<>();
            for (int i = 0; i < tasks.length; i++) {
                if ((best & (1 << i)) > 0) {
                    // already chosen
                } else {
                    arr.add(tasks[i]);
                }
            }
            tasks = new int[arr.size()];
            for (int i = 0; i < tasks.length; i++) {
                tasks[i] = arr.get(i);
            }
            tasks = Arrays.stream(tasks).boxed()
                    .sorted(Collections.reverseOrder())
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
        return count;
    }

    void find(int[] tasks, int index, int sum, int bit) {
        if (index == tasks.length) {
            if (highest < sum) {
                // new ans
                best = bit;
                highest = sum;
            }
        } else {

            // take it
            if (sum + tasks[index] <= sessionTime) {
                find(tasks, index + 1, sum + tasks[index], (bit | (1 << index)));
            }
            // don't take it
            find(tasks, index + 1, sum, bit);
        }
    }
}
