package algorithms.LeetCode;

public class FirstBadVersion extends VersionControl{
	
	public static int firstBadVersion(int n) {
			
			int min = -1;
			
			int start = 0;
			int end = n;
			
			while( start <= end ){
				//do not use (start + end)/2, because it will overflow in java
				int mid = start + (end - start) / 2;
				
				if( !isBadVersion( mid ) ){
					min = Math.max( min, mid );
					start = mid + 1;
				}else{
					end = mid - 1;
				}
				
			}
			
		return min + 1;
    }
	public static void main (String[] args) {
		System.out.println( firstBadVersion( 5 ));
	}
}

class VersionControl{
	
	public static boolean isBadVersion( int n ){
		
		switch( n ){
			case 0: return false;
			case 1: return false;
			case 2: return false;
			case 3: return false;
			case 4: return true;
			case 5: return true;
		}
		
		return false;	
	}
	
}

