/*
 Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

 */
import java.util.*;

public class SortCharactersByFrequency {

	//O(n) time complexity and O(1) space complexity
	public static String frequencySort(String s) {
		if( s.length() == 0) return "";
		Map<Character, Integer> map = new HashMap<>();

        PriorityQueue<Character> priorityQueue = new PriorityQueue<>( (o1,o2) -> map.get(o2) - map.get(o1) );
        for(char c: s.toCharArray() ){
			map.put( c, map.getOrDefault(c, 0) + 1 );
		}

		priorityQueue.addAll(map.keySet());

		StringBuilder sb = new StringBuilder();

		while ( !priorityQueue.isEmpty() ){
			char c = priorityQueue.poll();
			int value = map.get( c);
			while ( value != 0 ){
				sb.append( c  );
				value--;
			}
		}

		return sb.toString();
    }
	public static void main (String[] args) {
		
		System.out.println( frequencySort("Aabb"));
	}
}

