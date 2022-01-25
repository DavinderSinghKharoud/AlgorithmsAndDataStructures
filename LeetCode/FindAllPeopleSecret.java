package LeetCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given an integer n indicating there are n people numbered from 0 to n - 1. You are also given a 0-indexed 2D integer array meetings where meetings[i] = [xi, yi, timei] indicates that person xi and person yi have a meeting at timei. FindingThreeDigitEvenNumbers person may attend multiple meetings at the same time. Finally, you are given an integer firstPerson.
 * <p>
 * Person 0 has a secret and initially shares the secret with a person firstPerson at time 0. This secret is then shared every time a meeting takes place with a person that has the secret. More formally, for every meeting, if a person xi has the secret at timei, then they will share the secret with person yi, and vice versa.
 * <p>
 * The secrets are shared instantaneously. That is, a person may receive the secret and share it with people in other meetings within the same time frame.
 * <p>
 * Return a list of all the people that have the secret after all the meetings have taken place. You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 6, meetings = [[1,2,5],[2,3,8],[1,5,10]], firstPerson = 1
 * Output: [0,1,2,3,5]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 5, person 1 shares the secret with person 2.
 * At time 8, person 2 shares the secret with person 3.
 * At time 10, person 1 shares the secret with person 5.​​​​
 * Thus, people 0, 1, 2, 3, and 5 know the secret after all the meetings.
 * Example 2:
 * <p>
 * Input: n = 4, meetings = [[3,1,3],[1,2,2],[0,3,3]], firstPerson = 3
 * Output: [0,1,3]
 * Explanation:
 * At time 0, person 0 shares the secret with person 3.
 * At time 2, neither person 1 nor person 2 know the secret.
 * At time 3, person 3 shares the secret with person 0 and person 1.
 * Thus, people 0, 1, and 3 know the secret after all the meetings.
 * Example 3:
 * <p>
 * Input: n = 5, meetings = [[3,4,2],[1,2,1],[2,3,1]], firstPerson = 1
 * Output: [0,1,2,3,4]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 1, person 1 shares the secret with person 2, and person 2 shares the secret with person 3.
 * Note that person 2 can share the secret at the same time as receiving it.
 * At time 2, person 3 shares the secret with person 4.
 * Thus, people 0, 1, 2, 3, and 4 know the secret after all the meetings.
 * Example 4:
 * <p>
 * Input: n = 6, meetings = [[0,2,1],[1,3,1],[4,5,1]], firstPerson = 1
 * Output: [0,1,2,3]
 * Explanation:
 * At time 0, person 0 shares the secret with person 1.
 * At time 1, person 0 shares the secret with person 2, and person 1 shares the secret with person 3.
 * Thus, people 0, 1, 2, and 3 know the secret after all the meetings.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 105
 * 1 <= meetings.length <= 105
 * meetings[i].length == 3
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * 1 <= timei <= 105
 * 1 <= firstPerson <= n - 1
 */
public class FindAllPeopleSecret {

    public static void main(String[] args) {
        System.out.println(new FindAllPeopleSecret().findAllPeople2(
                5, new int[][]{{4, 3, 1}, {3, 1, 1}, {2, 1, 1}},
                2
        ));
    }

    //O(n * Log(n))
    public List<Integer> findAllPeople2(int n, int[][] meetings, int firstPerson) {
        UnionFind uf = new UnionFind(n);
        uf.setReached(0);
        uf.setReached(firstPerson);
        Arrays.sort(meetings, Comparator.comparingInt(o -> o[2]));

        Set<Integer> currVis = new HashSet<>();
        int len = meetings.length;
        for (int i = 0; i < len; i++) {
            int[] meeting = meetings[i];
            int time = meeting[2];
            currVis.clear();
            for (; i < len && meetings[i][2] == time; i++) {
                uf.unite(meetings[i][0], meetings[i][1]);
                currVis.add(meetings[i][0]);
                currVis.add(meetings[i][1]);
            }
            i--;
            for (int vis : currVis) {
                if (!uf.getReachedState(vis)) uf.reset(vis);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (uf.getReachedState(i)) ans.add(i);
        }
        return ans;
    }

    static class UnionFind {
        int[] parent, size;
        boolean[] canReached;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            canReached = new boolean[n];

            for (int i = 0; i < n; i++) parent[i] = i;
            Arrays.fill(size, 1);
        }

        boolean getReachedState(int a) {
            return canReached[findParent(a)];
        }

        void setReached(int a) {
            canReached[a] = true;
        }

        void reset(int a) {
            size[a] = 1;
            canReached[a] = false;
            parent[a] = a;
        }

        void unite(int a, int b) {
            int parent1 = findParent(a);
            int parent2 = findParent(b);
            if (parent1 == parent2) return;
            //Unite
            if (size[parent1] < size[parent2]) {
                parent1 = parent2 ^ parent1 ^ (parent2 = parent1);
            }
            parent[parent2] = parent1;
            canReached[parent1] |= canReached[parent2];
            size[parent1] += size[parent2];
            size[parent2] = 0;

        }

        int findParent(int n) {
            if (parent[n] == n) return n;
            int realParent = findParent(parent[n]);
            parent[n] = realParent;
            canReached[realParent] |= canReached[n];
            return realParent;
        }
    }

    /**
     * O(Number of nodes * Log(edges)) //Dijkstra algo
     */
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        int[] costs = new int[n];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;
        costs[firstPerson] = 0;

        ArrayDeque<int[]>[] tree = new ArrayDeque[n];
        for (int[] edge : meetings) {
            int a = edge[0], b = edge[1], time = edge[2];
            if (tree[a] == null) tree[a] = new ArrayDeque<>();
            if (tree[b] == null) tree[b] = new ArrayDeque<>();
            tree[a].add(new int[]{b, time});
            tree[b].add(new int[]{a, time});
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));

        pq.add(new Node(0, 0));
        pq.add(new Node(firstPerson, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.time != costs[curr.id]) continue;
            if (tree[curr.id] != null) {
                for (int[] child : tree[curr.id]) {
                    int cost = child[1];
                    //Check whether it can be reached or not
                    if (cost < curr.time) continue;
                    //Check if it can be minimized
                    if (cost < costs[child[0]]) {
                        costs[child[0]] = cost;
                        pq.add(new Node(child[0], cost));
                    }
                }
            }
        }

        return IntStream.range(0, costs.length).
                filter(o -> costs[o] != Integer.MAX_VALUE)
                .boxed()
                .collect(Collectors.toList());
    }

    static class Node {
        int id, time;

        public Node(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
}
