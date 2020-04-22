


import java.util.*;

public class  ContiguousArray {
    
        public static int findMaxLength(int[] nums) {
		    int count = 0;
		    Map<Integer,Integer> map = new HashMap<>();
		    map.put(0, - 1);
		    int max = 0;
		    
		    
		    for( int i = 0; i<nums.length; i++ ){
			
			if( nums[i] == 0 ){
			    count -= 1;
			}else{
			    count += 1;
			}
			
			if( map.containsKey(count) ){
			    max = Math.max( max, i - map.get(count));
			}else{
			    map.put( count, i);
			}
		    }
		    
		    return max;
	}
	
	public static void main (String[] args) {
		
		System.out.println( findMaxLength(new int[]{
		    1,0,1
		}));
	}
}

