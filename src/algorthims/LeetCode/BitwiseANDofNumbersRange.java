

public class BitwiseANDofNumbersRange {
	
	public static int rangeBitwiseAnd(int m, int n) {
        
        while( n > m ){
			n = n & n-1;
			
		}
		
		return n & m;
    }
	public static void main (String[] args) {
		
		System.out.println( rangeBitwiseAnd(5,7));
	}
}

