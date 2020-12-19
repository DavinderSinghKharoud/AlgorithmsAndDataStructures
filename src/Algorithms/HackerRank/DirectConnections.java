package Algorithms.HackerRank;

import java.io.IOException;
import java.util.*;

/**
 * Enter-View  is a linear, street-like country. By linear, we mean all the cities of the country are placed on a single straight line - the -axis. Thus every city's position can be defined by a single coordinate, , the distance from the left borderline of the country. You can treat all cities as single points.
 * Unfortunately, the dictator of telecommunication of EV (Mr. S. Treat Jr.) doesn't know anything about the modern telecom technologies, except for peer-to-peer connections. Even worse, his thoughts on peer-to-peer connections are extremely faulty: he believes that, if  people are living in city , there must be at least  cables from city  to every other city of EV - this way he can guarantee no congestion will ever occur!
 * Mr. Treat hires you to find out how much cable they need to implement this telecommunication system, given the coordination of the cities and their respective population.
 * Note that The connections between the cities can be shared. Look at the example for the detailed explanation.
 * Input Format
 * A number  is given in the first line and then comes  blocks, each representing a scenario.
 * Each scenario consists of three lines. The first line indicates the number of cities (N). The second line indicates the coordinates of the N cities. The third line contains the population of each of the cities. The cities needn't be in increasing order in the input.
 * Output Format
 * For each scenario of the input, write the length of cable needed in a single line modulo .
 * Constraints
 *
 *
 *
 * Border to border length of the country
 * Sample Input
 * 2
 * 3
 * 1 3 6
 * 10 20 30
 * 5
 * 5 55 555 55555 555555
 * 3333 333 333 33 35
 * Sample Output
 * 280
 * 463055586
 * Explanation
 * For the first test case, having  cities requires  sets of cable connections. Between city  and , which has a population of and , respectively, Mr. Treat believes at least  cables should come out of city 1 for this connection, and at least 20 cables should come out of city  for this connection. Thus, the connection between city  and city  will require  cables, each crossing a distance of  km. Applying this absurd logic to connection 2,3 and 1,3, we have
 *  =>  connections   km of cable
 *  =>  connections   km of cable
 *  =>  connections   km of cable
 * For a total of  , Output is  km of cable
 */
public class DirectConnections {
    public static void main2(String[] args) throws IOException {
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        final Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt(); //limit

        for (int tItr = 0; tItr < t; tItr++) {
            int count = scanner.nextInt();

            List<City2> lst = new ArrayList<>();

            for (int index = 0; index < count; index++) {
                lst.add(new City2(scanner.nextInt()));
            }

            for (int index = 0; index < count; index++) {
                lst.get(index).population = scanner.nextInt();
            }

            int result = solve2(lst);

            System.out.println(result);
        }

        scanner.close();
    }

    //O( n square ) Time complexity and O(1) Space complexity
    private static int solve2(List<City2> cities) {

        int mod = 1000000007;
        int res = 0;

        cities.sort(Comparator.comparingInt(o -> o.population));

        for (int index1 = 0; index1 < cities.size(); index1++) {
            int population = cities.get(index1).population;
            int coordinate = cities.get(index1).coordinate;

            int diff = 0;
            for (int index2 = 0; index2 < index1; index2++) {
                diff += Math.abs(coordinate - cities.get(index2).coordinate);
            }
            res += (diff * population) % mod;
        }
        return res;

    }

    static class City2 {
        int coordinate;
        int population;

        public City2(int coordinate) {
            this.coordinate = coordinate;
        }
    }
    
    
    
    /**
     * Using Fenwick Tree
     */
    static class BinaryIndexTree{
		
		long[] BIT;
		int len;
		
		public BinaryIndexTree( int[] nums ){
			len = nums.length;
			BIT = new long[len + 1];
			
			for(int index = 0; index < len; index++ ){
				update( index, nums[index]);
			}
		}
		
		public void update( int index, int val ){
			index++;
			while( index <= len ){
				BIT[index] += val;
				index += ( index & -index );
			}
		}
		
		public long getSum( int index ){
			long sum = 0;
			index++;
			while( index > 0 ){
				sum += BIT[index];
				index -= ( index & -index );
			}
			return sum;
		}
		
		public long sumRange( int index1, int index2 ){
			return getSum( index2 ) - getSum( index1 - 1);
		}
	}
	
	static class City{
		int coordinate;
		int population;
		int index;
		
		public City( int coordinate ){
			this.coordinate = coordinate;
		}
		
		public City( City other, int index ){
			this.coordinate = other.coordinate;
			this.population = other.population;
			this.index = index;
		}
	}
	

	//Time complexity O (n Log(n) and O(n) Space complexity
	public static void main(String[] args) {
        
        Scanner sc = new Scanner( System.in );
		int total = sc.nextInt();
		int mod = 1000000007;

		while( total --> 0 ){
			int len = sc.nextInt();
			
			List<City> coordinateList = new ArrayList<>();
			List<City> populationList = new ArrayList<>();
			
			for(int index = 0; index < len; index++ ){
				coordinateList.add( new City( sc.nextInt() ));
			}
			
			for( int index = 0; index < len; index++ ){
				coordinateList.get(index).population = sc.nextInt();
			}
			
			//Sort according to coordinates (ascending)
			coordinateList.sort(Comparator.comparingInt(x -> x.coordinate));
			
			int[] sortedCoordinates = new int[len];
			
			for( int index = 0; index < len; index++ ){
				populationList.add( new City(coordinateList.get(index), index));
				sortedCoordinates[index] = coordinateList.get(index).coordinate;
			}
			
			//Sort the population in descending order
			populationList.sort((x, y) -> Integer.compare(y.population, x.population));

			
			int[] cityNumber = new int[len];
			Arrays.fill( cityNumber, 1 );
			
			BinaryIndexTree cityNumberTree = new BinaryIndexTree(cityNumber); // to check how many cities exist
			BinaryIndexTree cityCoordinateTree = new BinaryIndexTree(sortedCoordinates); // to check how many cities are to the left and right


			long totalCable = 0;
			
			for( int index = 0; index < len; index++ ){ //Traverse over the population sorted list
				
				City curr = populationList.get(index);
				int currIndex = curr.index;
				int coordinate = curr.coordinate;
				
				long leftCable = cityNumberTree.getSum( currIndex - 1) * coordinate; //Get the number of cities exist on the left side
				leftCable -= cityCoordinateTree.getSum( currIndex - 1 ); //Subtract the sum of the coordinates existing in the left
				
				long rightCable = cityCoordinateTree.sumRange( currIndex + 1, len - 1); //Get the sum of coodinates to the right as they are sorted will be greater
				rightCable -= cityNumberTree.sumRange( currIndex + 1, len - 1 ) * coordinate; //Subtract the total count of cities exist in the right side.
				
				totalCable += ( leftCable + rightCable ) * curr.population;
				totalCable %= mod;

				//Set the values to zero to exclude the already visited
				cityCoordinateTree.update( currIndex, -sortedCoordinates[currIndex]);
				cityNumberTree.update( currIndex, -cityNumber[currIndex]);
				
			}

			System.out.println(totalCable);
		}
	}
}
