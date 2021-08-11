package InterviewBit;

/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example:
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 */
import java.util.*;
public class LongestConsecutiveSequence {

    public static int longestConsecutive(final List<Integer> lst) {
		int len = lst.size();
		if( len == 0 ) return 0;

		int res = -1;
		Set<Integer> set = new HashSet<>(lst);
		
		for(int num: lst ){
			//find the starting number of any consecutive sequence
			if( !set.contains(num - 1) ){
				int temp = num;
				int currentStreak = 1;

				while ( set.contains(temp + 1 ) ){
					currentStreak++;
					temp++;
				}

				res = Math.max( res, currentStreak );
			}
		}
		
		return res;
    }
    

    public static void main(String[] args) {

		System.out.println( longestConsecutive(Arrays.asList(100, 4, 200, 1, 3, 2)));
    }
}
