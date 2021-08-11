package LeetCode.Biweekly53;
import java.util.*;

/*
You are given two integer arrays nums1 and nums2 of length n.

The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).

For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
Rearrange the elements of nums2 such that the resulting XOR sum is minimized.

Return the XOR sum after the rearrangement.



Example 1:

Input: nums1 = [1,2], nums2 = [2,3]
Output: 2
Explanation: Rearrange nums2 so that it becomes [3,2].
The XOR sum is (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2.
Example 2:

Input: nums1 = [1,0,3], nums2 = [5,3,4]
Output: 8
Explanation: Rearrange nums2 so that it becomes [5,4,3].
The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
 */
public class MinimumXORSums {
    public static void main(String[] args) {
        MinimumXORSums o = new MinimumXORSums();
        // System.out.println(o.minimumXORSum2(new int[] { 1, 2 }, new int[] { 2, 3 }));
        System.out.println(o.minimumXORSum3(new int[]{1, 0, 3},
                new int[]{5, 3, 4}));
    }

    /***********************************************************************************************************/

    int[][] dp;

    public int minimumXORSum(int[] nums1, int[] nums2) {
        int len = nums1.length;
        dp = new int[14][1 << len + 1];
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        return dfs(nums1, nums2, 0, 0);
    }

    int dfs(int[] nums1, int[] nums2, int index, int mask) {
        if (index == nums2.length)
            return 0;
        if (dp[index][mask] == Integer.MAX_VALUE) {
            // Try every possiblity
            for (int i = 0; i < nums1.length; i++) {
                if ((mask & (1 << i)) == 0) {
                    // Chosse this
                    dp[index][mask] = Math.min(dp[index][mask],
                            (nums1[index] ^ nums2[i]) + dfs(nums1, nums2, index + 1, (mask | (1 << i))));
                }
            }
        }
        return dp[index][mask];
    }

