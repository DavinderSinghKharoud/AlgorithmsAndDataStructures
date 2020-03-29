//package algorthims.LeetCode;

import java.util.*;


public class GoldenCoins{
    
    public static int points( int num ){
	
	int points = 0;
	
	int mul = num / 500;
	num %= 500;
	points = points + mul*1000;
	    
	if( num > 0){
	    mul = num/5;
	    num %= 5;
	    points = points + mul*5;
	}
	    
	return points;
	
    }
    public static void main(String[] args) {
	
	Scanner sc = new Scanner( System.in );
	System.out.print( points( sc.nextInt() ));
    }
}
