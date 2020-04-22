package Java.Lambda.Streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsOperations {

    public static void main(String[] args) throws IOException {

        //it is skipping first 5 elements
        IntStream.range(1, 10)
                .skip(5)
                .forEach( System.out::println);

        System.out.println("###################");
        //it is sum first 5 elements
        System.out.println( IntStream.range(1, 10)
                .sum() );

        System.out.println("###################");

        //Sorting
        Stream.of("Ava", "Aneri", "Alberto")
                .sorted()
                .findFirst()
                .ifPresent(System.out::print);


        System.out.println("###################");
        //sorting
        String[] names = { "Jaggi", "Ankush", "Jagdeep"};

        Arrays.stream( names )
                .filter( x -> x.startsWith("J"))
                .sorted()
                .forEach(System.out::println);

        System.out.println("###################");

        //Taking average
        Arrays.stream( new int[]{ 2, 4, 6, 8, 10})
                .map( x -> x * x )
                .average()
                .ifPresent( System.out::println);

        System.out.println("###################");

        //map to lower case
        List<String> people = Arrays.asList("Jaggi", "Ankush", "Jagdeep");
        people.stream()
                .map(String::toLowerCase)
                .filter(x -> x.startsWith("a"))
                .forEach(System.out::println);


        System.out.println("###################");
        //from text file
//        try {
//            Stream<String> bands = Files.lines(Paths.get("band.txt"));
//
//            bands.sorted()
//                    .filter( x -> x.length() > 13 )
//                    .forEach( System.out::println);
//            bands.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("###################");

//        try {
//            List<String> bands = Files.lines(Paths.get("band.txt"))
//                    .filter( x -> x.contains("jit"))
//                    .collect(Collectors.toList());
//            bands.forEach( x -> System.out.println(x));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        System.out.println("###################");
//        Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
//        int rowCount = (int) rows1
//                .map( x -> x.split(","))
//                .filter( x -> x.length == 3)
//                .count();
//        System.out.println( rowCount + "rows.");
//        rows1.close();

        System.out.println("###################");
//        Stream<String> rows1 = Files.lines(Paths.get("data.txt"));
//        rows1.map( x -> x.split(","))
//                .filter( x -> x.length == 3)
//                .filter( x -> Integer.parseInt( x[1] ) > 15 )
//                .forEach( x -> System.out.println( x[0] +" " + x[1] + " " + x[2] ));
//
//        rows1.close();

        System.out.println("###################");
        System.out.println("Stream rows from CSV file ");
//        Stream<String> rows3 = Files.lines(Paths.get("data.txt"));
//        Map<String, Integer> map = new HashMap<>();
//
//        map = rows3.map( x -> x.split(","))
//                .filter( x -> x.length == 3)
//                .filter( x -> Integer.parseInt( x[1] ) > 15 )
//                .collect( Collectors.toMap(
//                        x-> x[0],
//                        x -> Integer.parseInt(x[1])
//                ));
//        for( String key: map.keySet() ){
//            System.out.println(key +" " + map.get(key));
//        }
//
//        rows3.close();


        System.out.println("###################");
        //Reduction - sum
        double total = Stream.of( 7.3, 1.5, 4.8 )
                .reduce( 0.0, ( Double a, Double b) -> a + b );
        System.out.println(total);


        System.out.println("###################");
        //Summary statistics
        IntSummaryStatistics summary = IntStream.of( 7, 2, 19, 88)
                .summaryStatistics();

        System.out.println( summary );
    }
}
