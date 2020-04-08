
import java.util.*;
public class CountingElements {
	
	public static int countElements(int[] arr) {
		
		int result = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		for(int num: arr ){
		    int count = map.getOrDefault( num, 0 );
		    map.put( num, ++count );
		}
		
		for( int num: map.keySet() ){
			if( map.get(num + 1) != null ){
			    result += map.get(num);
			}
		}
		
		return result;
	}
	
	public static void main (String[] args) {
		
		System.out.println( countElements( new  int[]{
		    1,3,2,3,5,0
		}));
	}
}

