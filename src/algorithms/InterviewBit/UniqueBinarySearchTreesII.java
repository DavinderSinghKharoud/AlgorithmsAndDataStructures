package algorithms.InterviewBit;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer A, how many structurally unique BST’s (binary search trees) exist that can store values 1…A?
 *
 * Input Format:
 *
 * The first and the only argument of input contains the integer, A.
 * Output Format:
 *
 * Return an integer, representing the answer asked in problem statement.
 * Constraints:
 *
 * 1 <= A <= 18
 * Example:
 *
 * Input 1:
 *     A = 3
 *
 * Output 1:
 *     5
 *
 * Explanation 1:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 */
public class UniqueBinarySearchTreesII {

    public static int numTrees(int a) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 1);
        map.put(2, 2);
        //map.put(3, 5);

        return helper( a, map );
    }

    private static int helper(int a, Map<Integer, Integer> map) {

        if( map.containsKey(a) ){
            return map.get(a);
        }
        int total = 0;
        for(int pointer = 1; pointer <= a; pointer++ ){
            int left = pointer - 1;
            int right = a - pointer;
            total += helper(left, map) * helper(right, map);
        }

        map.put(a, total);
        return total;
    }

    public static void main(String[] args) {
        System.out.println( numTrees(4) );
    }
}
