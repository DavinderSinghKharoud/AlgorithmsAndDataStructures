
import java.util.*;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 */
public class WordSearchII {

    //O(board.length) space complexity and time complexity O(not sure 4 is to power m), as we need to traverse the whole 2d array for each word
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


        System.out.println(findWords(board, list));

//        System.out.println( findWords2( new char[][]{
//                {'a','a'}
//        }, new String[]{
//                "a"
//        }));
    }

    //O(m * all the board character) time complexity and O(totol length of all the characters in the words ) space complexity
    public static List<String> findWords2(char[][] board, String[] words) {

		Trie trie = new Trie();
		for( String word: words ){
			trie.insert( word );
		}
		
		Set<String> resSet = new HashSet<>();	
		
		for( int i = 0; i<board.length; i++ ){
			for( int j = 0; j<board[0].length; j++ ){
				explore( board, i, j, resSet, trie.root);
			}
		}
		
		return new ArrayList<>(resSet);
    }
    
    public static void explore( char[][] board, int row, int col, Set<String> resSet, TrieNode root){
		
		if( row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] == '#' ){
			return;
		}
		
		if( !root.children.containsKey( board[row][col] )){
			return;
		}
		
		char temp = board[row][col];

		root = root.children.get( board[row][col] );
        //just to make sure we do not again visit this node
        board[row][col] = '#';
		//if we reach the end of trie
		if( root.children.containsKey( '*' ) ){
			resSet.add( root.word );
		}
		
		//explore all paths 
		explore( board, row - 1, col, resSet, root );
		explore( board, row + 1, col, resSet, root );
		explore( board, row , col - 1, resSet, root );
		explore( board, row , col + 1, resSet, root );
		
		board[row][col] = temp;
	}

    static class TrieNode{
        private HashMap<Character, TrieNode> children = new HashMap<>();
        String word;
    }

    static class Trie{
        private TrieNode root;
        char end ='*';

        public Trie(){
            root = new TrieNode();
        }

        //Inserts a word
        public void insert( String word ){
			
			TrieNode node = root;
            for( int index = 0; index<word.length(); index ++ ){
				
				char c = word.charAt(index);
				if( !node.children.containsKey(c) ){
					TrieNode trieNode = new TrieNode();
					node.children.put(c, trieNode );
				}
				node = node.children.get(c);
			}
			
			node.children.put(end, null);
			//so that we do not need to add the characters to get the final string
			node.word = word;
        }
    }
}

