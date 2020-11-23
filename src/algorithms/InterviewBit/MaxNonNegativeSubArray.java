package algorithms.InterviewBit;

/**
 * Given an array of integers, A of length N, find out the maximum sum sub-array of non negative numbers from A.
 *
 * The sub-array should be contiguous i.e., a sub-array created by choosing the second and fourth element and skipping the third element is invalid.
 *
 * Maximum sub-array is defined in terms of the sum of the elements in the sub-array.
 *
 * Find and return the required subarray.
 *
 * NOTE:
 *
 * If there is a tie, then compare with segment's length and return segment which has maximum length.
 * If there is still a tie, then return the segment with minimum starting index.
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 * -109 <= A[i] <= 109
 *
 *
 *
 * Input Format
 * The first and the only argument of input contains an integer array A, of length N.
 *
 *
 *
 * Output Format
 * Return an array of integers, that is a subarray of A that satisfies the given conditions.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = [1, 2, 5, -7, 2, 3]
 * Input 2:
 *
 *  A = [10, -1, 2, 3, -4, 100]
 *
 *
 * Example Output
 * Output 1:
 *
 *  [1, 2, 5]
 * Output 2:
 *
 *  [100]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The two sub-arrays are [1, 2, 5] [2, 3].
 *  The answer is [1, 2, 5] as its sum is larger than [2, 3].
 * Explanation 2:
 *
 *  The three sub-arrays are [10], [2, 3], [100].
 *  The answer is [100] as its sum is larger than the other two.
 */

import java.util.ArrayList;

public class MaxNonNegativeSubArray {

    public static ArrayList<Integer> maxset(ArrayList<Integer> a) {

        int len = a.size();
        int index = 0, maxCount = 0, count = 0, start = 0, end = -1;
        int fStart = -1, fEnd = -1;
        long sum = 0, maxSum = 0;
        ArrayList<Integer> res = new ArrayList<>();

        while ( index < len ){
            if( a.get(index) >= 0 ){
                sum += a.get(index);
                count++;
                end++;
            }

            if( sum > maxSum ){
                maxSum = sum;
                fStart = start;
                fEnd = end;
            }

            if( sum == maxSum && count > maxCount ){
                maxCount = count;
                fStart = start;
                fEnd = end;
            }

            if( a.get(index) < 0 ){
                count = 0;
                start = index + 1;
                end = index;
                sum = 0;
            }
            index++;
        }

        if( fStart != -1 && fEnd != -1 ){
            for (int i = fStart; i <= fEnd ; i++) {
                res.add(a.get(i));
            }
        }

        return res;
    }
    public static void main(String[] args) {
        ArrayList<Integer> lst = new ArrayList<>();
//        lst.add(1);
//        lst.add(2);
//        lst.add(5);
//        lst.add(-7);
//        lst.add(2);
//        lst.add(3);
        lst.add(756898537);
        lst.add(-1973594324);
        lst.add(-2038664370);
        lst.add(-184803526);
        lst.add(1424268980);
        System.out.println( maxset( lst ));
    }
}
