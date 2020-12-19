package Algorithms.HackerRank;

/**
 * You are given a 3-D Matrix in which each block contains 0 initially. The first block is defined by the coordinate (1,1,1) and the last block is defined by the coordinate (N,N,N). There are two types of queries.
 * UPDATE x y z W
 * updates the value of block (x,y,z) to W.
 * QUERY x1 y1 z1 x2 y2 z2
 * calculates the sum of the value of blocks whose x coordinate is between x1 and x2 (inclusive), y coordinate between y1 and y2 (inclusive) and z coordinate between z1 and z2 (inclusive).
 * Input Format
 * The first line contains an integer T, the number of test-cases. T testcases follow.
 * For each test case, the first line will contain two integers N and M separated by a single space.
 * N defines the N * N * N matrix.
 * M defines the number of operations.
 * The next M lines will contain either
 * 1. UPDATE x y z W
 * 2. QUERY  x1 y1 z1 x2 y2 z2
 * Output Format
 * Print the result for each QUERY.
 * Constrains
 * 1 <= T <= 50
 * 1 <= N <= 100
 * 1 <= M <= 1000
 * 1 <= x1 <= x2 <= N
 * 1 <= y1 <= y2 <= N
 * 1 <= z1 <= z2 <= N
 * 1 <= x,y,z <= N
 * -109 <= W <= 109
 * Sample Input
 * 2
 * 4 5
 * UPDATE 2 2 2 4
 * QUERY 1 1 1 3 3 3
 * UPDATE 1 1 1 23
 * QUERY 2 2 2 4 4 4
 * QUERY 1 1 1 3 3 3
 * 2 4
 * UPDATE 2 2 2 1
 * QUERY 1 1 1 1 1 1
 * QUERY 1 1 1 2 2 2
 * QUERY 2 2 2 2 2 2
 * Sample Output
 * 4
 * 4
 * 27
 * 0
 * 1
 * 1
 * Explanation
 * First test case, we are given a cube of 4 * 4 * 4 and 5 queries. Initially all the cells (1,1,1) to (4,4,4) are 0.
 * UPDATE 2 2 2 4 makes the cell (2,2,2) = 4
 * QUERY 1 1 1 3 3 3. As (2,2,2) is updated to 4 and the rest are all 0. The answer to this query is 4.
 * UPDATE 1 1 1 23. updates the cell (1,1,1) to 23. QUERY 2 2 2 4 4 4. Only the cell (1,1,1) and (2,2,2) are non-zero and (1,1,1) is not between (2,2,2) and (4,4,4). So, the answer is 4.
 * QUERY 1 1 1 3 3 3. 2 cells are non-zero and their sum is 23+4 = 27.
 */

import java.util.*;

public class CubeSummation {

    //Time complexity O( n^3 ) and Space complexity O(n*n*n)
    public static void main2(String[] args) {

        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        while (test-- > 0) {
            int len = sc.nextInt();
            int[][][] arr = new int[len][len][len];

            int queries = sc.nextInt();

            while (queries-- > 0) {
                String type = sc.next();

                if (type.equals("UPDATE")) {
                    update(arr, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
                } else {
                    System.out.println(getSum(arr, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1));
                }
            }
        }
    }

    public static void update(int[][][] arr, int x, int y, int z, int val) {
        arr[x][y][z] = val;
    }

    private static long getSum(int[][][] arr, int x1, int y1, int z1, int x2, int y2, int z2) {

        long sum = 0;
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    sum += arr[x][y][z];
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();

        while (test-- > 0) {
            int len = sc.nextInt(); //We do not really need this as we are using MAP
            Map<String, Integer> map = new HashMap<>();

            int queries = sc.nextInt();

            while (queries-- > 0) {
                String type = sc.next();
                StringBuilder sbr;
                if (type.equals("UPDATE")) {
                    sbr = new StringBuilder();
                    sbr.append(sc.nextInt() + " ").append(sc.nextInt() + " ").append(sc.nextInt());
                    map.put(sbr.toString(), sc.nextInt());
                } else {
                    long res = 0;
                    int x1 = sc.nextInt();
                    int y1 = sc.nextInt();
                    int z1 = sc.nextInt();
                    int x2 = sc.nextInt();
                    int y2 = sc.nextInt();
                    int z2 = sc.nextInt();
                    for (String key : map.keySet()) {
                        String[] arr = key.split(" ");
                        int x = Integer.parseInt(arr[0]);
                        int y = Integer.parseInt(arr[1]);
                        int z = Integer.parseInt(arr[2]);

                        if (x >= x1 && x <= x2 && y >= y1 && y <= y2 && z >= z1 && z <= z2) {
                            res += map.get(key);
                        }
                    }

                    System.out.println(res);
                }
            }
        }
    }

}
