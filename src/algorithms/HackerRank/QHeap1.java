package algorithms.HackerRank;

import java.util.*;

/**
 * This question is designed to help you get a better understanding of basic heap operations.
 * You will be given queries of  types:
 * " " - Add an element  to the heap.
 * " " - Delete the element  from the heap.
 * "" - Print the minimum of all the elements in the heap.
 * NOTE: It is guaranteed that the element to be deleted will be there in the heap. Also, at any instant, only distinct elements will be in the heap.
 * Input Format
 * The first line contains the number of queries, .
 * Each of the next  lines contains a single query of any one of the  above mentioned types.
 * Constraints
 *
 *
 * Output Format
 * For each query of type , print the minimum value on a single line.
 * Sample Input
 * 5
 * 1 4
 * 1 9
 * 3
 * 2 4
 * 3
 * Sample Output
 * 4
 * 9
 * Explanation
 * After the first  queries, the heap contains {}. Printing the minimum gives  as the output. Then, the  query deletes from the heap, and the  query gives  as the output.
 */
public class QHeap1 {


//    static Map<Integer, Integer> map = new HashMap<>();
//    static List<Integer> pq = new ArrayList<>();

//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        int limit = sc.nextInt();
//
//        for (int count = 0; count < limit; count++) {
//            int type = sc.nextInt();
//
//            if (type == 1) {
//                add(sc.nextInt());
//            } else if (type == 2) {
//                remove(sc.nextInt());
//            } else {
//                printNum();
//            }
//        }
//    }
//
//    public static void add(int num) {
//        if (map.containsKey(num)) return;
//        pq.add(num);
//        map.put(num, pq.size() - 1);
//        shiftUp();
//    }
//
//    public static void shiftUp() {
//        int index = pq.size() - 1;
//
//        while (index > 0) {
//            int parent = (index - 1) / 2;
//            if (pq.get(parent) > pq.get(index)) {
//
//                //swap
//                swap(index, parent);
//
//                //move one level up
//                index = parent;
//
//            } else break;
//        }
//    }
//
//    public static void remove(int num) {
//        int len = pq.size();
//        if (len == 1) {
//            pq.remove(0);
//            map.remove(num);
//        } else {
//            int index = map.get(num);
//            //Swap the values
//            swap(index, pq.size() - 1);
//
//            //Remove the last as already used
//            map.remove(pq.get(pq.size() - 1));
//            pq.remove(pq.size() - 1);
//
//            shiftDown(index);
//
//        }
//    }
//
//    public static void shiftDown(int index) {
//
//        int left = (2 * index) + 1;
//
//        while (left < pq.size()) {
//
//            int right = left + 1;
//            int max = right;
//            if (right < pq.size()) { //If right is valid
//
//                if (pq.get(right) > pq.get(left)) {
//                    max = left; //Replace the max if right is greater
//                }
//            }
//
//            if (pq.get(index) > pq.get(max)) { //We need to change the index
//
//                //Swap the values
//                swap(index, max);
//
//                index = max;
//                left = (2 * index) + 1;
//
//            } else break;
//
//        }
//    }
//
//
//    public static void printNum() {
//
//        System.out.println((pq.size() == 0) ? -1 : pq.get(0));
//    }
//


//    static void swap(int index1, int index2) {
//        int temp = pq.get(index1);
//        pq.set(index1, pq.get(index2));
//        pq.set(index2, temp);
//
//        map.put(pq.get(index1), index1);
//        map.put(pq.get(index2), index2);
//    }


    //For add and delete operation it is O(log n) time complexity and we have O(100000) space complexity


        public static void main(String[] args) {
            int[] arr = new int[100000];
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            Scanner sc = new Scanner(System.in);
            int limit = sc.nextInt();

            for (int i = 0; i < limit; i++) {
                int type = sc.nextInt();

                if (type == 1) {
                    int num = sc.nextInt();
                    if (!map.containsKey(num)) {
                        map.put(num, count + 1);
                        arr[count + 1] = num;
                        count++;
                        rising(arr, count, map);
                    }
                } else if (type == 2) {
                    int num = sc.nextInt();
                    if (!map.containsKey(num)) continue;
                    int index = map.get(num);

                    swap(arr, index, count, map);

                    map.remove(arr[count]);
                    count--;
                    sinking(arr,index, count, map);
                } else {
                    System.out.println(arr[1]);
                }
            }
        }

        private static void sinking(int[] arr, int index, int count, Map<Integer, Integer> map) {
            while (2 * index <= count) {
                int smallestChild = getSmallestChild(arr, index, count);
                if (arr[index] > arr[smallestChild]) {
                    swap(arr,index, smallestChild, map);
                    index = smallestChild;
                } else break;
            }
        }

        private static int getSmallestChild(int[] arr, int index, int count) {
            if (2 * index == count || arr[2 * index] < arr[2 * index + 1]) {
                return 2 * index;
            } else {
                return 2 * index + 1;
            }
        }

        private static void rising(int[] arr, int index, Map<Integer, Integer> map) {

            while (index > 1) {
                if (arr[index] < arr[index / 2]) {
                    swap(arr, index, index / 2, map);
                    index = index / 2;
                } else break;
            }
        }

        private static void swap(int[] arr, int index1, int index2, Map<Integer, Integer> map) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;

            map.put(arr[index1], index1);
            map.put(arr[index2], index2);
        }
}
