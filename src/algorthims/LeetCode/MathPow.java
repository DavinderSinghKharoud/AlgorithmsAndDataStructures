//package algorthims.LeetCode;


public class MathPow {
	
	// Only work for positive (n)
	public static double myPow(double x, int n) {
		if( n == 1){
		    return x;
		}
		return x * myPow(x, n - 1);
	}
	
	public static void main (String[] args) {
		
		System.out.println( myPow3(2,10
		));
	}
	
	//it works but Time Limit Exceeded 
	public static double myPow2(double x, int n) {
	    double res = 1;
	    for( int i = 0; i<Math.abs(n); i++ ){
		res *= x;
	    }
	    
	    
	    return ( n > 0 )? res: 1/res;
	}
	
	public static double myPow3(double x, int n) {
		if( n < 0){
			return myPow3(1/x, -n);
		}
		if( n == 0 ){
			return 1;
		}
		if( n%2 == 0 ){
			return myPow3(x * x, n /= 2);
		}else {
			return x * myPow3(x * x, (n-1)/2);
		}
	}
}

