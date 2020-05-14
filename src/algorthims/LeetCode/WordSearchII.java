
import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {

    public static List<String> findWords(char[][] board, String[] words) {

        HashMap<Character, List<int[]>> map = new HashMap<>();
        List<String> res = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                char c = board[row][col];
                List<int[]> list = map.getOrDefault(c, new ArrayList<>());
                list.add(new int[]{row, col});
                map.put(c, list);
            }

        }

        for (String word : words) {
            if (map.get(word.charAt(0)) != null) {
                List<int[]> list = map.get(word.charAt(0));
                for (int[] indices : list) {
                    if (dfs(indices[0], indices[1], board, word, 0)) {
                        res.add(word);
                        break;
                    }
                }
            }
        }
        return res;
    }


    private static boolean dfs(int row, int col, char[][] board, String word, int index) {
        /* when we are going out of the matrix then return false */
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || word.charAt(index) != board[row][col])
            return false;

        /* since we found the word so return true*/
        if (word.length() - 1 == index) {
            return true;
        }
        char temp = board[row][col];
        board[row][col] = '*';

        boolean found =
                dfs(row - 1, col, board, word, index + 1) || /*top*/
                        dfs(row, col + 1, board, word, index + 1) || /*right*/
                        dfs(row + 1, col, board, word, index + 1) || /*bottom*/
                        dfs(row, col - 1, board, word, index + 1); /* left */

        board[row][col] = temp;
        if (found) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] list = new String[]{"oath", "pea", "eat", "rain"};


        //System.out.println(findWords(board, list));

        System.out.println( findWords( new char[][]{
                {'a','a'}
        }, new String[]{
                "a"
        }));
    }
}

