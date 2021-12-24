package LeetCode.BiweeklyContest59;

/**
 * You wrote down many positive integers in a string called num. However, you realized that you forgot to add commas to seperate the different numbers. You remember that the list of integers was non-decreasing and that no integer had leading zeros.
 *
 * Return the number of possible lists of integers that you could have written down to get the string num. Since the answer may be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: num = "327"
 * Output: 2
 * Explanation: You could have written down the numbers:
 * 3, 27
 * 327
 * Example 2:
 *
 * Input: num = "094"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be positive.
 * Example 3:
 *
 * Input: num = "0"
 * Output: 0
 * Explanation: No numbers can have leading zeros and all numbers must be positive.
 *
 *
 * Constraints:
 *
 * 1 <= num.length <= 3500
 * num consists of digits '0' through '9'.
 */
public class WaysToSeparateNumbers {
    public static void main(String[] args) {
        WaysToSeparateNumbers o = new WaysToSeparateNumbers();

        System.out.println(o.numberOfCombinations("27"));
    }

    int len;
    int[][] dp;
    int[][] dpsum;
    //Suffix array
    int[][] lcp;
    String nums;
    int mod = (int) 1e9 + 7;

    public int numberOfCombinations(String num) {
        len = num.length();
        dp = new int[len][len];
        dpsum = new int[len][len];
        lcp = new int[len][len];
        nums = num;
        calculateLCM();
        find();
        return dpsum[0][0] % mod;
    }

    private void calculateLCM() {
        for (int i = len - 1; i >= 0; i--) {
            for (int j = len - 1; j >= 0; j--) {
                if (nums.charAt(i) == nums.charAt(j)) {
                    lcp[i][j] = 1 + ((i + 1 < len && j + 1 < len) ? lcp[i + 1][j + 1] : 0);
                }
            }
        }
    }

    void find() {
        for (int i = len - 1; i >= 0; i--) {
            if (nums.charAt(i) == '0')
                continue;

            int sum = 0;
            for (int j = len - 1; j >= i; j--) {
                if (j == len - 1) {
                    // Whole substring
                    dp[i][j] = 1;
                } else {
                    int templen = j - i + 1;
                    int start = j + 1;
                    int end = start + templen - 1;
                    if (end < len && isGreater2(i, start, templen)) {
                        dp[i][j] = dp[start][end];
                    }

                    // Include all the numbers that would be of greater length
                    if (end + 1 < len) {
                        dp[i][j] = (dp[i][j] + dpsum[start][end + 1]) % mod;
                    }
                }

                dpsum[i][j] = sum = (sum + dp[i][j]) % mod;
            }
        }
    }

    int zero = '0';

    boolean isGreater2(int prev, int middle, int len) {
        int common = lcp[prev][middle];
        if (common >= len) {
            return true;
        }
        return nums.charAt(prev + common) < nums.charAt(middle + common);
    }

}
