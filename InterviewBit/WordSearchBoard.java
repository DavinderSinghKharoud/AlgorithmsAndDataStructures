package InterviewBit;

import java.util.*;

public class WordSearchBoard {

    //Trie Solution( time complexity O(n * m) and space complexity O( n * m )
    public static int TrieExist(String[] arr, String word) {

        Map<Character, TreeNode> map = new HashMap<>();

        for (String curr : arr) {
            constructTree(map, curr);
        }

        return (isExist(map, word, 0)) ? 1 : 0;

    }

    public static void constructTree(Map<Character, TreeNode> map, String word) {

        for (int index = 0; index < word.length(); index++) {
            char c = word.charAt(index);

            if (map.containsKey(c)) {
                TreeNode curr = map.get(c);
                if (index == word.length() - 1) curr.isWord = true;
                map = curr.map;
            } else {
                TreeNode curr = new TreeNode();
                if (index == word.length() - 1) curr.isWord = true;
                map.put(c, curr);
                map = map.get(c).map;
            }

        }
    }

    public static boolean isExist(Map<Character, TreeNode> map, String word, int index) {

        TreeNode curr = map.get(word.charAt(index));
        if (curr == null) return false;

        if (index == word.length() - 1) {
            if (curr.isWord) return true;
        }

        return isExist(curr.map, word, index + 1);

    }

    public static void main(String[] args) {

        System.out.println(exist(new String[]{"ABCE", "SFCS", "ADEE"}, "ABCCED"));
    }

    /**
     * Given a 2D board and a word, find if the word exists in the grid.
     *
     * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The cell itself does not count as an adjacent cell.
     * The same letter cell may be used more than once.
     *
     * Example :
     *
     * Given board =
     *
     * [
     *   ["ABCE"],
     *   ["SFCS"],
     *   ["ADEE"]
     * ]
     * word = "ABCCED", -> returns 1,
     * word = "SEE", -> returns 1,
     * word = "ABCB", -> returns 1,
     * word = "ABFSAB" -> returns 1
     * word = "ABCD" -> returns 0
     * Note that 1 corresponds to true, and 0 corresponds to false.
     * @param s
     * @param word
     * @return
     */
    public static int exist(String[] s, String word) {

        char[][] arr = new char[s.length][];

        for (int index = 0; index < s.length; index++) {
            arr[index] = s[index].toCharArray();
        }

        for (int row = 0; row < arr.length; row++) {
            char[] curr = arr[row];
            for (int col = 0; col < curr.length; col++) {

                if (word.charAt(0) == curr[col]) {
                    if (helper(arr, word, row, col, 0)) {
                        return 1;
                    }
                }
            }
        }
        return 0;
    }

    static int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static boolean helper(char[][] arr, String word, int row, int col, int index) {
        if (row < 0 || row >= arr.length || col < 0 || col >= arr[row].length || arr[row][col] != word.charAt(index)) {
            return false;
        }

        if (index == word.length() - 1) {
            return true;
        }

        for (int[] dir : direc) {

            if (helper(arr, word, row + dir[0], col + dir[1], index + 1)) {
                return true;
            }

        }

        return false;
    }

    static class TreeNode {
        Map<Character, TreeNode> map;
        boolean isWord;

        public TreeNode() {
            map = new HashMap<>();
            isWord = false;
        }
    }
}
