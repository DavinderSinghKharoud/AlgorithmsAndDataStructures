//package algorthims.LeetCode;

import java.util.*;

/**
 * Problem Statement
Takahashi loves gold coins. He gains 
1000
 happiness points for each 
500
-yen coin he has and gains 
5
 happiness points for each 
5
-yen coin he has. (Yen is the currency of Japan.)

Takahashi has 
X
 yen. If he exchanges his money so that he will gain the most happiness points, how many happiness points will he earn?

(We assume that there are six kinds of coins available: 
500
-yen, 
100
-yen, 
50
-yen, 
10
-yen, 
5
-yen, and 
1
-yen coins.)**/


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
