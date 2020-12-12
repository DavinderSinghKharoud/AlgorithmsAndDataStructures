package algorithms.LeetCode;

public class KokoEatingBananas {

	/**
	 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.
	 *
	 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
	 *
	 * Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.
	 *
	 * Return the minimum integer K such that she can eat all the bananas within H hours.
	 *
	 *
	 *
	 * Example 1:
	 *
	 * Input: piles = [3,6,7,11], H = 8
	 * Output: 4
	 * Example 2:
	 *
	 * Input: piles = [30,11,23,4,20], H = 5
	 * Output: 30
	 * Example 3:
	 *
	 * Input: piles = [30,11,23,4,20], H = 6
	 * Output: 23
	 *
	 *
	 * Constraints:
	 *
	 * 1 <= piles.length <= 10^4
	 * piles.length <= H <= 10^9
	 * 1 <= piles[i] <= 10^9
	 */

	//Time complexity O(N log(max size of a pile) ) and Space complexity O(1)
    public static int minEatingSpeed(int[] piles, int H) {

		int low = 1;
		int high = -1;
		
		for(int num: piles){
			high = Math.max(high, num);
		}
		
		//We perform binary search to check if it is possible to eat all bananas and find the minimum
		while( low < high ){
			int mid = ( high - low )/2 + low;
			
			if( isPossible(piles, H, mid) ){
				high = mid;
			}else{
				//Eating speed is too slow
				low = mid + 1;
			}
		}
		
		return low;
        
    }
    
    static boolean isPossible( int[] piles, int hours, int speed ){
		int time = 0;
		
		for(int p: piles ){
			time += ( p - 1)/ speed + 1;
		}
		
		return time <= hours;
	}

    public static void main(String[] args) {

		System.out.print( minEatingSpeed(new int[]{3,6,7,11}, 8));
    }
}
