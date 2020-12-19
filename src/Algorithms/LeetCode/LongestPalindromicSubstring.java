package Algorithms.LeetCode;


public class LongestPalindromicSubstring {

    //Time Limit Exceeded, But works
    public static String longestPalindrome(String s) {

        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0;
        int start = 0;
        int end = 0;
        if (s.length() == 0) return "";

        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (check(s, i, j, dp) && j - i > max) {
                    max = j - i;
                    start = i;
                    end = j;
                }
            }


        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }


    public static boolean check(String s, int i, int j, boolean[][] dp) {

        while (i < j) {
            if (dp[i][j]) return true;
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return false;
            }

        }

        return true;
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }


    //Time complexity and Space complexity O(n)
    static String manachersAlgorithm(String s) {
        String str = getModifiedString(s, s.length());
        int len = (2 * s.length()) + 1;
        int[] P = new int[len];
        int c = 0; //stores the center of the longest palindromic substring until now
        int r = 0; //stores the right boundary of the longest palindromic substring until now
        int maxLen = 0;
        //to save the indexes for final longest palindrome
        int res_start = 0;
        int res_end = 0;

        for (int i = 0; i < len; i++) {
            //get mirror index of i
            int mirror = (2 * c) - i; // center - mirror_index = index - center

            //see if the mirror of i is expanding beyond the left boundary of current longest palindrome at center c
            //if it is, then take r - i as P[i]
            //else take P[mirror] as P[i]
            if (i < r) {
                P[i] = Math.min(r - i, P[mirror]);
            }

            //expand at i
            int a = i + (1 + P[i]);
            int b = i - (1 + P[i]);
            while (a < len && b >= 0 && str.charAt(a) == str.charAt(b)) {
                P[i]++;
                a++;
                b--;
            }

            //check if the expanded palindrome at i is expanding beyond the right boundary of current longest palindrome at center c
            //if it is, the new center is i
            if (i + P[i] > r) {
                c = i;
                r = i + P[i];

                if (P[i] > maxLen) { //update maxlen
                    maxLen = P[i];
                    res_start = 2 * c - r;
                    res_end = r;
                }
            }
        }
        return remove_hash(str.substring(res_start, res_end));
    }

    static String getModifiedString(String s, int N) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }

    static String remove_hash(String s) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '#') {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(manachersAlgorithm("aabaabb"));
    }

    ///////////////////
    //Time complexity O(n square) and Space complexity O(n)
    public static String longestPalindrome3(String s) {
        int len = s.length();
        int first = 0, second = 0;
        int max = 0;
        for (int index = 0; index < len; index++) {
            int len1 = expand(s, index, index);
            int len2 = expand(s, index, index + 1);

            if (max < len1) {
                first = index;
                second = -1;
                max = len1;
            }
            if (max < len2) {
                first = index;
                second = index + 1;
                max = len2;
            }
        }

        StringBuilder middle = new StringBuilder();
        if (second == -1) {
            second = first + 1;
            first = first - 1;
            middle.append(s.charAt(first + 1));
        }

        StringBuilder front = new StringBuilder();
        StringBuilder last = new StringBuilder();

        while (first >= 0 && second < len) {
            char c1 = s.charAt(first--);
            char c2 = s.charAt(second++);

            if (c1 == c2) {
                front.append(c1);
                last.append(c2);
            } else {
                break;
            }
        }

        return front.reverse().toString() + middle.toString() + last.toString();
    }

    private static int expand(String s, int start, int end) {

        int len = 0;
        if( start == end ){
            len = 1;
            start--;end++;
        }

        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++)) {
            len += 2;
        }
        return len;
    }

}

