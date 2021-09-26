package LeetCode;

public class LongestSubsequenceRepeatedKTimes {

    public static void main(String[] args) {
        LongestSubsequenceRepeatedKTimes o = new LongestSubsequenceRepeatedKTimes();
        System.out.println(o.longestSubsequenceRepeatedK("bbabbabbbbabaababab", 3));
    }

    int len;
    int a = 'a';
    String best = "";

    public String longestSubsequenceRepeatedK(String s, int k) {
        int[] count = new int[26];
        len = s.length();
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - a]++;
        }

       StringBuilder sbr = new StringBuilder();
        for (int i = count.length - 1; i >= 0; i--) {
            if (count[i] >= k) {
                count[i] /= k;
                while (count[i]-- > 0)
                    sbr.append(((char) (a + i)));
            }
        }

        generate(s, sbr.toString(), new StringBuilder(), k, 0);
        return best;
    }

    void generate(String s, String filtered, StringBuilder sbr, int k, int mask) {
        for (int i = 0; i < filtered.length(); i++) {
            if (((mask & (1 << i)) == 0)) {
                sbr.append(filtered.charAt(i));
                if (check(s, sbr, k)) {
                    if (sbr.length() > best.length())
                        best = sbr.toString();
                    generate(s, filtered, sbr, k, mask + (1 << i)); // choose and move forward
                }
                sbr.deleteCharAt(sbr.length() - 1);
            }
        }
    }

    boolean check(String s, StringBuilder sbr, int k) {
        for (int i = 0, j = 0; i < len && k > 0; i++) {
            j += (s.charAt(i) == sbr.charAt(j)) ? 1 : 0; // move next
            if (j == sbr.length()) {
                j = 0;
                k--;
            }
        }
        return k == 0;
    }
}
