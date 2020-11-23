package algorithms.LeetCode;

import java.util.*;

/**
 * Design a data structure that supports all following operations in average O(1) time.
 * <p>
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements.
 * Each element must have the same probability of being returned.
 */
class RandomizedSet {

    private HashMap<Integer, Integer> map;
    private List<Integer> lst ;
    private int count;
    private Random random;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        map = new HashMap<>();
        lst = new ArrayList<>();
        count = -1;
        random = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {

        if( !map.containsKey(val)){
            count++;
            map.put( val, count);
            lst.add( val);
            return true;
        }
        return false;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if( map.containsKey(val)){
            int index = map.get(val);
            int lastNum = lst.get(lst.size() - 1);
            Collections.swap(lst,index, lst.size() - 1);
            map.put( lastNum, index);
            map.remove(val);
            lst.remove(lst.size() - 1);
            count--;
            return true;
        }
        return false;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int index = random.nextInt(lst.size());
        return lst.get(index);

    }
}

public class InsertDeleteGetRandom {

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println( obj.insert(1));
        System.out.println(obj.remove(1));
        System.out.println(obj.insert(2));
        System.out.println(obj.insert(3));
        System.out.println(obj.insert(4));
        System.out.println(obj.insert(5));
        System.out.println( obj.getRandom());


    }

}
