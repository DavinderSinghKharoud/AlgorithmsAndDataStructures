package algorithms.LeetCode;

import java.util.*;

public class MinCostToConnectAllPoints {

    public static int minCostConnectPoints(int[][] points) {

        int res = 0;
        List<Node> lst = new ArrayList<>();

        for (int[] point : points) {
            lst.add(new Node(point));
        }
        List<Edge> edges = new ArrayList<>();
        for (int index = 0; index < points.length; index++) {
            int[] src = points[index];
            for (int index2 = index + 1; index2 < points.length; index2++) {
                int[] des = points[index2];

                int weight = Math.abs(src[0] - des[0]) + Math.abs(src[1] - des[1]);
                edges.add(new Edge(index, index2, weight));
            }
        }

        edges.sort(Comparator.comparingInt(o -> o.weight));

        int numberOfEdges = 0;
        int index = 0;
        while (numberOfEdges < points.length - 1) { //V vertices should have V - 1 edges to crease MST

            Edge curr = edges.get(index++);

            Node src = lst.get(curr.srcIndex);
            Node des = lst.get(curr.desIndex);

            Node parent1 = findParent(src);
            Node parent2 = findParent(des);

            if (parent1 == parent2) continue;

            //Unite them
            if (parent1.parent == parent1) {
                parent1.parent = parent2;
            } else {
                parent2.parent = parent1;
            }


            res += curr.weight;
            numberOfEdges++;

        }

        return res;

    }

    static Node findParent(Node node) {
        Node parent = node.parent;

        if (parent == node) return node;
        node.parent = findParent(parent);
        return node.parent;
    }

    static class Node {
        Node parent;
        int[] coordinate;

        public Node(int[] coordinate) {
            parent = this;
            this.coordinate = coordinate;
        }
    }

    static class Edge {
        int srcIndex;
        int desIndex;
        int weight;

        public Edge(int src, int des, int weight) {
            this.srcIndex = src;
            this.desIndex = des;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {

        System.out.println(minCostConnectPoints2(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    //Time Complexity O( E Log(v) ) and Space Complexity O( n square )
    public static int minCostConnectPoints2(int[][] points) {

        int len = points.length;

        int[][] graph = new int[len][len];

        for (int src = 0; src < len; src++) {
            int[] srcPoints = points[src];
            for (int des = src + 1; des < len; des++) {
                int[] desPoints = points[des];
                int weight = Math.abs(srcPoints[0] - desPoints[0]) + Math.abs(srcPoints[1] - desPoints[1]);

                graph[src][des] = graph[des][src] = weight;
            }
        }

        //Prims Algorithm
        boolean[] visited = new boolean[len];
        int[] costs = new int[len];
        Arrays.fill(costs, Integer.MAX_VALUE);

        //int[] --> [ src, des, weight ]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        pq.add(new int[]{0, 0});
        visited[0] = true;
        costs[0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            visited[curr[0]] = true;


            for (int neighbour = 0; neighbour < len; neighbour++) {

                if (!visited[neighbour] && graph[curr[0]][neighbour] != 0 && graph[curr[0]][neighbour] < costs[neighbour]) {
                    costs[neighbour] = graph[curr[0]][neighbour];
                    pq.add(new int[]{neighbour, costs[neighbour]});
                }
            }

        }

        int res = 0;
        for (int cost : costs) {
            res += cost;
        }
        return res;
    }

    int len = 0;

    //Referenced Code: Time complexity and Space Complexity O( V square )
    public int minCostConnectPoints3(int[][] points) {
        len = points.length;
        int[][] graph = new int[len][len];

        // Create a graph based on all possible distances
        for (int i = 0; i <= len; i++) {
            for (int j = i + 1; j < len; j++) {
                int d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                graph[i][j] = graph[j][i] = d;
            }
        }

        // Prims Algo
        int[] parent = new int[len];
        int[] cost = new int[len];
        boolean[] visited = new boolean[len];

        Arrays.fill(cost, Integer.MAX_VALUE);

        //Step 1: Always include first 1st vertex in MST.
        parent[0] = -1;
        cost[0] = 0;

        for (int i = 0; i < len - 1; i++) {
            int u = minIndex(visited, cost);    // Step 2
            visited[u] = true;

            //Step 3:
            for (int v = 0; v < len; v++) {

                //Step 4:
                if (graph[u][v] != 0 && visited[v] == false && graph[u][v] < cost[v]) {
                    cost[v] = graph[u][v];
                    parent[v] = u;
                }
            }
        }
        int finalCost = 0;

        for (int i : cost) {   // Traverse the cost array and add values to the minimum cost.
            finalCost += i;
        }
        return finalCost;
    }

    private int minIndex(boolean[] visited, int[] cost) {  // find the vertex with minimum key value
        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < len; i++) {
            if (visited[i] == false && minCost > cost[i]) {
                minCost = cost[i];
                minIndex = i;
            }
        }
        return minIndex;

    }
}
