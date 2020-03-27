
public class PeakElement {
	    public static int findPeakElement(int[] nums) {
				  
		  if( nums.length == 1 ) return 0;
		  int num  = nums[0];
		  for( int i = 1; i<nums.length; i++ ){
			if( num > nums[ i ]){
			      return i - 1;
			}
			if( i == nums.length - 1){return nums.length - 1;}
			num = nums[ i ];
			
		  }
		  return -1;
	    }
	    public static void main(String[] args) {
			 System.out.println( findPeakElement( new int[] {
			       1,2,3,1}) );
		}
      
      }
