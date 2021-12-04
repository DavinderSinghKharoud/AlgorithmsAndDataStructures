package Preparation;

public class OneEditDistance {

    public static void main(String[] args) {
        System.out.println(new OneEditDistance().isOneEditDistance2("adbDd", "adbdd"));
    }

    public boolean isOneEditDistance2(String s, String t) {
        int m = s.length(), n = t.length();
        if (Math.abs(m - n) > 1) return false;
        int k = Math.min(m, n);
        int i = 0, j = 0;
        while (i < k && s.charAt(i) == t.charAt(i)) ++i;
        while (j < k - i && s.charAt(m - 1 - j) == t.charAt(n - 1 - j)) ++j;
        return m + n - k - 1 == i + j;
    }

    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length(), len2 = t.length();
        if (Math.abs(len1 - len2) > 1) return false;

        for (int i = 0; i < Math.min(len1, len2); i++) {
            char cs = s.charAt(i), ct = t.charAt(i);
            //Delete current character
            String substring = s.substring(i + 1, s.length());
            String modi = s.substring(0, i) + substring;
            if (modi.equals(t)) return true;
            //change this character if not equals
            if (cs != ct) {
                modi = s.substring(0, i) + ct + substring;
                if (modi.equals(t)) return true;

                //insert char if not equals
                modi = s.substring(0, i) + ct + s.substring(i, s.length());
                if (modi.equals(t)) return true;
            }
        }

        if (len2 > len1) {
            return s.equals(t.substring(0, len2 - 1));
        } else if (len1 > len2) {
            return s.substring(0, s.length() - 1).equals(t);
        }
        return false;
    }
}
