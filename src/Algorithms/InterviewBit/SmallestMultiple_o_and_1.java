package Algorithms.InterviewBit;

import java.util.*;

/**
 * You are given an integer N. You have to find smallest multiple of N which consists of digits 0 and 1 only. Since this multiple could be large, return it in form of a string.
 * <p>
 * Note:
 * <p>
 * Returned string should not contain leading zeroes.
 * For example,
 * <p>
 * For N = 55, 110 is smallest multiple consisting of digits 0 and 1.
 * For N = 2, 10 is the answer.
 */
public class SmallestMultiple_o_and_1 {

    //O(n * len_of_the_String_ans)  time and O(n) space complexity
    public static String multiple(int num) {

        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        Set<Integer> set = new HashSet<>();

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int remainder = getRemainder(curr, num);
            if (remainder == 0) return curr;


            // If this remainder is not previously seen, then push t0 and t1 in our queue
            if (!set.contains(remainder)) {
                set.add(remainder);
                queue.add(curr + "0");
                queue.add(curr + "1");
            }
        }

        return "";
    }

    public static int getRemainder(String s, int n) {

        int remaining = 0;

        for (char c : s.toCharArray()) {
            int curr = remaining * 10 + Character.getNumericValue(c);
            remaining = curr % n;

        }

        return remaining;
    }

    public static void main(String[] args) {

        System.out.println(multiple3(55));
    }
    
    //O(n) time and space complexity
    public static String multiple2(int num) {

		if( num == 1 ){
			return "1";
		}
		Map<Integer, String> dp = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        dp.put(1, "1");
        
        while ( !queue.isEmpty() ){
			int rem = queue.poll();
			
			if( rem == 0 ){
				return dp.get(rem);
			}
			
			for(int digit: new int[]{0, 1} ){
				int new_rem = ( 10 * rem + digit) % num;
				
				if( !dp.containsKey(new_rem) ){
					queue.add(new_rem);
					dp.put(new_rem, dp.get(rem) + (char)( digit + '0' ) );
				}
			}
		}
		
        
        return "";
    }

    //O(n) time and space complexity
     public static String multiple3(int num) {
		
		if(num == 1 ){
		 	return "1";
		}
		
		int[] parent = new int[num];
		int[] state = new int[num];
		int[] steps = new int[]{0, 1};
		
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        
        Arrays.fill(parent, -1);
        Arrays.fill(state, -1);
        
        while( !queue.isEmpty() ){
			int curr = queue.poll();
			if( curr == 0 ) break;
			
			for( int step: steps ){
				int next_rem = (curr * 10 + step) % num;
				if( parent[next_rem] == -1 ){
					parent[next_rem] = curr;
					state[next_rem] = step;
					queue.add(next_rem);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int rem = 0;
		
		while( rem != 1 ){
			sb.append(state[rem]);
			rem = parent[rem];
		}
		
		sb.append("1");
		return sb.reverse().toString();
		
        
	 }
    
}
