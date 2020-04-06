//package algorthims.LeetCode;


public class FindFirstandLastPositionofElementinSortedArray {
    
	    public static int[] searchRange(int[] nums, int target) {
		    
		    int[] result = new int[2];
		    int min = Integer.MAX_VALUE;
		    int max = -1;
		    
		    int start = 0;
		    int end = nums.length - 1;
		    
		    //for finding the ending postion
		    
		    while( start <= end ){
			int mid = (start + end)/2;
			
			if( nums[mid] == target ){
			    max = Math.max( max, mid );
			    start = mid + 1  ;
			}else if ( nums[mid] < target ){
			    start = mid + 1;
			    
			}else{
			    end = mid - 1;
			}
			
		    }
		    
		    result[1] = max;
		    
		    //for finding the starting position
		    start = 0;
		    end = nums.length - 1;
		    
		    while( start <= end ){
			int mid = (start + end)/2;
			
			if( nums[mid] == target ){
			    min = Math.min( min, mid );
			    end = mid - 1  ;
			}else if ( nums[mid] < target ){
			    start = mid + 1;
			    
			}else{
			    end = mid - 1;
			}
			
		    }
		    
		    result[0] = min == Integer.MAX_VALUE ? -1: min ;
		    return result;
	    }
	
	    public static void main (String[] args) {
		
		int[] res = searchRange( new int[]{
		    5,7,7,8,8,10
		}, 8 );
		
		System.out.println( res[0] + " " + res[1]);
	    }
}

