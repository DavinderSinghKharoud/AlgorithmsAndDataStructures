package algorithms.HackerRank;

public class KindergartenAdventures {

    static int solve(int[] t) {
        int n = t.length;
        int[] adjust = new int[n];
        int current = 0;
        for (int i = 0; i < n; i++) {
            if (i >= t[i]) {
                current++;
            }
            adjust[(i + 1 - t[i] + n) % n]--;
            adjust[(i + 1) % n]++;
        }

        int result = 0;
        int bestVal = current;

        for (int i = 1; i < n; i++) { // von 1!..n-1
            if ((current += adjust[i]) > bestVal) {
                bestVal = current;
                result = i;
            }
        }
        return result + 1;
    }

    public static void main(String[] args) {

        System.out.println( solve(new int[]{2, 0, 1}));
    }
}
