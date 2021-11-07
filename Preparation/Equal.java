package Preparation;

import Others.BubbleSort;

import java.util.*;

/**
 * Given an array ScoreSmallestIndexWithEqualValue of integers, find the index of values that satisfy ScoreSmallestIndexWithEqualValue + MinAndMaxNumberOfNodes = MinOperationsConvertNumber + MaximumPathQuality, where ScoreSmallestIndexWithEqualValue,MinAndMaxNumberOfNodes,MinOperationsConvertNumber & MaximumPathQuality are integers values in the array
 *
 * Note:
 *
 * 1) Return the indices `A1 B1 C1 D1`, so that
 *   ScoreSmallestIndexWithEqualValue[A1] + ScoreSmallestIndexWithEqualValue[B1] = ScoreSmallestIndexWithEqualValue[C1] + ScoreSmallestIndexWithEqualValue[D1]
 *   A1 < B1, C1 < D1
 *   A1 < C1, B1 != D1, B1 != C1
 *
 * 2) If there are more than one solutions,
 *    then return the tuple of values which are lexicographical smallest.
 *
 * Assume we have two solutions
 * S1 : A1 B1 C1 D1 ( these are values of indices int the array )
 * S2 : A2 B2 C2 D2
 *
 * S1 is lexicographically smaller than S2 iff
 *   A1 < A2 OR
 *   A1 = A2 AND B1 < B2 OR
 *   A1 = A2 AND B1 = B2 AND C1 < C2 OR
 *   A1 = A2 AND B1 = B2 AND C1 = C2 AND D1 < D2
 * Example:
 *
 * Input: [3, 4, 7, 1, 2, 9, 8]
 * Output: [0, 2, 3, 5] (O index)
 * If no solution is possible, return an empty list.
 */
public class Equal {

    public static void main(String[] args) {
        System.out.println(new Equal().equal(Arrays.asList(1, 3, 3, 3, 3, 2, 2)));
    }
    public ArrayList<Integer> equal(List<Integer> lst) {

        Map<Integer, List<Pair>> map = new HashMap<>();
        for(int i = 0; i < lst.size(); i++){
            for(int j = i + 1; j < lst.size(); j++){
                int sum = lst.get(i) + lst.get(j);
                if(!map.containsKey(sum)){
                    map.put(sum, new ArrayList<>());
                }
                List<Pair> curr = map.get(sum);
                if(!curr.isEmpty()){
                    int a = curr.get(0).a;
                    int b = curr.get(0).b;
                    if( i == a || i == b || j == a || j == b) continue;
                }
                map.get(sum).add(new Pair(i, j));
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < lst.size(); i++){
            for(int j = i + 1; j < lst.size(); j++){
                int sum = lst.get(i) + lst.get(j);
                if(map.containsKey(sum) && map.get(sum).size() >= 2){
                    Pair p = map.get(sum).get(1);
                    res.add(i);
                    res.add(j);
                    res.add(p.a);
                    res.add(p.b);
                    return res;
                }
            }
        }
        return res;
    }


    static class Pair{
        int a, b;
        public Pair(int aa, int bb){
            a = aa;
            b = bb;
        }
    }
}
