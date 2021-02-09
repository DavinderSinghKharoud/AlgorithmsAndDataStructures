import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        int mxN = (int) 5e5;
        int[] arr = new int[mxN];
        ArrayDeque<Integer> lst = new ArrayDeque<>(mxN);
        Stack<Integer> stack = new Stack<>();
        Arrays.fill(arr, 1);
//        for (int i = 0; i < mxN; i++) {
//            lst.add(1);
//        }


        Instant start = Instant.now();

        int is;
        for (int i = 0; i < mxN; i++) {
           //arr[i] = 1;
           stack    .add(1);
        }

        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");

    }
}