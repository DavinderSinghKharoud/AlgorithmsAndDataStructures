package algorithms.LeetCode;

import java.util.Arrays;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {

        char sChar[] = s.toCharArray();
        char tChar[] = t.toCharArray();
        Arrays.sort( sChar);
        Arrays.sort( tChar );
        s = new String( sChar);
        t = new String( tChar );

        return s.equals( t );

    }

    public static void main(String[] args) {

        System.out.println( isAnagram( "rac", "car"));

    }
}
