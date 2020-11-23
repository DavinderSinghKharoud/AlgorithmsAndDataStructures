package algorithms.HackerRank;

import java.util.*;

/**
 * Create a list, , of  empty sequences, where each sequence is indexed from  to . The elements within each of the  sequences also use -indexing.
 * Create an integer, , and initialize it to .
 * There are  types of queries that can be performed on the list of sequences:
 * Query: 1 x y
 * Find the sequence, , at index  in .
 * Append integer  to sequence .
 * Query: 2 x y
 * Find the sequence, , at index  in .
 * Find the value of element  in  (where  is the size of ) and assign it to .
 * Print the new value of  on a new line
 * Note:  is the bitwise XOR operation, which corresponds to the ^ operator in most languages. Learn more about it on Wikipedia.  is the modulo operator.
 * Function Description
 * Complete the dynamicArray function below.
 * dynamicArray has the following parameters:
 * - int n: the number of empty sequences to initialize in
 * - string queries[q]: an array of query strings
 * Returns
 * int[]: the results of each type 2 query in the order they are presented
 * Input Format
 * The first line contains two space-separated integers,  (the number of sequences) and  (the number of queries), respectively.
 * Each of the  subsequent lines contains a query in the format defined above, .
 * Constraints
 *
 *
 *
 * It is guaranteed that query type  will never query an empty sequence or index.
 * Sample Input
 * 2 5
 * 1 0 5
 * 1 1 7
 * 1 0 3
 * 2 1 0
 * 2 1 1
 * Sample Output
 * 7
 * 3
 * Explanation
 * Initial Values:
 *
 *
 *  = [ ]
 *  = [ ]
 * Query 0: Append  to sequence .
 *
 *  = [5]
 *  = [ ]
 * Query 1: Append  to sequence .
 *  = [5]
 *  = [7]
 * Query 2: Append  to sequence .
 *
 *  = [5, 3]
 *  = [7]
 * Query 3: Assign the value at index  of sequence  to , print .
 *
 *  = [5, 3]
 *  = [7]
 * 7
 * Query 4: Assign the value at index  of sequence  to , print .
 *
 *  = [5, 3]
 *  = [7]
 * 3
 */
public class DynamicArrays {

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
        List<Integer> res = new ArrayList<>();

        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int count = 0; count < n; count++ ){
            map.put(count, new ArrayList<>());
        }
        int lastAnswer = 0;

        for (int index = 0; index < queries.size(); index++) {
            List<Integer> curr = queries.get(index);
            int type = curr.get(0);

            int seq = (curr.get(1) ^ lastAnswer) % n;
            int value = curr.get(2);

            if (type == 1) {
                map.get(seq).add(value);

            } else {
                value = value % (map.get(seq).size());
                lastAnswer = map.get(seq).get(value);
                res.add(lastAnswer);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int q = scanner.nextInt();

        List<List<Integer>> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {

            List<Integer> queriesRowItems = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                queriesRowItems.add(scanner.nextInt());
            }

            queries.add(queriesRowItems);
        }

        List<Integer> result = dynamicArray(n, queries);

        System.out.println(result);
    }
}
