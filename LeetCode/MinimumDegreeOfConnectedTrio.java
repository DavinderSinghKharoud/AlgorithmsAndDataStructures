package LeetCode;

public class MinimumDegreeOfConnectedTrio {

    public static void main(String[] args) {
        MinimumDegreeOfConnectedTrio obj = new MinimumDegreeOfConnectedTrio();
        System.out.println(
                obj.minTrioDegree(6, new int[][] { { 1, 2 }, { 1, 3 }, { 3, 2 }, { 4, 1 }, { 5, 2 }, { 6, 3 } }));
    }

    public int minTrioDegree(int n, int[][] edges) {
        int[][] tree = new int[n][n];

        int[] counts = new int[n];

        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            tree[a][b] = 1;
            tree[b][a] = 1;

            counts[a]++;
            counts[b]++;
        }

        int res = Integer.MAX_VALUE;

        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            for (int i = 0; i < n; i++) {
                if (tree[a][i] == 1 && tree[b][i] == 1) {
                    res = Math.min(res, counts[a] + counts[b] + counts[i] - 6);
                }
            }
        }
        return (res == Integer.MAX_VALUE) ? -1 : res;

    }
}
