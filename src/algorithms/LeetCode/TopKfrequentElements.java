package algorithms.LeetCode;

import java.util.*;

public class TopKfrequentElements {

    public static List<Integer> topKFrequent(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for( int num: nums){

            map.put( num, map.getOrDefault(num, 0)+1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));

        for( int n: map.keySet()){
            heap.add(n);
            if( heap.size() > k){
                heap.poll();
            }
        }

        List<Integer> top_k = new LinkedList<>();
        while ( !heap.isEmpty()){
            top_k.add(heap.poll());

        }

        Collections.reverse(top_k);
        return top_k;

    }
    public static void main(String[] args) {

        System.out.println( topKFrequent(new int[]{
                1,1,1,2,2,3,3,3,3
        },2));

    }
}