    /***********************************************************************************************************/
    public int minimumXORSum3(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int source = 2 * len, sink = source + 1;
        MinimumCostFlow minCostFlow = new MinimumCostFlow(2 * len + 2);

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                minCostFlow.addEdge(i, j + len, 1, nums1[i] ^ nums2[j]);
            }
        }

        for (int i = 0; i < len; i++) {
            minCostFlow.addEdge(source, i, 1, 0);
        }
        for (int i = 0; i < len; i++) {
            minCostFlow.addEdge(i + len, sink, 1, 0);
        }

        return minCostFlow.minCostFlow(Integer.MAX_VALUE, source, sink);
    }

    public class MinimumCostFlow {

        int n;
        ArrayDeque<Integer>[] adj;
        int[][] cost, capacity;
        int[] dis, parent;

        public MinimumCostFlow(int n) {
            this.n = n;
            adj = new ArrayDeque[n];
            Arrays.setAll(adj, o -> new ArrayDeque<>());
            cost = new int[n][n];
            capacity = new int[n][n];
            dis = new int[n];
            parent = new int[n];
        }

        int minCostFlow(int k, int source, int target) {

            int flow = 0, cost = 0;
            while (flow < k) {
                // Reset the distance and parent array
                Arrays.fill(dis, Integer.MAX_VALUE);
                Arrays.fill(parent, -1);

                // Find the shortest path
                shortestPath(n, source);

                // If we cannot find any more path to target node
                if (dis[target] == Integer.MAX_VALUE) {
                    break;
                }

                // Find the max flow on that path
                int f = k - flow;
                int curr = target;
                while (curr != source) {
                    // Find the min capacity
                    f = Math.min(f, capacity[parent[curr]][curr]);
                    curr = parent[curr];
                }

                // Apply flow
                flow += f;
                cost += f * dis[target];
                curr = target;

                while (curr != source) {
                    capacity[parent[curr]][curr] -= f;
                    capacity[curr][parent[curr]] += f;
                    curr = parent[curr];
                }
            }

            if (k == Integer.MAX_VALUE || flow < k) {
                return cost;
            }
            return -1;
        }

        void shortestPath(int n, int source) {
            dis[source] = 0;
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(source);
            boolean[] inQueue = new boolean[n];
            inQueue[source] = true;

            while (!queue.isEmpty()) {
                int u = queue.poll();
                inQueue[u] = false;
                for (int v : adj[u]) {
                    if (capacity[u][v] > 0 && dis[v] > dis[u] + cost[u][v]) {
                        dis[v] = dis[u] + cost[u][v];
                        parent[v] = u;
                        if (!inQueue[v]) {
                            inQueue[v] = true;
                            queue.add(v);
                        }
                    }
                }
            }
        }

        void addEdge(int from, int to, int capacity, int cost) {
            adj[from].add(to);
            adj[to].add(from);
            this.cost[from][to] = cost;
            this.cost[to][from] = -cost;
            this.capacity[from][to] = capacity;
        }

        void reset() {
            adj = new ArrayDeque[n];
            Arrays.setAll(adj, o -> new ArrayDeque<>());
            cost = new int[n][n];
            capacity = new int[n][n];
        }
    }

    /***********************************************************************************************************/
    public int minimumXORSum2(int[] nums1, int[] nums2) {
        int[][] arr = new int[nums1.length][nums2.length];
        int len = nums1.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                arr[i][j] = nums1[i] ^ nums2[j];
            }
        }

        Hungarian hungarian = new Hungarian(arr);
        int[][] ans = hungarian.findOptimalAssignment();
        int res = 0;
        for (int[] curr : ans) {
            res += nums1[curr[0]] ^ nums2[curr[1]];
        }
        return res;
    }

    /**
     * An implemetation of the Kuhn–Munkres assignment algorithm of the year 1957.
     * https://en.wikipedia.org/wiki/Hungarian_algorithm
     *
     * @author https://github.com/aalmi | march 2014
     * @version 1.0
     */
    class Hungarian {

        int[][] matrix; // initial matrix (cost matrix)

        // markers in the matrix
        int[] squareInRow, squareInCol, rowIsCovered, colIsCovered, staredZeroesInRow;

        public Hungarian(int[][] matrix) {
            if (matrix.length != matrix[0].length) {
                try {
                    throw new IllegalAccessException("The matrix is not square!");
                } catch (IllegalAccessException ex) {
                    System.err.println(ex);
                    System.exit(1);
                }
            }

            this.matrix = matrix;
            squareInRow = new int[matrix.length]; // squareInRow & squareInCol indicate the position
            squareInCol = new int[matrix[0].length]; // of the marked zeroes

            rowIsCovered = new int[matrix.length]; // indicates whether a row is covered
            colIsCovered = new int[matrix[0].length]; // indicates whether a column is covered
            staredZeroesInRow = new int[matrix.length]; // storage for the 0*
            Arrays.fill(staredZeroesInRow, -1);
            Arrays.fill(squareInRow, -1);
            Arrays.fill(squareInCol, -1);
        }

        /**
         * find an optimal assignment
         *
         * @return optimal assignment
         */
        public int[][] findOptimalAssignment() {
            step1(); // reduce matrix
            step2(); // mark independent zeroes
            step3(); // cover columns which contain a marked zero

            while (!allColumnsAreCovered()) {
                int[] mainZero = step4();
                while (mainZero == null) { // while no zero found in step4
                    step7();
                    mainZero = step4();
                }
                if (squareInRow[mainZero[0]] == -1) {
                    // there is no square mark in the mainZero line
                    step6(mainZero);
                    step3(); // cover columns which contain a marked zero
                } else {
                    // there is square mark in the mainZero line
                    // step 5
                    rowIsCovered[mainZero[0]] = 1; // cover row of mainZero
                    colIsCovered[squareInRow[mainZero[0]]] = 0; // uncover column of mainZero
                    step7();
                }
            }

            int[][] optimalAssignment = new int[matrix.length][];
            for (int i = 0; i < squareInCol.length; i++) {
                optimalAssignment[i] = new int[]{squareInCol[i], i};
            }
            return optimalAssignment;
        }

        /**
         * Check if all columns are covered. If that's the case then the      * optimal
         * solution is found
         *
         * @return true or false
         */
        private boolean allColumnsAreCovered() {
            for (int i : colIsCovered) {
                if (i == 0) {
                    return false;
                }
            }
            return true;
        }

        /**
         * Step 1: Reduce the matrix so that in each row and column at least one zero
         * exists: 1. subtract each row minima from each element of the row 2. subtract
         * each column minima from each element of the column
         */
        private void step1() {
            // rows
            for (int i = 0; i < matrix.length; i++) {
                // find the min value of the current row
                int currentRowMin = Integer.MAX_VALUE;
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] < currentRowMin) {
                        currentRowMin = matrix[i][j];
                    }
                }
                // subtract min value from each element of the current row
                for (int k = 0; k < matrix[i].length; k++) {
                    matrix[i][k] -= currentRowMin;
                }
            }

            // cols
            for (int i = 0; i < matrix[0].length; i++) {
                // find the min value of the current column
                int currentColMin = Integer.MAX_VALUE;
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[j][i] < currentColMin) {
                        currentColMin = matrix[j][i];
                    }
                }
                // subtract min value from each element of the current column
                for (int k = 0; k < matrix.length; k++) {
                    matrix[k][i] -= currentColMin;
                }
            }
        }

        /**
         * Step 2: mark each 0 with a "square", if there are no other marked zeroes in
         * the same row or column
         */
        private void step2() {
            int[] rowHasSquare = new int[matrix.length];
            int[] colHasSquare = new int[matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    // mark if current value == 0 & there are no other marked zeroes in the same row
                    // or column
                    if (matrix[i][j] == 0 && rowHasSquare[i] == 0 && colHasSquare[j] == 0) {
                        rowHasSquare[i] = 1;
                        colHasSquare[j] = 1;
                        squareInRow[i] = j; // save the row-position of the zero
                        squareInCol[j] = i; // save the column-position of the zero
                        continue; // jump to next row
                    }
                }
            }
        }

        /**
         * Step 3: Cover all columns which are marked with a "square"
         */
        private void step3() {
            for (int i = 0; i < squareInCol.length; i++) {
                colIsCovered[i] = squareInCol[i] != -1 ? 1 : 0;
            }
        }

        /**
         * Step 7: 1. Find the smallest uncovered value in the matrix. 2. Subtract it
         * from all uncovered values 3. Add it to all twice-covered values
         */
        private void step7() {
            // Find the smallest uncovered value in the matrix
            int minUncoveredValue = Integer.MAX_VALUE;
            for (int i = 0; i < matrix.length; i++) {
                if (rowIsCovered[i] == 1) {
                    continue;
                }
                for (int j = 0; j < matrix[0].length; j++) {
                    if (colIsCovered[j] == 0 && matrix[i][j] < minUncoveredValue) {
                        minUncoveredValue = matrix[i][j];
                    }
                }
            }

            if (minUncoveredValue > 0) {
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (rowIsCovered[i] == 1 && colIsCovered[j] == 1) {
                            // Add min to all twice-covered values
                            matrix[i][j] += minUncoveredValue;
                        } else if (rowIsCovered[i] == 0 && colIsCovered[j] == 0) {
                            // Subtract min from all uncovered values
                            matrix[i][j] -= minUncoveredValue;
                        }
                    }
                }
            }
        }

        /**
         * Step 4: Find zero value Z_0 and mark it as "0*".
         *
         * @return position of Z_0 in the matrix
         */
        private int[] step4() {
            for (int i = 0; i < matrix.length; i++) {
                if (rowIsCovered[i] == 0) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        if (matrix[i][j] == 0 && colIsCovered[j] == 0) {
                            staredZeroesInRow[i] = j; // mark as 0*
                            return new int[]{i, j};
                        }
                    }
                }
            }
            return null;
        }

        /**
         * Step 6: Create a chain K of alternating "squares" and "0*"
         *
         * @param mainZero => Z_0 of Step 4
         */
        private void step6(int[] mainZero) {
            int i = mainZero[0];
            int j = mainZero[1];

            Set<int[]> K = new LinkedHashSet<>();
            // (a)
            // add Z_0 to K
            K.add(mainZero);
            boolean found = false;
            do {
                // (b)
                // add Z_1 to K if
                // there is a zero Z_1 which is marked with a "square " in the column of Z_0
                if (squareInCol[j] != -1) {
                    K.add(new int[]{squareInCol[j], j});
                    found = true;
                } else {
                    found = false;
                }

                // if no zero element Z_1 marked with "square" exists in the column of Z_0, then
                // cancel the loop
                if (!found) {
                    break;
                }

                // (c)
                // replace Z_0 with the 0* in the row of Z_1
                i = squareInCol[j];
                j = staredZeroesInRow[i];
                // add the new Z_0 to K
                if (j != -1) {
                    K.add(new int[]{i, j});
                    found = true;
                } else {
                    found = false;
                }

            } while (found); // (d) as long as no new "square" marks are found

            // (e)
            for (int[] zero : K) {
                // remove all "square" marks in K
                if (squareInCol[zero[1]] == zero[0]) {
                    squareInCol[zero[1]] = -1;
                    squareInRow[zero[0]] = -1;
                }
                // replace the 0* marks in K with "square" marks
                if (staredZeroesInRow[zero[0]] == zero[1]) {
                    squareInRow[zero[0]] = zero[1];
                    squareInCol[zero[1]] = zero[0];
                }
            }

            // (f)
            // remove all marks
            Arrays.fill(staredZeroesInRow, -1);
            Arrays.fill(rowIsCovered, 0);
            Arrays.fill(colIsCovered, 0);
        }
    }

}
