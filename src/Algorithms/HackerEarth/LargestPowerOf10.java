package Algorithms.HackerEarth;

import java.util.*;
public class LargestPowerOf10 {
	//import for Scanner and other utility classes
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        int q = scan.nextInt();
        
        HashMap<Integer, List<int[]> > map = new HashMap<>();
        
        for( int count = 0; count < n - 1; count++ ){
			int firstNode = scan.nextInt();
			int secondNode = scan.nextInt();
			int weight = scan.nextInt();
			firstNode--;
			secondNode--;

			//adding edges
			List<int[]> lst1 = map.getOrDefault( firstNode, new ArrayList<>() );
			lst1.add(new int[]{secondNode, weight});
			
			map.put( firstNode, lst1 );
			
			List<int[]> lst2 = map.getOrDefault( secondNode, new ArrayList<>() );
			lst2.add ( new int[]{ firstNode, weight } );
			map.put( secondNode, lst2 );
			
		
		}
		
		for( int count = 0; count < q; count++ ){
			int firstNode = scan.nextInt();
			int secondNode = scan.nextInt();
			Set<Integer> check = new HashSet<>();
			System.out.println( findLargestPower(map, --firstNode, --secondNode, check) );
		}
        
    }
    
    public static int findLargestPower(HashMap<Integer, List<int[]> > map, int firstNode, int secondNode, Set<Integer> check ){
		
		int product = findProductSum( map, firstNode, secondNode, check, 0 );
		
		int max = 0;
		int i = 10;
		int power = 0;
		while( i < product ){
			power++;
			if( product % i == 0 ){
				max = Math.max( max, power );
			}

			i *= 10;
		}
		
		return max;
	}
    public static int findProductSum( HashMap<Integer, List<int[]> > map, int start, int end, Set<Integer> check, int totolWeight ){
		
		if( check.contains(start)  ){
			return 0;
		}
		
		if( start == end ){
			return 1;

		}
		
		
		check.add( start );
		
		for(int[] neighbour: map.get(start) ){
			int temp= neighbour[1] * findProductSum( map, neighbour[0], end, check, totolWeight);
			totolWeight = Math.max( totolWeight, temp );
			if( temp > 0 ){
				return totolWeight;
			}
		}
		
		
		return totolWeight;
	}
}
