package Templates.Palindrome;

import java.util.Arrays;

public class ManacherAlgo {
    //abbabb -->[1, 1, 1, 3, 1, 1]
    int[] manacherOdd(String s) {
        int[] res = new int[s.length()];
        for (int i = 0, l = 0, r = -1; i < s.length(); i++) {
            int k = (i > r) ? 1 : Math.min(res[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < s.length() && s.charAt(i - k) == s.charAt(i + k)) {
                k++;
            }

            res[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }
        return res;
    }

    //"abbabba" --> [1, 1, 2, 1, 1, 2, 1]
    int[] manacherEven(String s) {
        int[] res = new int[s.length()];
        for (int i = 0, l = 0, r = -1; i < s.length(); i++) {
            int k = (i > r) ? 0 : Math.min(res[l + r - i + 1], r - i + 1);
            while (0 <= i - k - 1 && i + k < s.length() && s.charAt(i - k - 1) == s.charAt(i + k)) {
                k++;
            }

            res[i] = k--;
            if (i + k > r) {
                l = i - k - 1;
                r = i + k;
            }
        }
        return Arrays.stream(res).map(num -> {
            if (num == 0) return 1;
            return num;
        }).toArray();
    }
}
