package Algorithms.HackerRank;

import java.util.*;

public class Triplets {

    //Time complexity O( n square ) and O( n ) Space Complexity
    static long solve(long[] d) {
        int len = d.length;
        long[] dp = new long[len];

        Set<Long> set = new HashSet<>();

        for (int index1 = len - 1; index1 >= 0; index1--) {
            int count = 0;
            for (int index2 = index1 + 1; index2 < len; index2++) {
                if (d[index2] > d[index1]) {
                    if( set.contains(d[index2] ) )continue;
                    set.add(d[index2]);
                    count++;
                }
            }
            set = new HashSet<>();
            dp[index1] = count;
        }

        long res = 0;
        Set<Long> distinct = new HashSet<>();
        for (int index1 = 0; index1 < len; index1++) {
            long curr = d[index1];
            if (distinct.contains(curr)) {
                continue;
            } else distinct.add(curr);
            for (int index2 = index1 + 1; index2 < len; index2++) {
                if (d[index2] > curr) {
                    if (set.contains(d[index2])) continue;
                    res += dp[index2];
                    set.add(d[index2]);
                }
            }
            set = new HashSet<>();
        }

        return res;

    }

    public static void main(String[] args) {

        //System.out.println(solve(new long[]{1, 1, 5, 4, 3, 6, 6, 5, 9, 10}));
        
        //useFenwickTree();
        
    }
    
//    public static void useFenwickTree(){
//		Scanner sc = new Scanner(System.in);
//
//		int limit = sc.nextInt();
//		int[] arr = new int[limit];
//
//
//		Set<Integer> set = new HashSet<>();
//		for(int index = 0; index < limit; index++ ){
//			arr[index] = sc.nextInt();
//			set.add(arr[index]);
//		}
//
//		int count = 1; //For fenwick tree
//		Map<Integer,Integer> map = new HashMap<>();
//		FenwickTree a = new FenwickTree(),b = new FenwickTree(),c = new FenwickTree();
//
////		for(int num: set ){
////			map.put(num, count++);
////		}
//
//		for (Iterator<Integer> it = set.iterator(); it.hasNext(); ) {
//			Integer curr = it.next();
//			map.put(curr, count++);
//
//		}
//
//		for(int index = 0; index < limit; index++ ){
//			int currIndex = map.get( arr[index] );
//
//			a.set( currIndex, 1 );
//			b.set( currIndex, a.query(currIndex - 1) );
//			c.set( currIndex, b.query( currIndex - 1) );
//
//		}
//
//		System.out.println( c.query(100000 - 1) );
//
//	}
//
//	private static class FenwickTree{
//
//		int MAX = 100000;
//		int[] tree = new int[MAX];
//
//		public long get(int index ){
//			return query(index) - query(index - 1);
//		}
//
//		public void set( int index, long value ){
//			long curr = get(index);
//			update( index, value - curr);
//		}
//
//		public void update( int index, long value ){
//			while( index <= MAX ){
//				tree[index] += value;
//				index += (index & -index );
//			}
//		}
//
//		public long query( int index ){
//			long sum = 0;
//
//			while( index > 0 ){
//				sum += tree[index];
//				index -= ( index & -index );
//			}
//
//			return sum;
//		}
//
//	}
	
}
