package Generic;

import java.util.HashMap;
import java.util.Map;

public class testing {TrieNode root;

    /** Initialize your data structure here. */
    public testing() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {

        TrieNode temp = root;
        for( char c: word.toCharArray() ){

            if( temp.children.containsKey(c) ){
                temp = temp.children.get(c);
                continue;
            }

            TrieNode curr = new TrieNode();
            temp.children.put( c,curr );
            temp = curr;

        }
        temp.children.put('*', null );
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode temp = root;
        for( char c: word.toCharArray() ){

            if( !temp.children.containsKey(c) ){
                return false;
            }
            temp = temp.children.get(c);
        }

        return temp.children.containsKey('*');
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String word) {
        TrieNode temp = root;
        for( char c: word.toCharArray() ){

            if( !temp.children.containsKey(c) ){
                return false;
            }
            temp = temp.children.get(c);
        }

        return true;
    }

    public static void main(String[] args) {
        testing testing = new testing();
        testing.insert("apple");
        System.out.println(testing.search("apple"));
    }
}

class TrieNode{
    Map<Character, TrieNode> children = new HashMap<>();
}
