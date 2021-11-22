package Preparation;

import java.util.*;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
public class LRUCache {


    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));
        ;    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));     // return 4

        LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap<>(10, 0.75f, true);
        linkedHashMap.put(1, 1);
        linkedHashMap.put(2, 2);
        linkedHashMap.put(3, 3);
        linkedHashMap.put(4, 4);

        linkedHashMap.get(2);
        for (Map.Entry<Integer, Integer> l : linkedHashMap.entrySet()) {
            System.out.println(l.getKey());
        }
    }

//    LruMap map;
//    public LRUCache(int capacity) {
//        map = new LruMap(capacity);
//    }
//
//    public int get(int key) {
//        return map.getOrDefault(key, -1);
//    }
//
//    public void put(int key, int value) {
//        map.put(key, value);
//    }
//
//    static class LruMap extends LinkedHashMap<Integer, Integer>{
//        static final float loadFactor = 0.75f;
//        int capacity;
//        public LruMap(int capacity){
//            super(capacity, loadFactor, true);
//            this.capacity = capacity;
//        }
//
//        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){
//            return size() > capacity;
//        }
//    }

    int rank;
    Map<Integer, Node> map;
    TreeMap<Integer, Integer> sortedMap;
    int capacity;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        sortedMap = new TreeMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);

        //Change rank
        sortedMap.remove(node.rank);
        sortedMap.put(++rank, key);
        node.rank = rank;
        map.put(key, node);

        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //Remove the rank from sorted
            sortedMap.remove(map.get(key).rank);
        }
        ++rank;
        System.out.println(rank + " " + key);
        map.put(key, new Node(value, rank));
        sortedMap.put(rank, key);

        //Remove if size is greater than intial capacity
        checkSize();
    }

    void checkSize() {
        if (map.size() > capacity) {
            //Remove the least recently used
            Map.Entry<Integer, Integer> entry = sortedMap.firstEntry();
            sortedMap.remove(entry.getKey());
            System.out.println("Remove " + entry.getKey() + " " + entry.getValue());
            map.remove(entry.getValue());

        }
    }

    static class Node {
        int val, rank;

        public Node(int val, int rank) {
            this.val = val;
            this.rank = rank;
        }
    }

    //Using Linked List and Map
    /*******************************/
//    class Node {
//        Node prev;
//        Node next;
//        int key;
//        int val;
//    }
//    private Map<Integer, Node> map;
//    private int size;
//    private Node tail;
//    private Node head;
//
//    public LRUCache(int capacity) {
//        this.map = new HashMap<>();
//        this.size = capacity;
//    }
//
//    public int get(int key) {
//        Node node = map.get(key);
//        if (node == null) return -1;
//        remove(node);
//        map.put(node.key, append(node.key, node.val));
//        return node.val;
//    }
//
//    public void put(int key, int value) {
//        if (map.containsKey(key)) {
//            remove(map.get(key));
//        }
//        map.put(key, append(key, value));
//        if (map.size() > size) {
//            map.remove(remove(head));
//        }
//    }
//
//    private int remove(Node node) {
//        Node prev = node.prev;
//        Node next = node.next;
//        if (prev == null && next == null) {
//            head = null;
//            tail = null;
//            return node.key;
//        }
//        if (prev != null && next != null) {
//            prev.next = next;
//            next.prev = prev;
//            return node.key;
//        }
//        if (prev == null) {
//            next.prev = null;
//            head = next;
//        } else if (next == null) {
//            prev.next = null;
//            tail = prev;
//        }
//        return node.key;
//    }
//
//    private Node append(int key, int value) {
//        Node node = new Node();
//        node.key = key;
//        node.val = value;
//        if (tail == null) {
//            tail = node;
//            head = node;
//            return node;
//        }
//        tail.next = node;
//        node.prev = tail;
//        tail = node;
//        return node;
//    }


}
