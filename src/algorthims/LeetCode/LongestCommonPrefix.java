package algorthims.LeetCode;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {

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

    public static void main(String[] args) {

        System.out.println(longestCommonPrefix(new String[]{
                "flower", "flow", "flight"
        }));
    }


}
