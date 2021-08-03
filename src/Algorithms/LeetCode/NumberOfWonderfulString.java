package Algorithms.LeetCode;

import java.util.*;

public class NumberOfWonderfulString {

    public static void main(String[] args) {
        NumberOfWonderfulString o = new NumberOfWonderfulString();
       System.out.println(o.wonderfulSubstrings("ccabccaabb"));
    }

    public long wonderfulSubstrings(String word) {
        long ans = 0;
        int[] count = new int[1025];
        count[0] = 1;
        int mask = 0;

        for (int i = 0; i < word.length(); i++) {
            mask = mask ^ (1 << (word.charAt(i) - 'a'));

            // Add all the even cases to the result
            ans += count[mask];

            for (int j = 0; j < 10; j++) {
                int bitmask = mask ^ (1 << j);
                ans += count[bitmask];
            }
            count[mask]++;
        }
        return ans;
    }
}
