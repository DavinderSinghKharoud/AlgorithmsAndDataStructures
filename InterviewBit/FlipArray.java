/*
iven an array of positive elements, you have to flip the sign of some of its elements such that the resultant sum of the elements of array should be minimum non-negative(as close to zero as possible). Return the minimum no. of elements whose sign needs to be flipped such that the resultant sum is minimum non-negative.

Constraints:

 1 <= n <= 100
Sum of all the elements will not exceed 10,000.

Example:

FindGreatestCommonDivisor = [15, 10, 6]
ans = 1 (Here, we will flip the sign of 15 and the resultant sum will be 1 )

FindGreatestCommonDivisor = [14, 10, 4]
ans = 1 (Here, we will flip the sign of 14 and the resultant sum will be 0)
 */


import java.util.*;

public class FlipArray {

    public static int solve(final List<Integer> lst) {

        int len = lst.size();
        if (len == 0) return 0;
        int total = 0;

        for (int num : lst) {
            total += num;
        }

        //we need to calculate how many minimum numbers we need to flip up to half like
        // for 32 --> 16(half)  then 16 - 16 --> 0 which is minimum value possible
        int[] dp = new int[total / 2 + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int num = 0; num < lst.size(); num++) {
            for (int sum = total/2; sum >= 1; sum--) {

                if (sum - lst.get(num) >= 0 && dp[sum - lst.get(num)] != Integer.MAX_VALUE) {
                    dp[sum] = Math.min(dp[sum], dp[sum - lst.get(num)] + 1);
                }
            }
        }

        for (int sum = total / 2; sum >= 0; sum--) {
            if (dp[sum] != Integer.MAX_VALUE) {
                return dp[sum];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        lst.add(8);
        lst.add(4);
        lst.add(5);
        lst.add(7);
        lst.add(6);
        lst.add(2);
        System.out.println(solve(lst));
    }

    public static int solve2(final List<Integer> A) {
        int a[] = new int[A.size()];
        int s = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = A.get(i);
            s += a[i];
        }
        // System.out.println(s/2+1);
        int dp[] = new int[s / 2 + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = s / 2; j >= 0; j--) {
                if (j - a[i] >= 0 && dp[j - a[i]] != Integer.MAX_VALUE) {
                    // System.out.println(a[i] + " " + j);
                    dp[j] = Math.min(dp[j], dp[j - a[i]] + 1);
                }
            }
        }

        for (int i = dp.length - 1; i >= 0; i--) {
            // System.out.println(dp[i]);
            if (dp[i] != Integer.MAX_VALUE) {
                return dp[i];
            }
        }

        return 0;
    }

}

