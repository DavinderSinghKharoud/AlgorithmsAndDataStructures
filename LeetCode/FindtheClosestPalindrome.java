package LeetCode;

public class FindtheClosestPalindrome {

    public static void main(String[] args) {
        System.out.println(nearestPalindromic("10"));
    }

    public static String nearestPalindromic(String s) {
        if (s.equals("1")) return "0";
        String standard = findStandard(s);
        String previous = previous(s);
        String forward = forward(s);

        if (standard.equals(s)) {
            if (dis(s, previous) > dis(s, forward))
                return forward;
            return previous;
        }

        long st = dis(standard, s);
        long prev = dis(previous, s);
        long fw = dis(forward, s);

        if (prev <= st && prev <= fw)
            return previous;
        if (st <= prev && st <= fw)
            return standard;

        return forward;

    }

    public static String forward(String s) {
        StringBuilder sbr = new StringBuilder(s);
        int mid = (s.length() - 1) / 2;

        while (mid >= 0 && sbr.charAt(mid) == '9') {
            sbr.replace(mid, mid + 1, "0");
            mid--;
        }

        if (mid < 0) {
            sbr.insert(0, 1);
        } else {
            sbr.replace(mid, mid + 1, "" + (char) (sbr.charAt(mid) + 1));
        }

        return findStandard(sbr.toString());
    }

    public static String findStandard(String s) {
        String half = s.substring(0, (s.length()) / 2);
        StringBuilder otherHalf = new StringBuilder(half).reverse();

        if (s.length() % 2 == 1) {
            // If it is odd
            half += (s.charAt(s.length() / 2));
        }
        return half += otherHalf.toString();
    }

    public static String previous(String s) {
        StringBuilder sbr = new StringBuilder(s);
        int mid = (s.length() - 1) / 2;

        while (mid >= 0 && sbr.charAt(mid) == '0') {
            sbr.replace(mid, mid + 1, "9");
            mid--;
        }

        if (mid == 0 && sbr.charAt(mid) == '1') {
            sbr.delete(0, 1);
            int m = (sbr.length() - 1)/2;
            sbr.replace(m, m + 1, "9");
        } else {
            sbr.replace(mid, mid + 1, "" + (char) (sbr.charAt(mid) - 1));
        }

        String mirror = findStandard(sbr.toString());
        return mirror;
    }

    public static long dis(String a, String b) {
        return Math.abs(Long.parseLong(a) - Long.parseLong(b));
    }

}
