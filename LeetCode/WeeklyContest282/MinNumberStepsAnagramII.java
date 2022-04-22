package LeetCode.WeeklyContest282;

public class MinNumberStepsAnagramII {
    public static void main(String[] args) {
        MinNumberStepsAnagramII o = new MinNumberStepsAnagramII();
    }

    public int minSteps(String s, String t) {
        int[] sCounts = new int[26];
        int[] tCounts = new int[26];
        int a = 'a';
        for (int i = 0; i < s.length(); i++) {
            sCounts[s.charAt(i) - a]++;
        }
        for (int i = 0; i < t.length(); i++) {
            tCounts[t.charAt(i) - a]++;
        }

        int res = 0;
        for (int i = 0; i < sCounts.length; i++) {
            res += Math.abs(sCounts[i] - tCounts[i]);
        }
        return res;
    }
}
