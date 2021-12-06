package Preparation;

public class MaxValueBoundedArray {

    public static void main(String[] args) {
        System.out.println(new MaxValueBoundedArray().maxValue(4, 2, 6));
    }

    public int maxValue2(int n, int index, int maxSum) {
        maxSum -= n;
        int left = 0, right = maxSum, mid;
        while (left < right) {
            mid = (left + right + 1) / 2;
            if (test(n, index, mid) <= maxSum)
                left = mid;
            else
                right = mid - 1;
        }
        return left + 1;
    }

    /**
     * Find the arithmetic sum between a -- b --> { a + b) * (a - b + 1)/ 2;}
     */
    private long test(int n, int index, int a) {
        int b = Math.max(a - index, 0);
        long res = (long) (a + b) * (a - b + 1) / 2;
        b = Math.max(a - ((n - 1) - index), 0);
        res += (long) (a + b) * (a - b + 1) / 2;
        return res - a;
    }

    public int maxValue(int n, int index, int maxSum) {
        int start = 0, end = maxSum;
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (isPoss(mid, index, n, maxSum)) {
                start = mid;
            } else end = mid - 1;
        }

        return start;
    }

    boolean isPoss(int val, int index, int n, int maxSum) {
        int currLen = 0;
        int decLen = val - 1; // 5
        long sum;
        if (index < decLen) {
            //get left sum
            sum = getASum(decLen) - getASum(decLen - index);
        } else {
            sum = getASum(decLen);
            if (decLen < index) sum += (index - decLen);
        }

        if (sum > maxSum) return false;
        System.out.println(sum);
        //Get the right sum
        int rightLen = n - index - 1;
        if (rightLen < decLen) {
            sum += getASum(decLen) - getASum(decLen - rightLen);
        } else {
            sum += getASum(decLen);
            if (decLen < rightLen) sum += (rightLen - decLen);
        }

        System.out.println(sum + " " + val);
        return sum + val <= maxSum;
    }

    long getASum(long n) {
        return (n * (n + 1)) / 2;
    }
}
