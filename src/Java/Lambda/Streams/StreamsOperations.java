package Java.Lambda.Streams;

import java.util.stream.IntStream;

public class StreamsOperations {

    public static void main(String[] args) {

        //it is skipping first 5 elements
//        IntStream.range(1, 10)
//                .skip(5)
//                .forEach( System.out::println);

        //it is sum first 5 elements
        System.out.println( IntStream.range(1, 10)
                .sum());

    }
}
