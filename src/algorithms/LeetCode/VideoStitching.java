package algorithms.LeetCode;

import java.util.*;

public class VideoStitching {

    //Time Complexity O( N square ) and Space Complexity O(n)
    static int videoStitching(int[][] clips, int T) {

        int len = clips.length;
        if( len == 0 ) return 0;

        Arrays.sort(clips, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o2[1], o1[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        });

        if( clips[0][0]  != 0 ) return -1;
        if( clips[0][1] >= T ) return 1;

        int[] dp = new int[len];
        dp[0] = 1;
        for( int index = 1; index < len; index++ ){
			int min = Integer.MAX_VALUE;
			for(int backward = index - 1; backward >= 0; backward-- ){
				
				if( isOverlappPossible( clips, backward, index) ){
					min = Math.min( min, dp[backward] );
				}
			}
			
			if( clips[index][1] >= T ) return (min == Integer.MAX_VALUE)? -1: min + 1;
			dp[index] = min + 1;
		}
		
		return -1;

    }

	static boolean isOverlappPossible( int[][] clips, int index1, int index2 ){
		
		int[] first = clips[index1];
		int[] second = clips[index2];
		
		return first[1] >= second[0];
	}
    public static void main(String[] args) {

        System.out.println( videoStitching(new int[][]{{0,0},{0,6}},5));
    }
}
