
import java.util.*;	

public class JewelsandStones {
	
	public static int numJewelsInStones(String J, String S) {
        Map<Character, Boolean> map = new HashMap<>();
        int count = 0;
        
        for( int i = 0; i< J.length(); i++ ){
			map.put( J.charAt(i), true );
		}
		
		for( int i = 0; i< S.length(); i++ ){
			if( map.get( S.charAt(i) ) != null ){
				count++;
			}
		}
		
		return count;
    }
	public static void main (String[] args) {
		
		System.out.println( numJewelsInStones( "aA", "aAAbbbb"));
	}
}

