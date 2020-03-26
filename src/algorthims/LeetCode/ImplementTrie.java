// package algorthims.LeetCode;

import java.util.HashMap;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 */
public class ImplementTrie {
    HashMap<String, Boolean> map;

    /**
     * Initialize your data structure here.
     */
    public ImplementTrie() {
        map = new HashMap<>();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        map.put(word, true);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (map.get(word) != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        for (String keys : map.keySet()){
		  if( keys.length() >= prefix.length() && keys.substring(0, prefix.length() ).equals( prefix) ){
			return true;
		  }
	}
	return false;
    }
		  

    public static void main(String[] args) {
         ImplementTrie trie = new ImplementTrie();
	 trie.insert( "word");
	 System.out.println( trie.search("word"));
	 System.out.println( trie.startsWith("wo"));
	 
	 
    }
         

}
