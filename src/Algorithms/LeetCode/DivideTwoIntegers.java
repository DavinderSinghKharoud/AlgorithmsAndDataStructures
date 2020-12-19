

public class DivideTwoIntegers {
	
	public static int divide(int dividend, int divisor) {
	    
	    if( Integer.MIN_VALUE == dividend && divisor == -1 ){
	    	return Integer.MAX_VALUE;
		}
	    if( dividend == 0 ) return 0;

	    int a = Math.abs( dividend );
	    int b = Math.abs( divisor );
	    int res = 0;

	    while ( a - b >= 0 ){
	    	int x = 0; // 2 is to power 0 == 1

			while ( a- ( b << 1 << x) >= 0 ){ // b<<1 means multiply by 2 and <<x means multiply by 2 is to power x
				x++;
			}

			res += 1 << x;
			a -= b << x;
		}

		return ( (dividend >= 0) == (divisor>=0) ) ? res: -res;
	}
	public static void main (String[] args) {
		
		System.out.println( divide( -10, 3 ));
	}
}

