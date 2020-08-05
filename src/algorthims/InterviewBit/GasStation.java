package algorthims.InterviewBit;
import java.util.*;

/**
 * Theorem 1: A road trip is only possible if the total gas available is greater or equal to the total cost for the entire roadtrip. If not we can immediatley return -1, since we cannot complete the road trip.
 * Theorem 2: We cannot start at a station if what we have in our tank is less than the cost to get to the next station, we must start at another station if a solution exists.
 */
public class GasStation {

	//O(n) time complexity
    public static int canCompleteCircuit(final List<Integer> gas, final List<Integer> cost) {
		
		int len = gas.size();

		
		int gasAvailable = 0;
		int totalCost = 0;
		
		//Theorem 1
		for(int index = 0; index < len; index++ ){
			gasAvailable += gas.get(index);
			totalCost += cost.get(index);
		}
		
		if( totalCost > gasAvailable ) return -1;
		
		//Theorem 2
		int start = 0, tank = 0;
		
		for(int index = 0; index < len; index++ ){
			
			tank += gas.get(index);
			tank -= cost.get(index);
			
			if( tank < 0 ){//we cannot continue
				start = index + 1; 
				tank = 0;
				
			}
		}
		
		return start;
    }
    public static void main(String[] args) {

		System.out.println( canCompleteCircuit(Arrays.asList(1, 2), Arrays.asList(2, 1)));
    }

    //Almost same as above, just in a single pass
	public int canCompleteCircuit2(int[] gas, int[] cost) {

		int totalCost = 0, gasAvailable = 0, tank = 0, start = 0;
		for (int i = 0; i < gas.length; i++) {
			totalCost += cost[i];
			gasAvailable += gas[i];

			tank += gas[i];
			tank -= cost[i];
			if (tank < 0 ) {
				start = i + 1;
				tank = 0;
			}
		}

		if (totalCost > gasAvailable) return -1;
		return start;
	}
}
