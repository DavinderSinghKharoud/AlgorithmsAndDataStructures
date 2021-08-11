import java.util.*;

public class WordLadder {
	
	 public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		    
		    Map<String,Boolean> visited = new HashMap<>();
		    Map<String, List<String> > all_comb = new HashMap<>();
		    
		    int len = beginWord.length();
		    
		    /**
		     * Do the pre-processing on the given wordList and find all the possible generic/intermediate states. Save these intermediate states in a dictionary with key as the intermediate word and value as the list of words which have the same intermediate word.
		     * */
		    for( String word: wordList ){
			
			for( int j = 0; j<len; j++ ){
			    //key is the generic word
			    //value is the list of words, have the same intermediate generic type
			    String inteWord = word.substring(0, j) + '*' + word.substring(j + 1, len );
			    
			    List<String> transformations = all_comb.getOrDefault( inteWord, new ArrayList<>() );
			    transformations.add( word );
			    all_comb.put( inteWord, transformations );
			}
		    }
		    
		    Queue<String> queue = new LinkedList<>();
		    int level = 1;
		    
		    visited.put( beginWord, true );
		    queue.add( beginWord );
		    
		    while( !queue.isEmpty() ){
			
			int size = queue.size();
			
			for( int count = 0; count < size; count++ ){
			    String curr_word = queue.remove();
			    
			    for( int index = 0; index<len; index++ ){
				//find intermediate word
				String inte_word = curr_word.substring(0, index ) + '*' + curr_word.substring(index + 1, len );
				
				//check for all adjacent words that share this same intermediate state
				for( String adjacentWord: all_comb.getOrDefault(inte_word, new ArrayList<>() )){
				    
				    //if it is the end word
				    if( adjacentWord.equals( endWord )){
					return level + 1;
				    }
				    
				    //Otherwise add to the queue if it is not visited
				    if( !visited.containsKey(adjacentWord )){
					visited.put( adjacentWord, true );
					queue.add( adjacentWord );
				    }
				}
				
			    }
			}
			
			level++;
		    }
		    
		    
		    
			return 0;
	}
	public static void main (String[] args) {
		
		List<String> dic = Arrays.asList(
		    "hot","dot","dog","lot","log","cog"
		);
		
		System.out.println( ladderLength("hit", "cog", dic ));
	}
}

