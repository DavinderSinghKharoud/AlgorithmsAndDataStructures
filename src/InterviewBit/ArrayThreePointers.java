/*
You are given 3 arrays A, B and C. All 3 of the arrays are sorted.

Find i, j, k such that :
max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i])) is minimized.
Return the minimum max(abs(A[i] - B[j]), abs(B[j] - C[k]), abs(C[k] - A[i]))

**abs(x) is absolute value of x and is implemented in the following manner : **

      if (x < 0) return -x;
      else return x;
Example :

Input : 
        A : [1, 4, 10]
        B : [2, 15, 20]
        C : [10, 12]

Output : 5 
         With 10 from A, 15 from B and 10 from C. 
 * 
 */

import java.util.*;

public class ArrayThreePointers {
	
	public static int minimize(final List<Integer> A, final List<Integer> B, final List<Integer> C) {
		
		int diff = Integer.MAX_VALUE;
		
		//Traverse Array
		int index1 = 0, index2 = 0, index3 = 0;
		
		while( index1 < A.size() && index2 < B.size() && index3 < C.size() ){
			
			int max = Math.max( A.get(index1), Math.max( B.get(index2) , C.get(index3) ));
			int min = Math.min( A.get(index1), Math.min( B.get(index2) , C.get(index3) ));
			
			int curr = max - min;
			
			if( curr < diff ){
				diff = curr;
			}
			
			//if diff is zero, we cannot get less than that
			if( diff == 0 ) break;
			
			//increment the index at which value is minimum, because increasing the smaller value can decrease the difference
			if( A.get(index1) == min ) index1++;
			else if( B.get(index2) == min ) index2++;
			else index3++;
			
			
		}
		
		return diff;
    }
	public static void main (String[] args) {
		
		System.out.println( minimize(Arrays.asList(1, 4, 10), Arrays.asList(2, 15, 20), Arrays.asList(10, 12)));
	}
}

