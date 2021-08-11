package LeetCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a matrix, and a target, return the number of non-empty submatrices that sum to target.
 *
 * A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
 *
 * Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
 * Output: 4
 * Explanation: The four 1x1 submatrices that only contain 0.
 * Example 2:
 *
 * Input: matrix = [[1,-1],[-1,1]], target = 0
 * Output: 5
 * Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.
 */
public class NumberOfSubmatricesThatSumToTarget {
    //O(n ^ 3) time compelexity and O(n) space complexity
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n = matrix.length;
        if( n == 0 ) return 0;
        int m = matrix[0].length;

        int count = 0;

        for (int left = 0; left < m; left++) {
            int[] dp = new int[n];

            for(int right = left; right < m; right++ ){

                for(int index = 0; index < n; index ++ ){
                    dp[index] += matrix[index][right];
                }

                count += find_target_in_array( dp, target );
            }
        }

        return count;
    }

    //O(n) time ans space complexity
    public static int find_target_in_array(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        //because 0 will be the starting point
        map.put(0,1);

        for (int index = 0; index < arr.length; index++) {
            sum += arr[index];

            //if same key exists, it means sum between the boundaries is equal to target
            if( map.containsKey( sum - target) ){
                count += map.get( sum - target );
            }

            map.put( sum, map.getOrDefault(sum, 0) + 1 );
        }
        return count;
    }


}
