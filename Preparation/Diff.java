package Preparation;

public class Diff {
    public static void main(String[] args) {

    }

    public int diffPossible(int[] A, int B) {
        //edge case for zero
        if (B == 0) {
            for (int i = 1; i < A.length; i++) {
                if (A[i] - A[i - 1] == 0) return 1;
            }
            return 0;
        }
        int start = 0, end = 1;
        while (end < A.length) {
            int diff = A[end] - A[start];
            if (diff < B) {
                end++;
            } else if (diff > B) {
                start++;
            } else return 1;
        }
        return 0;
    }
}
