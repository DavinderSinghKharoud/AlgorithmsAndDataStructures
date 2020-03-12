package algorthims.LeetCode;

public class LongestCommonPrefix {

    //Brute Force
    public static String longestCommonPrefix1(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        boolean check = false;

        int len = Integer.MAX_VALUE;
        for (String str : strs) {
            len = Math.min(str.length(), len);
        }

        for (int i = 0; i < len; i++) {

            Character ch = strs[0].charAt(i);

            for (int j = 1; j < strs.length; j++) {

                if (!ch.equals(strs[j].charAt(i))) {
                    check = true;
                    break;
                }


            }

            if (check) {
                break;
            }
            stringBuilder.append(ch);
        }

        return stringBuilder.toString();
    }

    //Horizontal scanning
    public static String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)

            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    //Binary Search
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }

    public static void main(String[] args) {

        System.out.println(longestCommonPrefix2(new String[]{
                "flower", "flow", "flight"
        }));
    }


}
