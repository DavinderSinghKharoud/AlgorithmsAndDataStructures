package LeetCode;

import java.util.*;

public class CountTheRepetations {

    public static void main(String[] args) {
        CountTheRepetations o = new CountTheRepetations();
        System.out.println(o.getMaxRepetitions("aaaaaaa", 5, "aaaaaaaa", 3));
    }

    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0)
            return 0;
        int[] indexes = new int[s2.length() + 1], counts = new int[s2.length() + 1];

        int index = 0, count = 0;
        for (int i = 0; i < Math.min(n1, s2.length() + 1); i++) {
            for (int j = 0; j < s1.length(); j++) {
                if (s1.charAt(j) == s2.charAt(index)) {
                    index++;
                }
                if (index == s2.length()) {
                    index = 0;
                    count++;
                }

            }

            indexes[i] = index;
            counts[i] = count;

            // Check if there is any cycle
            for (int k = 0; k < i; k++) {
                if (indexes[k] == indexes[i]) {
                    // We found the cycle
                    int prevCount = counts[k];
                    int insideCycle = (counts[i] - counts[k]) * ((n1 - k - 1) / (i - k));
                    int endCount = counts[k + (n1 - 1 - k) % (i - k)] - counts[k];
                    return (prevCount + insideCycle + endCount) / n2;
                }

            }

        }
        return count/n2;
    }
}
