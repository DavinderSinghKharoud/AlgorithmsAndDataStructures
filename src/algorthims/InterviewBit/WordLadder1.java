package algorthims.InterviewBit;

import java.util.*;

public class WordLadder1 {

    public static int solve(String start, String end, List<String> dic) {

		Set<String> set = new HashSet<>(dic);
		set.add(end);
		LinkedList<Node> queue = new LinkedList<>();
		
		queue.offer(new Node(start, 1) );
		
		while( !queue.isEmpty() ){
			Node curr = queue.pop();
			String word = curr.word;
			
			if( word.equals(end) ){ //If we found the result
				return curr.topLength;
			}
			
			char[] currWord = word.toCharArray();
			for( int index = 0; index < currWord.length; index++ ){
				char temp = currWord[index];
				for(int c = 'a'; c <= 'z'; c++ ){
					
					if( c != temp ){
						currWord[index] = (char)c;
					}
					
					String modified = new String(currWord);
					
					if( set.contains(modified) ){
						queue.offer(new Node(modified, curr.topLength + 1) );
						set.remove(modified);
					}
				}
				
				currWord[index] = temp; //again setting the default character
			}
		}
		
		return -1;
    }
    public static void main(String[] args) {


		System.out.println( solve("hit","cog", Arrays.asList("hot", "dot", "dog", "lot", "log")));
    }
    
    static class Node{
		String word;
		int topLength;
		
		public Node( String word, int topLength ){
			this.word = word;
			this.topLength = topLength;
		}
	}
}
