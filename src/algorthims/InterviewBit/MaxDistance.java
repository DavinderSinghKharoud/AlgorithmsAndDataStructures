package algorthims.InterviewBit;

import java.util.*;

/**
 * Problem Description
 * <p>
 * Given an array A of integers, find the maximum of j - i subjected to the constraint of A[i] <= A[j].
 * <p>
 * <p>
 * <p>
 * Input Format
 * First and only argument is an integer array A.
 * <p>
 * <p>
 * <p>
 * Output Format
 * Return an integer denoting the maximum value of j - i;
 * <p>
 * <p>
 * <p>
 * Example Input
 * Input 1:
 * <p>
 * A = [3, 5, 4, 2]
 * <p>
 * <p>
 * Example Output
 * Output 1:
 * <p>
 * 2
 * <p>
 * <p>
 * Example Explanation
 * Explanation 1:
 * <p>
 * Maximum value occurs for pair (3, 4).
 */
public class MaxDistance {

    //O(n square) time complexity
    public static int maximumGap1(final List<Integer> lst) {

        int max = Integer.MIN_VALUE;
        int len = lst.size();

        if (len == 1) {
            return lst.get(0);
        }
        for (int end = 1; end < len; end++) {
            for (int start = 0; start < end; start++) {
                if (lst.get(end) >= lst.get(start)) {
                    max = Math.max(max, end - start);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {

        System.out.println(maximumGap3(Arrays.asList(3, 5, 4, 2)));
    }

    //O(n logn ) time complexity and O(n) space complexity
    public static int maximumGap2(final List<Integer> a) {

        int len = a.size();
        List<helper> lst = new ArrayList<>();
        for (int index = 0; index < len; index++) {
            lst.add(new helper(index, a.get(index)));
        }

        Collections.sort(lst, (o1, o2) -> o1.value - o2.value);

        //to store the right max at each index
        int max = Integer.MIN_VALUE;
        int[] maxIndex = new int[len];
        for (int index = len - 1; index >= 0; index--) {
            max = Math.max(max, lst.get(index).index);
            maxIndex[index] = max;
        }

        max = Integer.MIN_VALUE;

        for (int index = 0; index < len; index++) {
            max = Math.max(max, maxIndex[index] - lst.get(index).index);
        }

        return max;
    }

    static class helper {
        int index;
        int value;

        public helper(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    //O(n) time and space complexity
    public static int maximumGap3(final List<Integer> a) {
        int len = a.size();

        if( len == 1 ){
            return a.get(0);
        }
        //store the left min for each index
        int min = Integer.MAX_VALUE;
        int[] leftMin = new int[len];

        for (int index = 0; index < len; index++) {
            min = Math.min(min, a.get(index));
            leftMin[index] = min;
        }

        //store the right max for each index
        int max = Integer.MIN_VALUE;
        int[] rightMax = new int[len];

        for (int index = len - 1; index >= 0; index--) {
            max = Math.max(max, a.get(index));
            rightMax[index] = max;
        }


        int left = 0, right = 0, maxDiff = -1;
        while (left < len && right < len) {

            if (rightMax[right] >= leftMin[left]) {
                maxDiff = Math.max( maxDiff, right - left );
                right++;
            }else{
                left++;
            }
        }
        return maxDiff == -1? 0: maxDiff;
    }
}
