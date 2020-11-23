package algorithms.LeetCode;

public class CountAndSay {

    public static String countAndSay(int n) {

        if (n < 1)
            return "";

        if (n == 1)
            return "1";

        String prev = countAndSay(n - 1);

        int indx = 0;
        int len = prev.length();
        StringBuilder sb = new StringBuilder();
        while (indx < len) {

            int count = 0;
            char cur = prev.charAt(indx);
            while (indx < len && prev.charAt(indx) == cur) {
                indx++;
                count++;
            }
            sb.append(count);
            sb.append(cur);
        }

        return sb.toString();

    }

    public static void main(String[] args) {

        System.out.println(countAndSay(4));
    }
}
