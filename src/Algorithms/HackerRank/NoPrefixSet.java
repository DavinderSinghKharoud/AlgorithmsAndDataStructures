package Algorithms.HackerRank;

import java.util.*;

/**
 * Given  strings. Each string contains only lowercase letters from (both inclusive). The set of  strings is said to be GOOD SET if no string is prefix of another string else, it is BAD SET. (If two strings are identical, they are considered prefixes of each other.)
 * For example, aab, abcde, aabcd is BAD SET because aab is prefix of aabcd.
 * Print GOOD SET if it satisfies the problem requirement.
 * Else, print BAD SET and the first string for which the condition fails.
 * Input Format
 * First line contains , the number of strings in the set.
 * Then next  lines follow, where  line contains  string.
 * Constraints
 *
 *  Length of the string
 * Output Format
 * Output GOOD SET if the set is valid.
 * Else, output BAD SET followed by the first string for which the condition fails.
 * Sample Input00
 * 7
 * aab
 * defgab
 * abcde
 * aabcde
 * cedaaa
 * bbbbbbbbbb
 * jabjjjad
 * Sample Output00
 * BAD SET
 * aabcde
 * Sample Input01
 * 4
 * aab
 * aac
 * aacghgh
 * aabghgh
 * Sample Output01
 * BAD SET
 * aacghgh
 * Explanation
 * aab is prefix of aabcde. So set is BAD SET and it fails at string aabcde.
 */
public class NoPrefixSet {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int queries = sc.nextInt();

        Node root = new Node();
        for (int count = 0; count < queries; count++) {
            String str = sc.next();


            if (!isGoodSetOrAdd(str, root)) {
                return;
            }
        }

        System.out.print("GOOD SET");
    }

    public static boolean isGoodSetOrAdd(String s, Node node) {

        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);

            Node curr = node.childrens.getOrDefault(c, new Node());
            if (curr.isWord) {
                System.out.println("BAD SET");
                System.out.print(s);
                return false;
            }
            if (index == s.length() - 1) {
                curr.isWord = true;
                if (curr.childrens.size() > 0) {
                    System.out.println("BAD SET");
                    System.out.print(s);
                    return false;
                }
            }

            node.childrens.put(c, curr);

            node = node.childrens.get(c);
        }

        return true;
    }


    private static class Node {
        Map<Character, Node> childrens;
        boolean isWord;

        public Node() {
            childrens = new HashMap<>();
            isWord = false;
        }
    }
}
