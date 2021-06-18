package Algorithms.LeetCode.WeeklyContest245;

public class MaximumNumberRemovalCharacters {
    public static void main(String[] args) {
        MaximumNumberRemovalCharacters o = new MaximumNumberRemovalCharacters();

        System.out.println(o.maximumRemovals("abcacb", "ab", new int[]{3, 1, 0}));
        System.out.println(o.maximumRemovals("abcbddddd", "abcd", new int[]{3, 2, 1, 4, 5, 6}));
        System.out.println(o.maximumRemovals("abcab", "abc", new int[]{0, 1, 2, 3, 4}));

    }

    public int maximumRemovals(String s, String p, int[] removable) {

        int start = 0, end = removable.length;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (isPossible(s, p, removable, mid)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    boolean isPossible(String s, String p, int[] removable, int mid) {
        boolean[] isNotPoss = new boolean[s.length()];
        for (int i = 0; i < mid; i++) {
            isNotPoss[removable[i]] = true;
        }

        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!isNotPoss[i]) {
                if (s.charAt(i) == p.charAt(j)) {
                    j++;
                }
                if (j >= p.length())
                    return true;
            }
        }
        return false;
    }
}
