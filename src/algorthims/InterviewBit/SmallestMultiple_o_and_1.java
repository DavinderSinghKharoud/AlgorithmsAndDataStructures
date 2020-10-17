package algorthims.InterviewBit;

import java.util.*;

/**
 * You are given an integer N. You have to find smallest multiple of N which consists of digits 0 and 1 only. Since this multiple could be large, return it in form of a string.
 * <p>
 * Note:
 * <p>
 * Returned string should not contain leading zeroes.
 * For example,
 * <p>
 * For N = 55, 110 is smallest multiple consisting of digits 0 and 1.
 * For N = 2, 10 is the answer.
 */
public class SmallestMultiple_o_and_1 {

    public static String multiple(int num) {

        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        Set<Integer> set = new HashSet<>();

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int remainder = getRemainder(curr, num);
            if (remainder == 0) return curr;


            // If this remainder is not previously seen, then push t0 and t1 in our queue
            if (!set.contains(remainder)) {
                set.add(remainder);
                queue.add(curr + "0");
                queue.add(curr + "1");
            }
        }

        return "";
    }

    public static int getRemainder(String s, int n) {

        int remaining = 0;

        for (char c : s.toCharArray()) {
            int curr = remaining * 10 + Character.getNumericValue(c);
            remaining = curr % n;

        }

        return remaining;
    }

    public static void main(String[] args) {

        System.out.println(multiple(55));
    }
}
