package InterviewBit;

import java.util.*;
public class NextPermutation {

	//O(n) Time and Space complexity
    public static List<Integer> nextPermutation(List<Integer> lst) {
		
		int len = lst.size();

		for( int index = len - 2; index >= 0; index-- ){

			//If we found the number that is just lesser than the "Strictly decreasing sequence" because that is number which has possibility of changing to get next permutation
			if( lst.get(index) < lst.get(index + 1) ){

				//Then we find the number( in strictly decreasing sequence ) which is greater than the number found at index above
				for (int start = len - 1; start > index ; start--) {


					if( lst.get(start) > lst.get(index) ){
						//Swap the numbers because it will be next number for the permutation
						int temp = lst.get(index);
						lst.set(index, lst.get(start) );
						lst.set(start, temp);
						break;
					}

				}

				//inverse the list greater than index because before it was exhausted with no possibility and now got all the possibilities
				inverseList( lst, index + 1 );
				return lst;
			}
		}
		
		Collections.sort(lst);
		return lst;
    }
    
    public static void inverseList( List<Integer> lst, int index ){
		
		int start = index;
		int end = lst.size() - 1;
		
		while( start < end ){
			int temp = lst.get(start);
			lst.set( start, lst.get(end) );
			lst.set( end, temp );
			start++;
			end--;
		}
	}
    public static void main(String[] args) {

		System.out.println( nextPermutation(Arrays.asList(506, 59, 854, 422)));
    }
}
