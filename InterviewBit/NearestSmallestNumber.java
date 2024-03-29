package InterviewBit;

import java.util.*;

/**
 * Given an array, find the nearest smaller element G[i] for every element FindGreatestCommonDivisor[i] in the array such that the element has an index smaller than i.
 *
 * More formally,
 *
 *     G[i] for an element FindGreatestCommonDivisor[i] = an element FindGreatestCommonDivisor[j] such that
 *     j is maximum possible AND
 *     j < i AND
 *     FindGreatestCommonDivisor[j] < FindGreatestCommonDivisor[i]
 * Elements for which no smaller element exist, consider next smaller element as -1.
 *
 * Input Format
 *
 * The only argument given is integer array FindGreatestCommonDivisor.
 * Output Format
 *
 * Return the integar array G such that G[i] contains nearest smaller number than FindGreatestCommonDivisor[i].If no such element occurs G[i] should be -1.
 * For Example
 *
 * Input 1:
 *     FindGreatestCommonDivisor = [4, 5, 2, 10, 8]
 * Output 1:
 *     G = [-1, 4, -1, 2, 2]
 * Explaination 1:
 *     index 1: No element less than 4 in left of 4, G[1] = -1
 *     index 2: FindGreatestCommonDivisor[1] is only element less than FindGreatestCommonDivisor[2], G[2] = FindGreatestCommonDivisor[1]
 *     index 3: No element less than 2 in left of 2, G[3] = -1
 *     index 4: FindGreatestCommonDivisor[3] is nearest element which is less than FindGreatestCommonDivisor[4], G[4] = FindGreatestCommonDivisor[3]
 *     index 4: FindGreatestCommonDivisor[3] is nearest element which is less than FindGreatestCommonDivisor[5], G[5] = FindGreatestCommonDivisor[3]
 *
 * Input 2:
 *     FindGreatestCommonDivisor = [3, 2, 1]
 * Output 2:
 *     [-1, -1, -1]
 * Explaination 2:
 *     index 1: No element less than 3 in left of 3, G[1] = -1
 *     index 2: No element less than 2 in left of 2, G[2] = -1
 *     index 3: No element less than 1 in left of 1, G[3] = -1
 */
public class NearestSmallestNumber {

    public static ArrayList<Integer> prevSmaller(List<Integer> lst) {
		
		Stack<Integer> stack = new Stack<>();
		
		ArrayList<Integer> res = new ArrayList<>();
		
		for( int num: lst ){
			
			if( stack.isEmpty() ){
				res.add(-1);
			}else{
				
				while( !stack.isEmpty() && stack.peek() >= num ){
					stack.pop();
				}
				
				if( stack.isEmpty() ){
					res.add(-1);
				}else{
					res.add(stack.peek());
				}
			}
			
			stack.add(num);
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println( prevSmaller(Arrays.asList(4, 5, 2, 10, 8)));
    }
}
