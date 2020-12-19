package Algorithms.HackerRank;

/**
 * Given an array, we define its value to be the value obtained by following these instructions:
 * Write down all pairs of numbers from this array.
 * Compute the product of each pair.
 * Find the sum of all the products.
 * For example, for a given array, for a given array [, , , ],
 * Pairs	(7, 2), (7, -1), (7, 2), (2, -1), (2, 2), (-1, 2)
 * Products of the pairs	14, -7, 14, -2, 4, -2
 * Sum of the products	14 + (-7) + 14 + (-2) + 4 + (-2) =
 * Note that  is listed twice, one for each occurrence of .
 * Given an array of integers, find the largest value of any of its nonempty subarrays.
 * Note: A subarray is a contiguous subsequence of the array.
 * Complete the function largestValue which takes an array and returns an integer denoting the largest value of any of the array's nonempty subarrays.
 * Input Format
 * The first line contains a single integer , denoting the number of integers in array .
 * The second line contains  space-separated integers  denoting the elements of array .
 * Constraints
 * <p>
 * <p>
 * Subtasks
 * for 20% of the points.
 * for 70% of the points.
 * Output Format
 * Print a single line containing a single integer denoting the largest value of any of the array's nonempty subarrays.
 * Sample Input 0
 * 6
 * -3 7 -2 3 5 -2
 * Sample Output 0
 * 41
 * Explanation 0
 * In this case, we have . The largest-valued subarray turns out to be  with value .
 * Sample Input 1
 * 10
 * 5 7 -5 6 3 9 -8 2 -1 10
 * Sample Output 1
 * 200
 */
public class PairSums {

    //Time complexity O( n square ) and Space complexity O(1)
    static long largestValue(int[] arr) {

        int res = Integer.MIN_VALUE;
        int len = arr.length;
        for (int index1 = 0; index1 < len; index1++) {
            int sum = arr[index1];
            int product = 0;
            res = Math.max(res, sum);

            for (int index2 = index1 + 1; index2 < len; index2++) {

                product += sum * arr[index2];
                sum += arr[index2];

                res = Math.max(res, product);
            }
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(largestValue(new int[]{-3, 7, -2, 3, 5, -2}));
        System.out.println(largestValue(new int[]{5, 7, -5, 6, 3, 9, -8, 2, -1, 10}));
    }
}
