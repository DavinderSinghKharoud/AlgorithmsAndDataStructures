	package algorithms.LeetCode;

import java.util.*;

public class GroupThePeopleGivenTheGroupSizeTheyBelongTo {

	//O(n) Time complexity and O( number of different Groups ) space complexity
    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		List<List<Integer>> res = new ArrayList<>();
		
		for( int index = 0; index < groupSizes.length; index++ ){
			List<Integer> curr = map.getOrDefault( groupSizes[index], new ArrayList<>() );
			curr.add(index);
			if( curr.size() == groupSizes[index] ){
				res.add(curr);
				map.remove(groupSizes[index]);
			}else{
				map.put( groupSizes[index], curr );
			}
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println( groupThePeople(new int[]{3,3,3,3,3,1,3}));
    }
}
