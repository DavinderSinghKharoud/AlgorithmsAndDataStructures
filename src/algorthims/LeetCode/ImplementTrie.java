// package algorthims.LeetCode;

import java.util.*;

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
 
  class TrieNode{
       private TrieNode[] links;
        
	private final int R = 26;
	private boolean isEnd;
	
	public TrieNode(){
	      links = new  TrieNode[R];
       }
       
       public boolean containKey( char ch){
	     return links[ ch - 'a'] != null;
       }
       
       public TrieNode get( char c ){
	     return links[ c - 'a'];
       }
       
       public void put( char ch, TrieNode node ){
	     links[ ch - 'a'] = node;
       }
       
       public void setEnd(){
	     isEnd = true;
       }
       public boolean isEnd(){
	     return isEnd;
       }
       
 }
 
public class ImplementTrie {
      /*
       
    HashMap<String, Boolean> map;

    public ImplementTrie() {
        map = new HashMap<>();
    }

    public void insert(String word) {
        map.put(word, true);
    }

    public boolean search(String word) {
        if (map.get(word) != null) {
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        for (String keys : map.keySet()){
		  if( keys.length() >= prefix.length() && keys.substring(0, prefix.length() ).equals( prefix) ){
			return true;
		  }
	}
	return false;
    } */

/*
	    private TrieNode root;
	     public ImplementTrie(){
		   root = new  TrieNode();
	     }
	     
	     //Inserts a word  into the trie
	     public void insert( String word){
		   TrieNode node = root;
		   for( int i = 0; i< word.length(); i++ ){
			 char currentChar = word.charAt( i );
			 if( !node.containKey(currentChar) ){
			       node.put( currentChar, new TrieNode());
			 }
			 node = node.get( currentChar );
		   }
		   node.isEnd();
	     }
	     
	     //Search 	a prefix or whole key in the trie
	     public TrieNode searchPrefix( String word ){
		   TrieNode node = root;
		   for( int i = 0;  i < word.length();   i ++ ){
			 char curLetter = word.charAt( i );
			 if( node.containKey( curLetter)){
			       node = node.get( curLetter );
			 } else{
			       return null;
			 }
		   }
		   return node;
	     }
	     
	     //Returns if the word is in the trie
	     public boolean search( String word ){
		   TrieNode node = searchPrefix( word );
		   return node != null && node.isEnd();
	     }
	     
	    //Return true if there is any word in the trie with this prefix
	    public boolean startsWith( String prefix ){
		  TrieNode node = searchPrefix( prefix );
		  return node != null;
	    }*/
    
    public static void main(String[] args) {
         ImplementTrie trie = new ImplementTrie();
	 trie.insert( "word");
	 System.out.println( trie.search("word"));
	 System.out.println( trie.startsWith("wo"));
	 
	 
    }
    
     private TrieTree root;
    /** Initialize your data structure here. */
    public ImplementTrie() {
        root = new TrieTree();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieTree node = root;
        for(int i=0; i<word.length(); i++){
            if(node.next[word.charAt(i)-'a'] == null) node.next[word.charAt(i)-'a'] = new TrieTree();
            node = node.next[word.charAt(i)-'a'];
        }
        
        node.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieTree node = root;
        for(int i=0; i<word.length(); i++){
            if(node.next[word.charAt(i)-'a'] == null) return false;
            node = node.next[word.charAt(i)-'a'];
        }
        
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String pre) {
        TrieTree node = root;
        for(int i=0; i<pre.length(); i++){
            if(node.next[pre.charAt(i)-'a'] == null) return false;
            node = node.next[pre.charAt(i)-'a'];
        }
        
        return true;
    }
    
    private class TrieTree {
        private TrieTree[] next;
        private boolean isWord;
        public TrieTree() {
            next = new TrieTree[26];
        }
    }     

}
