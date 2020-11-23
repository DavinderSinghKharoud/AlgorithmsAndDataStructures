package algorithms.HackerRank;

import java.util.*;
public class MrXAndHisShots {

    //O(Players and shots ) time complexity
    static int solve(int[][] shots, int[][] players) {

        int res = 0;

        for (int[] player : players) {
            for (int[] shot : shots) {
                if (isOverlapp(player, shot)) {
                    res++;
                }
            }
        }

        return res;

    }

    public static boolean isOverlapp(int[] player, int[] shot) {

        int[] first = (player[0] < shot[0]) ? player : shot;
        int[] second = (shot[0] > player[0]) ? shot : player;

        return first[1] >= second[0];

    }

    public static void main(String[] args) {

        System.out.println(solve2(new int[][]{{1, 2}, {2, 3}, {4, 5}, {6, 7}}, new int[][]{{1, 5}, {2, 3}, {4, 7}, {5, 7}}));
        //System.out.println(solve2(new int[]{1, 2, 4, 6}, new int[]{2, 3, 5, 7} , new int[][]{{1, 5}, {2, 3}, {4, 7}, {5, 7}}));
    }
    
    
    static int solve2(int[][] shots, int[][] players) {
		int len = shots.length;

    	int[] shots_start = new int[len];
		int[] shots_end = new int[len];

		for (int index = 0; index < shots.length; index++) {
			shots_start[index] = shots[index][0];
			shots_end[index] = shots[index][1];
		}
		Arrays.sort(shots_start);
		Arrays.sort(shots_end);
		int res = 0;
		
		for(int index = 0; index < players.length; index++ ){
			int left = players[index][0];
			int right = players[index][1];
			
			int count1 = len - lowerBoundSearch( shots_start , 0, len - 1, right); //Number of non-overlap intervals
			int count2 =  upperBoundSearch( shots_end, 0, len - 1, left); //Number of non-overlap intervals
			
			
			int strength = len - ( count1 + count2 );
			
			res += strength;
		}
		
		return res;
	}
	
	public static int lowerBoundSearch( int[] arr, int low, int high, int key ){
		
		while( low <= high ){
			int mid = ( low + high) /2;
			if( arr[mid] > key ){
				high = mid - 1;
			}else {
				low = mid + 1;
			}
		}

		return low;
	}

	public static int upperBoundSearch( int[] arr, int low, int high, int key ){

		while( low <= high ){
			int mid = ( low + high) /2;
			if( arr[mid] >= key ){
				high = mid - 1;
			}else {
				low = mid + 1;
			}
		}

		return low;
	}

}
