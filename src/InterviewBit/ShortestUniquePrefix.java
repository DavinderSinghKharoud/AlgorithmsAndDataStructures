package InterviewBit;

import java.util.*;

public class ShortestUniquePrefix {

    //O( n * m) time complexity and O( total of unique string length) space complexity
    public ArrayList<String> prefix(List<String> lst) {
        int len = lst.size();
        ArrayList<String> res = new ArrayList<>();
        if (len == 0) return res;

        Trie trie = new Trie();

        for (String str : lst) {
            trie.insert(str);
        }

        for( String str: lst ){
            res.add(trie.getPrefix(str));
        }
        return res;

    }


    class Node {

        private HashMap<Character, Node> children = new HashMap<>();
		private HashMap<Character, Integer> words = new HashMap<>();

    }


    class Trie {

        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String str) {

            Node temp = root;

            for (char c : str.toCharArray()) {

                if (!temp.children.containsKey(c)) {
                    Node node = new Node();
                    temp.children.put(c, node);
                    temp.words.put(c, 1);
                } else {
                    temp.words.put(c, temp.words.get(c) + 1);
                }

                temp = temp.children.get(c);
            }

        }

		public String getPrefix( String str ){
			
			StringBuilder sbr = new StringBuilder();
			Node curr = root;
			for( char c: str.toCharArray() ){
				sbr.append(c);
				
				if( curr.words.get(c) == 1 ){
				    return sbr.toString();
                }

				curr = curr.children.get(c);
			}

			return "";
		}
        public ArrayList<String> getUniquePrefix() {

            ArrayList<String> res = new ArrayList<>();

            StringBuilder sbr = new StringBuilder();
            getUniqueUtil(root, res, sbr);

            return res;
        }

        public void getUniqueUtil(Node node, ArrayList<String> res, StringBuilder sbr) {

            for (Character c : node.children.keySet()) {
                sbr.append(c);
                if (node.words.get(c) == 1) {
                    res.add(sbr.toString());
                    sbr.deleteCharAt(sbr.length() - 1);
                    continue;
                } else {
                    getUniqueUtil(node.children.get(c), res, sbr);
                }
                sbr.deleteCharAt(sbr.length() - 1);
            }
        }


    }


    public static void main(String[] args) {

        ShortestUniquePrefix shortestUniquePrefix = new ShortestUniquePrefix();
        System.out.println(shortestUniquePrefix.prefix(Arrays.asList("zebra", "dog", "duck", "dove")));
    }
}
