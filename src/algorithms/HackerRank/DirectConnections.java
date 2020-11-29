package algorithms.HackerRank;

import java.io.IOException;
import java.util.*;

public class DirectConnections {




    public static void main(String[] args) throws IOException {
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
    
    
    

}
