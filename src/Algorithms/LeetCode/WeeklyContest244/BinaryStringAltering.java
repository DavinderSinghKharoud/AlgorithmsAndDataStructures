package Algorithms.LeetCode.WeeklyContest244;

public class BinaryStringAltering {
    public static void main(String[] args) {
        BinaryStringAltering o = new BinaryStringAltering();
        System.out.println(o.minFlips("110"));
    }

    public int minFlips(String s) {
        int len = s.length();
        String first = get(2 * len, true);
        String second = get(2 * len, false);
        s += s;

        int ans1 = 0, ans2 = 0;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 2 * len; i++) {
            char c = s.charAt(i), c1 = first.charAt(i), c2 = second.charAt(i);
            if (c != c1)
                ans1++;
            if (c != c2)
                ans2++;
            if (i >= len) {
                //subtract the left most element
                if (s.charAt(i - len) != first.charAt(i - len))
                    ans1--;
                if (s.charAt(i - len) != second.charAt(i - len))
                    ans2--;
            }
            if (i >= len) {
                ans = Math.min(Math.min(ans, ans1), ans2);
            }
        }
        return ans;
    }

    String get(int len, boolean isZero) {
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (isZero) {
                sbr.append("0");
            } else {
                sbr.append("1");
            }
            isZero = !isZero;
        }
        return sbr.toString();
    }
}
