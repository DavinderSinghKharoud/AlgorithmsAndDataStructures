package LeetCode;

import java.util.*;

/**
 * Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
 *
 * For a given query word, the spell checker handles two categories of spelling mistakes:
 *
 * Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
 * Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
 * Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
 * Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
 * Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually, it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
 * Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
 * Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
 * Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
 * In addition, the spell checker operates under the following precedence rules:
 *
 * When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
 * When the query matches a word up to capitlization, you should return the first such match in the wordlist.
 * When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
 * If the query has no matches in the wordlist, you should return the empty string.
 * Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].
 *
 *
 *
 * Example 1:
 *
 * Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
 * Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
 * Example 2:
 *
 * Input: wordlist = ["yellow"], queries = ["YellOw"]
 * Output: ["yellow"]
 *
 *
 * Constraints:
 *
 * 1 <= wordlist.length, queries.length <= 5000
 * 1 <= wordlist[i].length, queries[i].length <= 7
 * wordlist[i] and queries[i] consist only of only English letters.
 */
public class VowelSpellChecker {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new VowelSpellChecker().spellchecker(
                new String[]{"YellOw"},
                new String[]{"yollow"}
        )));
    }

    public String[] spellchecker(String[] wordlist, String[] queries) {
        int len = queries.length;
        String[] ans = new String[len];
        Set<String> full = new HashSet<>();
        Map<String, String> cap = new HashMap<>();
        Map<String, String> vowelsError = new HashMap<>();

        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        StringBuilder sbr = new StringBuilder();
        for (String word : wordlist) {
            full.add(word);
            String lower = word.toLowerCase();
            if (!cap.containsKey(lower)) cap.put(lower, word);
            sbr.delete(0, sbr.length());
            for (int i = 0; i < word.length(); i++) {
                char lcase = Character.toLowerCase(word.charAt(i));
                if (!vowels.contains(lcase)) {
                    sbr.append(word.charAt(i));
                }else sbr.append("*");
            }
            String without = sbr.toString().toLowerCase();
            if (!vowelsError.containsKey(without)) {
                vowelsError.put(without, word);
            }
        }
        for (int i = 0; i < len; i++) {
            String query = queries[i];
            String lower = query.toLowerCase();
            if (full.contains(query)) ans[i] = query;
            else if (cap.containsKey(lower)) ans[i] = cap.get(lower);
            else {
                sbr.delete(0, sbr.length());
                for (int j = 0; j < lower.length(); j++) {
                    if(!vowels.contains(lower.charAt(j))) sbr.append(lower.charAt(j));
                    else sbr.append("*");
                }
                ans[i] = vowelsError.getOrDefault(sbr.toString(), "");
            }
        }
        return ans;
    }
}
