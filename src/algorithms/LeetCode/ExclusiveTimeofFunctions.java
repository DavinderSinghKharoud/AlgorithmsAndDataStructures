package algorithms.LeetCode;

import java.util.*;

/**
 * On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
 *
 * We store logs in timestamp order that describe when a function is entered or exited.
 *
 * Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".  For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.  "1:end:2" means the function with id 1 ended at the end of timestamp 2.
 *
 * A function's exclusive time is the number of units of time spent in this function.  Note that this does not include any recursive calls to child functions.
 *
 * The CPU is single threaded which means that only one function is being executed at a given time unit.
 *
 * Return the exclusive time of each function, sorted by their function id.
 */
public class ExclusiveTimeofFunctions {

    public static int[] exclusiveTime1(int n, List<String> logs) {

        int[] res = new int[n ];

        logs.sort((o1, o2) -> {
            String[] arr1 = o1.split(":");
            String[] arr2 = o2.split(":");

            return Integer.parseInt(arr1[2]) - Integer.parseInt(arr2[2]);
        });

        Stack<String[]> stack = new Stack<>();

        for( String s: logs ){
            String[] arr = s.split(":");

            if( stack.isEmpty() || arr[1].equals("start") ){
                stack.push(arr );

            }else{
                String[] curr = stack.pop();
                int len = Integer.parseInt(arr[2]) - Integer.parseInt(curr[2]) + 1;
                res[ Integer.parseInt(curr[0]) ] += len;

                if( !stack.isEmpty() ){
                    String[] temp = stack.pop();
                    res[ Integer.parseInt(temp[0]) ] -= len;
                    stack.push( temp );
                }
            }

        }

        return res;
    }
    public static void main(String[] args) {

        System.out.println(Arrays.toString(exclusiveTime2(2,
                Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6"))));
    }

    //O(n) time complexity and the stack can go up to the dpeth of atmost n/2, where n refers to the number of elements in the given logs list
    public static int[] exclusiveTime2(int n, List<String> logs) {
		
		int[] res = new int[n];
		Stack<Integer> stack = new Stack<>();
		int prev;
		
		String[] first = logs.get(0).split(":");
		stack.push( Integer.parseInt( first[0] ));
		prev = Integer.parseInt(first[2]);
		
		
		for( int count = 1; count<logs.size(); count++ ){
			String[] curr = logs.get(count).split(":");
			
			if( curr[1].equals( "start" ) ){
				int temp = Integer.parseInt(curr[2]) - prev;
				
				if( !stack.isEmpty() ){
					res[stack.peek()] += temp;
				}
				
				stack.push( Integer.parseInt(curr[0]) );
				prev = Integer.parseInt(curr[2]);
				
			}else{ // we need to remove
				int len = Integer.parseInt(curr[2]) - prev + 1;
				res[Integer.parseInt(curr[0]) ] += len;
				prev = Integer.parseInt(curr[2]) + 1;
				stack.pop();
			}
		}
		
		return res;
			
    }
}
















