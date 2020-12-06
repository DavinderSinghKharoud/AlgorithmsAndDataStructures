package algorithms.HackerRank;

/**
 * One of the most important skills a programmer needs to learn early on is the ability to pose a problem in an abstract way. This skill is important not just for researchers but also in applied fields like software engineering and web development.
 * You are able to solve most of a problem, except for one last subproblem, which you have posed in an abstract way as follows: Given an array consisting of  integers , define
 * <p>
 * For example, for an input array [ 10, -5, 5, 20 ], a subsegment  would be computed as follows:
 * image
 * What is , i.e., the maximum value of  among all subsegments ?
 * Complete the function maximumValue which takes an integer array as input and returns the maximum value of  among all subsegments .
 * Note that:
 * <p>
 * <p>
 * Input Format
 * The first line contains a single integer
 * The second line contains  space-separated integers
 * Constraints
 * <p>
 * <p>
 * Output Format
 * Print a single integer denoting the answer
 * Sample Input 0
 * 4
 * 10 -5 5 20
 * Sample Output 0
 * 50
 * Explanation 0
 * The maximum value occurs at  as shown below.
 * image
 * Sample Input 1
 * 5
 * 7 12 24 6 5
 * Sample Output 1
 * 144
 * Explanation 1
 * The maximum value occurs at .
 */
public class TheStrangeFunction {

    static long maximumValue(int[] arr) {

        long res = Integer.MIN_VALUE;
        int len = arr.length;
        long max, gcd, sum;

        for (int index1 = 0; index1 < len; index1++) {
            max = arr[index1];
            sum = arr[index1];
            gcd = Math.abs(arr[index1]);

            res = Math.max(res, getValue(max, sum, gcd));
            for (int index2 = index1 + 1; index2 < len; index2++) {

                max = Math.max(max, arr[index2]);
                sum += arr[index2];
                gcd = getGcd(gcd, Math.abs(arr[index2]));

                res = Math.max(res, getValue(max, sum, gcd));
            }
        }

        return res;

    }

    static long getValue(long max, long sum, long gcd) {
        return gcd * (sum - max);
    }

    /**
     * The Euclidean Algorithm for finding GCD(A,B) is as follows:
     * If A = 0 then GCD(A,B)=B, since the GCD(0,B)=B, and we can stop.
     * If B = 0 then GCD(A,B)=A, since the GCD(A,0)=A, and we can stop.
     * Write A in quotient remainder form (A = B⋅Q + R)
     * Find GCD(B,R) using the Euclidean Algorithm since GCD(A,B) = GCD(B,R)
     */
    static long getGcd(long num1, long num2) {
        if (num2 > num1) return getAbstractGcd(num2, num1);
        return getAbstractGcd(num1, num2);
    }

    private static long getAbstractGcd(long num1, long num2) {

        if (num2 == 0) return num1;

        long remainder = num1 % num2;

        return getAbstractGcd(num2, remainder);
    }

    public static void main(String[] args) {

        System.out.println(maximumValue(new int[]{10, -5, 5, 20}));
    }
}
