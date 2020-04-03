package algorthims.LeetCode;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.**/

public class GasStation{
    
    public static int canCompleteCircuit(int[] gas, int[] cost) {
		
	int result = -1;
	int len = gas.length;
	
	
	for( int start = 0; start<gas.length; start++ ){
	    
	    int next = start + 1;
	    int fuel = gas[start];
	    
	    while( next != start ){

		if( next == len){
		    next = 0;
		}
		//At starting index
		if( fuel < cost[( next == 0)? cost.length - 1: next- 1]){
			break;
		}
		fuel = fuel - cost[( next == 0)? gas.length - 1: next- 1] + gas[next];
		//if fuel is negative, so we cannot move forward
		if( fuel < 0 ){ break; }
			next++;
	    }
	    
	    if( next == start ){ result = start;}

	}
	
	return result;
    }
    
     public static void main(String[] args) {
        
	System.out.println( canCompleteCircuit2( new int[]{
	    3,3,4
	}, new int[]{
	    3,4,4
	}  ) );
    }
    
    //O(n)
      public static int canCompleteCircuit2(int[] gas, int[] cost) {

    	  int sum = 0; //total gas remaining
		  int start = 0; //For tracking the position
		  int defficient = 0;
		  int len = cost.length;

		  for( int i = 0; i<len; i++ ){


			  sum += gas[i] - cost[i];

			  //If sum is less than 0, we cannot travel more
			  if( sum < 0 ){
				  start = i + 1; //Reset the starting index
				  defficient += sum;
				  sum = 0; //Reset again to zero
			  }
		  }

		  return ( sum + defficient >= 0) ? start: -1;
      }
    
}
