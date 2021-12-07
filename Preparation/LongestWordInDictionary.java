package Preparation;

/**
 * Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.
 * <p>
 * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: words = ["w","wo","wor","worl","world"]
 * Output: "world"
 * Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 * <p>
 * Input: words = ["a","banana","app","appl","ap","apply","apple"]
 * Output: "apple"
 * Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * words[i] consists of lowercase English letters.
 */

import java.util.*;

public class LongestWordInDictionary {

    String[] words;

    public String longestWord(String[] words) {
        this.words = words;

        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i);
        }

        return trie.findLongest(words);
    }

    class Trie {
        Node root;

        public Trie() {
            root = new Node(')');
        }

        public void insert(String word, int index) {
            Node node = root;

            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                node.childrens.putIfAbsent(c, new Node(c));
                node = node.childrens.get(c);
            }
            node.end = index;
        }

        public String findLongest(String[] words) {
            String ans = "";
            Stack<Node> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                Node curr = stack.pop();
                //Update ans
                if (curr.end >= 0) ans = getLexLongest(ans, words[curr.end]);
                for (Map.Entry<Character, Node> entry : curr.childrens.entrySet()) {
                    Node child = entry.getValue();
                    if (child.end >= 0) {
                        stack.add(child);
                    }
                }
            }
            return ans;
        }

        String getLexLongest(String ans, String b) {
            if (b.length() > ans.length()) return b;
            if (b.length() == ans.length()) {
                int compare = b.compareTo(ans);
                if (compare < 0) return b;
            }
            return ans;
        }
    }

    static class Node {
        char c;
        Map<Character, Node> childrens;
        int end;

        public Node(char c) {
            this.c = c;
            childrens = new HashMap<>();
            end = -1;
        }
    }
}
