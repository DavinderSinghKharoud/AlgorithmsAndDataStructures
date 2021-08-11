package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class QueueReconstructionQueue {

    public static int[][] reconstructQueue(int[][] people) {
        // first sort Desc the array by person[0]. If person[0] has same value ([7,0], [7,1]) then we sort Asc person[1].
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return (a[0] == b[0]) ? a[1] - b[1] : b[0] - a[0];
            }
        });

        // Once we obtain our sorted array, we can add them to our ArrayList's specific index based on person[1]'s value.
        // If there is already a person at that index than that person is pushed to the next index*.
        List<int[]> result = new ArrayList<int[]>();
        for (int[] person : people){
            result.add(person[1], person);
        }

        // As we're converting our ArrayList back to an Array, we need to defined its size (while each subarrays can be omitted if need).
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int [][]queue = reconstructQueue(new int[][]{
                {7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}
        });

        for( int i = 0; i < queue.length; i++ ){

            for( int j = 0; j < queue[1].length; j++ ){
                System.out.print( queue[i][j] + "," );
            }
            System.out.println();
        }
    }
}
