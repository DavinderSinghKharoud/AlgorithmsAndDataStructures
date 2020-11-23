package algorithms.HackerRank;


import java.util.*;

/**
 * ou are a waiter at a party. There are  stacked plates on pile . Each plate has a number written on it. Then there will be iterations. In -th iteration, you start picking up the plates in  from the top one by one and check whether the number written on the plate is divisible by the -th prime. If the number is divisible, you stack that plate on pile . Otherwise, you stack that plate on pile . After  iterations, plates can only be on pile , . Output numbers on these plates from top to bottom of each piles in order of , .
 * Input Format
 * The first line contains two space separated integers,  and .
 * The next line contains  space separated integers representing the initial pile of plates, i.e., . The leftmost value represents the bottom plate of the pile.
 * Constraints
 *
 *
 *
 * Output Format
 * Output  lines. Each line contains a number written on the plate. Printing should be done in the order defined above.
 * Sample Input 0
 * 5 1
 * 3 4 7 6 5
 * Sample Output 0
 * 4
 * 6
 * 3
 * 7
 * 5
 * Explanation 0
 * Initially:
 *  = [3, 4, 7, 6, 5]<-TOP
 * After 1 iteration:
 *  = []<-TOP
 *  = [6, 4]<-TOP
 *  = [5, 7, 3]<-TOP
 * We should output numbers in  first from top to bottom, and then output numbers in  from top to bottom.
 * Sample Input 1
 * 5 2
 * 3 3 4 4 9
 * Sample Output 1
 * 4
 * 4
 * 9
 * 3
 * 3
 * Explanation 1
 * Initially:
 *  = [3, 3, 4, 4, 9]<-TOP
 * After  iteration:
 *  = []<-TOP
 *  = [4, 4]<-TOP
 *  = [3, 3, 9]<-TOP
 * After  iteration:
 *  = []<-TOP
 *  = [4, 4]<- TOP
 *  = [3, 3, 9]<-TOP
 * We should output numbers in  first from top to bottom, and then output numbers in  from top to bottom.
 */
public class Waiter {

    static int[] waiter(int[] number, int q) {

        int[] res = new int[number.length];
        int index = 0;

		Stack<Integer> a = new Stack<>();
		
		for(int i = number.length - 1; i >= 0; i-- ){
			a.add(number[i]);
		}
		
		
		int prime = 2; //first prime
		
		for(int count = 0; count < q; count++ ){
			Stack<Integer> b = new Stack<>();
			Stack<Integer> c = new Stack<>();
			
			while( !a.isEmpty() ){
				int num = a.pop();
				
				if( num % prime == 0 ){
					b.add(num);
				}else{
					c.add(num);
				}
			}
			
			index = addRes(b, res, index);
			a = c;
			prime = nextPrime( prime );
		}
		
		addRes(a, res, index);
		return res;
    }

    private static int addRes(Stack<Integer> stack, int[] res, int index ) {

        int temp = index + stack.size() - 1;
        index = index + stack.size();
        while ( !stack.isEmpty() ){
            res[temp--] = stack.pop();
        }
        return index;
    }


    public static int nextPrime( int num ){
		for(int n = num + 1; ; n++ ){
			if( isPrime(n) ){
				return n;
			}
		}
	}
	
	public static boolean isPrime( int num ){
		for(int n = 2; n * n <= num; n++ ){
			if( num % n == 0 ) return false;
		}
		return true;
	}
    public static void main(String[] args) {

        System.out.println(Arrays.toString(waiter(new int[]{3, 3, 4, 4, 9}, 2)));
    }
}
