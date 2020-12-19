package Algorithms.LeetCode;

public class MinimumDeletionsToMakeStringBalanced {

    //Time Complexity and Space complexity O(n)
    static int minimumDeletions(String s) {

        int len = s.length();

        if (len == 0) return 0;

        int[] aDp = new int[len + 1]; //Denote the number of deletions for Sub-String[0:index] such that it ends with 'a'
        int[] bDp = new int[len + 1]; //Denote the number of deletions for Sub-String[0:index] such that it ends with 'b'

        int index = 1;
        for (char c : s.toCharArray()) {

            if (c == 'a') {
                aDp[index] = aDp[index - 1];
                bDp[index] = bDp[index - 1] + 1;
            } else {
                //Suppose if array is up to this index and ends with 'b'
                aDp[index] = aDp[index - 1] + 1;
                bDp[index] = Math.min(bDp[index - 1], aDp[index - 1]);
            }
            index++;
        }

        return Math.min(bDp[len], aDp[len]);

    }

    public static void main(String[] args) {

        System.out.println(minimumDeletions3("aababbab"));
    }

    //Time Complexity O(n) and Space Complexity O(1)
    static int minimumDeletions2(String s) {
        int len = s.length();

        if (len == 0) return 0;

        int previousA = 0;
        int previousB = 0;

        for (char c : s.toCharArray()) {

            if (c == 'a') {
                previousB += 1;
            } else {
                //Suppose if array is up to this index and ends with 'b'
                previousB = Math.min(previousA, previousB);
                previousA += 1;
            }
        }

        return Math.min(previousA, previousB);

    }

    //Time Complexity and Space Complexity O(N)
    static int minimumDeletions3(String s) {

        int len = s.length();

        if (len == 0) return 0;

        int[] countA = new int[len];
        countA[len - 1] = s.charAt(len - 1) == 'a' ? 1 : 0;

        for (int index = len - 2; index >= 0; index--) {
            countA[index] = countA[index + 1] + (( s.charAt(index) == 'a')? 1: 0);
        }
        int res = Integer.MAX_VALUE;
        int bCount = 0;

        for (int index = 0; index < len; index++) {
            if (s.charAt(index) == 'b') {
                res = Math.min(res, bCount + countA[index]);
                bCount++;
            }
        }

        res = Math.min(res, bCount);
        return (res == Integer.MAX_VALUE) ? 0 : res;
    }
}
