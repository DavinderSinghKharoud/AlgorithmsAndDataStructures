package Java.Lambda.Course;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

    static List<Integer> lst = Arrays.asList(1, 2, 3, 4);
    public static void main(String[] args) {
        //setUpStreams();
        //genrateRandom();
        //patternCompile();
        // filterStream();
        //mapStream();
       // filterMapStream();
        sortStream();
    }

    private static void sortStream() {
      //  lst.stream().sorted().forEach(System.out::println);
        //lst.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        List<int[]> lst = Arrays.asList(new int[]{1, 2}, new int[]{2, 3}, new int[]{1, 5});
        lst.stream().sorted(Comparator.comparingInt((int[] arr) -> arr[0]).thenComparingInt((int[] arr) -> arr[1]).reversed()).
                forEach(o -> System.out.println(o[1]));

    }

    private static void filterMapStream() {

        //get the odd numbers, increment by 2 and print
        lst.stream().filter(o -> (o % 2 == 1)).map(o -> o + 2).forEach(System.out::println);
    }

    private static void mapStream() {
        //It is like the projection sql query where number of rows remain same but objects transformed

        lst.stream().map(o -> (o + 2)).forEach(System.out::println);
    }

    private static void filterStream() {

        //It is like where clause of the sql query
        System.out.println(lst.stream().filter(o -> (o % 2 == 0))
                .count());

        //Intermediate operation does not run until they get the destination where results will lie

        lst.stream().filter(o -> {
            System.out.println(o);
            return (o % 2 == 0);
        });
    }

    private static void patternCompile() {
        Stream<String> stream = Pattern.compile(" ").splitAsStream("hello world");
        stream.forEach(System.out::println);
    }

    private static void genrateRandom() {
        Stream<Double> stream = Stream.generate(Math::random).limit(5);
        stream.forEach(System.out::println);

        Stream<Integer> stream1 = Stream.iterate(20, n -> n + 2).limit(5);
        stream1.forEach(System.out::println);
    }

    static void setUpStreams() {

        Set<Integer> set = new HashSet<>(); //It can also be list
        for (int i = 0; i < 5; i++) {
            set.add(i);
        }
        Stream<Integer> stream = set.stream();
        stream.forEach(o -> System.out.println(o));
        stream = set.stream();
        stream.forEach(System.out::println);

        System.out.println("aaaaaaarrrrrrrr");
        int[] arr = {1, 2, 3};
        IntStream stream2 = Arrays.stream(arr);
        stream2 = Arrays.stream(arr, 0, 2);
        stream2.forEach(System.out::println);
    }
}
