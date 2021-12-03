package Preparation;

/**
 * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.
 * <p>
 * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 * Output: true
 * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 * Example 2:
 * <p>
 * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 * Output: false
 * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 * Example 3:
 * <p>
 * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 * Output: false
 * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
 */
public class VerifyAlienDictionary {
    int a = 'a';
    int[] orders;

    public boolean isAlienSorted(String[] words, String order) {
        int len = words.length;
        orders = new int[26];
        int id = 1;
        for (int i = 0; i < order.length(); i++) {
            orders[order.charAt(i) - a] = id++;
        }

        if (len == 1) return true;
        String prev = words[0];

        for (int i = 1; i < len; i++) {
            if (!isGreater(words[i], prev)) return false;
            prev = words[i];
        }

        return true;
    }

    boolean isGreater(String first, String second) {
        int len1 = first.length(), len2 = second.length();
        for (int i = 0; i < Math.min(len1, len2); i++) {
            char ca = first.charAt(i), cb = second.charAt(i);
            if (orders[ca - a] > orders[cb - a]) return true;
            else if (orders[ca - a] < orders[cb - a]) return false;
        }
        //System.out.println(len1 + " " + len2);
        if (len1 >= len2) return true;
        return false;
    }

}
