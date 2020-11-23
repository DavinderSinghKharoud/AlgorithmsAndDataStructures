package algorithms.InterviewBit;

import java.util.*;

public class WordLadderII {

    public static ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dict) {
        
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        if( start.equals(end) ){
        	ArrayList<String> list = new ArrayList<>();
        	list.add(end);
        	result.add(list);
        	return result;
		}
		Map<String,Integer> dic = new HashMap<>();
        for(String s: dict){
        	dic.put(s, null);
		}
        
   
        LinkedList<Node> queue = new LinkedList<>();
        
        Node root = new Node(start, 0, null);
        queue.offer(root);
		int min_len = Integer.MAX_VALUE;
		
		while( !queue.isEmpty() ){
			Node curr = queue.pop();
			if( result.size() > 0 && curr.depth > min_len ){ //It means there is already a pattern that reaches the end and we have exceeded the min length of depth, so no matter even we get another pattern that gives us result but it will not be shortest path
				return result;
			}
			
			String word = curr.word;
			
			char[] wordArr = word.toCharArray();
			
			for( int index = 0; index < wordArr.length; index++ ){
				char temp = wordArr[index];
				for( int c = 'a'; c <= 'z'; c++ ){
					
					if( c == temp ) continue;
					
					wordArr[index] = (char)c;
					String modified = new String(wordArr);
					
					if( modified.equals(end) ){
						ArrayList<String> lst = new ArrayList<>();
						lst.add(end);
						
						Node node = curr;
						while( node != null ){
							lst.add(node.word);
							node = node.prev;
						}
						
						Collections.reverse(lst);
						result.add( lst );
						
						if( curr.depth <= min_len ){
							min_len = curr.depth;
						}else{
							return result;
						}
					}
					
					
					if( dic.containsKey(modified) ){

						if( dic.get(modified) == null || dic.get(modified) >= curr.depth + 1 ){
							dic.computeIfAbsent(modified, k -> curr.depth + 1);
							queue.offer( new Node(modified, curr.depth + 1, curr) );
						}

					}
				}
				wordArr[index] = temp;
			}
			
		}
        return result;
    }

    public static void main(String[] args) {

    	ArrayList<String> arr = new ArrayList<>();
    	arr.add("ted");arr.add("tex");arr.add("tax");arr.add("tad");
		arr.add("den");arr.add("rex");arr.add("pee");
		System.out.println( findLadders("red","tax", arr ));
    }
    
    static class Node{
		String word;
		int depth;
		Node prev;
		
		public Node( String word, int depth, Node prev ){
			this.word = word;
			this.depth = depth;
			this.prev = prev;
		}
	}
}
