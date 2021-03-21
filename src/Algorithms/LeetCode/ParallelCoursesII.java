package Algorithms.LeetCode;

import java.util.*;

/**
 * Given the integer n representing the number of courses at some university labeled from 1 to n, and the array dependencies where dependencies[i] = [xi, yi] represents a prerequisite relationship, that is, the course xi must be taken before the course yi. Also, you are given the integer k.
 *
 * In one semester you can take at most k courses as long as you have taken all the prerequisites for the courses you are taking.
 *
 * Return the minimum number of semesters to take all courses. It is guaranteed that you can take all courses in some way.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
 * Output: 3
 * Explanation: The figure above represents the given graph. In this case we can take courses 2 and 3 in the first semester, then take course 1 in the second semester and finally take course 4 in the third semester.
 * Example 2:
 *
 *
 *
 * Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
 * Output: 4
 * Explanation: The figure above represents the given graph. In this case one optimal way to take all courses is: take courses 2 and 3 in the first semester and take course 4 in the second semester, then take course 1 in the third semester and finally take course 5 in the fourth semester.
 * Example 3:
 *
 * Input: n = 11, dependencies = [], k = 2
 * Output: 6
 */
public class ParallelCoursesII {
    public static void main(String[] args) {
        // System.out.println( minNumberOfSemesters(5, new
        // int[][]{{2,1},{3,1},{4,1},{1,5}}, 2));

        int[][] arr = new int[26][];
        String test = "[[12,8],[2,4],[3,7],[6,8],[11,8],[9,4],[9,7],[12,4],[11,4],[6,4],[1,4],[10,7],[10,4],[1,7],[1,8],[2,7],[8,4],[10,8],[12,7],[5,4],[3,4],[11,7],[7,4],[13,4],[9,8],[13,8]]";
        test = test.substring(1, test.length() - 1);
        test = test.replaceAll("\\[", "");
        test = test.replaceAll("]", "");

        int i = 0;
        int[] curr = new int[2];
        int a = -1, b = -1;
        String[] ac = test.split(",");
        for (String c : ac) {
            int num = Integer.parseInt(c);
            if (a == -1) {
                a = num;
            } else {
                b = num;
                curr[0] = a;
                curr[1] = b;
                a = -1;
                b = -1;
                arr[i++] = curr;
                curr = new int[2];
            }
        }

        ParallelCoursesII parallelCoursesII
                = new ParallelCoursesII();

       // System.out.println(parallelCoursesII.minNumberOfSemesters2(13, arr, 9));
       System.out.println(parallelCoursesII.minNumberOfSemesters2(5, new int[][]{ {2,1},{3,1},{4,1},{1,5}}, 2));
    }

    int n, k;
    int[] dp;
    List<List<Integer>> prequisite;

    public int minNumberOfSemesters2(int n, int[][] dependencies, int k) {
        this.n = n;
        this.k = k;
        dp = new int[(1 << (n + 1))];
        prequisite = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
           prequisite.add(new ArrayList<>());
        }
        for (int[] courses : dependencies) {
            prequisite.get(courses[0] - 1).add(courses[1] - 1);
        }

        Arrays.fill(dp
        , -1);
        return make(0);

    }

    public int make(int mask) {
        if (mask == (1 << n) - 1) {
            // Filled all the courses
            return 0;
        }

        if (dp[mask] != -1) {
            // If the combination is already traversed
            return dp[mask];
        }

        // Check the indegree of nodes
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            // Check if course is already fulfilled
            if ((mask & (1 << i)) != 0)
                continue;

            for (int neighbours : prequisite.get(i)) {
                indegree[neighbours]++;
            }
        }

        int nodes = 0; // Nodes that can be traversed
        // Check all the nodes which can be fulfilled
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0 && (mask & (1 << i)) == 0) {
                nodes |= (1 << i);
            }
        }

        int availableCourses = Integer.bitCount(nodes);
        int taken = n + 1; // Max for the question

        if (availableCourses > k) {
            // We need to find all possible combinations that can yield an answer and select
            // the minimum
           for (int i = nodes; i > 0; i = (i - 1) & nodes) {
                int took = Integer.bitCount(i);
                if (took != k) {
                    // It will never be optimal to take less and k courses, and it is also not
                    // possible to take more courses than k
                    continue;
                }
                taken = Math.min(taken, 1 + make(mask | i));
            }
        } else {
            // If it is no of available courses are less than k then we can take all of
            // courses
            taken = 1 + make(mask | nodes);
        }

        return dp[mask] = taken;

    }

    public static int minNumberOfSemesters(int n, int[][] dependencies, int k) {

        List<List<Integer>> tree = new ArrayList<>();
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int[] curr : dependencies) {
            int a = curr[0] - 1;
            int b = curr[1] - 1;
            count[b]++;
            tree.get(a).add(b);
        }

        int res = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] vis = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (count[i] == 0) {
                queue.add(i);
                vis[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int len = queue.size();
            res += getSemester(len, k);
            for (int i = 0; i < len; i++) {
                int curr = queue.poll();
                for (int neighbours : tree.get(curr)) {
                    count[neighbours]--;
                    if (!vis[neighbours]) {
                        queue.add(neighbours);
                        vis[neighbours] = true;
                    }
                }
            }
        }

        return res;

    }

    public static int getSemester(int len, int k) {
        return (len + k - 1) / k;
    }
}
