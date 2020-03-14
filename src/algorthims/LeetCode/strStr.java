package algorthims.LeetCode;

public class strStr {
    public static int strStr(String haystack, String needle) {

        if (needle.length() == 0 || haystack.equals(needle)) {
            return 0;
        }
        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {

            if (haystack.charAt(i) == needle.charAt(0)) {
                if (haystack.substring(i, i + needle.length()).equals(needle)) {
                    return i;
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(strStr("mississippi", "pi"));
    }
}
