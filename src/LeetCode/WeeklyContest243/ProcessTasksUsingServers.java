package LeetCode.WeeklyContest243;

import java.util.*;

public class ProcessTasksUsingServers {
    public static void main(String[] args) {
        ProcessTasksUsingServers o = new ProcessTasksUsingServers();
        System.out.println(Arrays.toString(o.assignTasks(new int[]{1, 2, 3
        }, new int[]{5, 4, 3, 1, 2})));
    }

    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<Point> used = new PriorityQueue<>((o1, o2)->{
            if(o1.time != o2.time) return Long.compare(o1.time, o2.time);
            if(o1.weight != o2.weight) return Integer.compare(o1.weight, o2.weight);
            return Integer.compare(o1.index, o2.index);
        });
        PriorityQueue<Point> free = new PriorityQueue<>((o1, o2) -> {
            if (o1.weight == o2.weight)
                return Integer.compare(o1.index, o2.index);
            return Integer.compare(o1.weight, o2.weight);
        });

        for (int i = 0; i < servers.length; i++) {
            free.add(new Point(servers[i], i, 0));
        }

        int[] ans = new int[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            while (!used.isEmpty() && used.peek().time <= i) {
                free.add(used.poll());
            }

            Point curr;
            if (free.isEmpty()) {
                // Take the used server with min time and update the value
                curr = used.poll();
                curr.time += tasks[i];
            } else {
                // Take the free server with min weight
                curr = free.poll();
                curr.time = i + tasks[i];
            }
            ans[i] = curr.index;
            used.add(curr);
        }
        return ans;
    }

    class Point {
        int weight, index;
        long time;

        public Point(int w, int i, long t) {
            weight = w;
            index = i;
            time = t;
        }
    }
}
