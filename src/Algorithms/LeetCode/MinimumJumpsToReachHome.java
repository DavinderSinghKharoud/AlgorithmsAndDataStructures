package Algorithms.LeetCode;

import java.util.*;

public class MinimumJumpsToReachHome {


    public static void main(String[] args) {

        System.out.println(minimumJumps(new int[]{162, 118, 178, 152, 167, 100, 40, 74, 199, 186, 26, 73, 200, 127, 30, 124, 193, 84, 184, 36, 103, 149, 153, 9, 54, 154, 133, 95, 45, 198, 79, 157, 64, 122, 59, 71, 48, 177, 82, 35, 14, 176, 16, 108, 111, 6, 168,
                31, 134, 164, 136, 72, 98}, 29, 98, 80));
    }

    static int ans = -1;
    static Map<Integer, Integer> map = new HashMap<>();

    public static int minimumJumps(int[] forbidden, int a, int b, int x) {
        // We can use bfs to find the min steps
        Set<Integer> notAllowed = new HashSet<>();
        for (int num : forbidden) {
            notAllowed.add(num);
        }

        Node node = new Node(0, 0, false);
        Set<Integer> set = new HashSet<>();
        set.add(0);
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (curr.val == x) {
                return curr.steps;
            }
            if(notAllowed.contains(curr.val)) continue;

            // Move backward
            if (curr.val - b >= 0) {
                if (!set.contains(curr.val - b) && !curr.isBack) {
                    set.add(curr.val - b);
                    Node back = new Node(curr.val - b, curr.steps + 1, true);
                    queue.add(back);
                }
            }

            if (curr.val + a <= 4 * 2000) {
                if (!set.contains(curr.val + a) ) {
                    queue.add(new Node(curr.val + a, curr.steps + 1, false));
                    set.add(curr.val + a);
                }
            }

        }
        return -1;
    }

    public static class Node {
        int val, steps;
        boolean isBack = false;

        public Node(int x, int steps, boolean isBack) {
            this.val = x;
            this.steps = steps;
            this.isBack = isBack;
        }
    }

    public static void traverse(int num, int des) {

    }
}
