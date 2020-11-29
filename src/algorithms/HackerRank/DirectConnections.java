package algorithms.HackerRank;

import java.io.IOException;
import java.util.*;

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
