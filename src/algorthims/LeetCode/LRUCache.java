package algorthims.LeetCode;

import java.util.*;


 class LRUCache {
	 class Node {
		 Node prev;
		 Node next;
		 int key;
		 int val;
	 }
	 private Map<Integer, Node> map;
	 private int size;
	 private Node tail;
	 private Node head;

	 public LRUCache(int capacity) {
		 this.map = new HashMap<>();
		 this.size = capacity;
	 }

	 public int get(int key) {
		 Node node = map.get(key);
		 if (node == null) return -1;
		 remove(node);
		 map.put(node.key, append(node.key, node.val));
		 return node.val;
	 }

	 public void put(int key, int value) {
		 if (map.containsKey(key)) {
			 remove(map.get(key));
		 }
		 map.put(key, append(key, value));
		 if (map.size() > size) {
			 map.remove(remove(head));
		 }
	 }

	 private int remove(Node node) {
		 Node prev = node.prev;
		 Node next = node.next;
		 if (prev == null && next == null) {
			 head = null;
			 tail = null;
			 return node.key;
		 }
		 if (prev != null && next != null) {
			 prev.next = next;
			 next.prev = prev;
			 return node.key;
		 }
		 if (prev == null) {
			 next.prev = null;
			 head = next;
		 } else {
			 prev.next = null;
			 tail = prev;
		 }
		 return node.key;
	 }

	 private Node append(int key, int value) {
		 Node node = new Node();
		 node.key = key;
		 node.val = value;
		 if (tail == null) {
			 tail = node;
			 head = node;
			 return node;
		 }
		 tail.next = node;
		 node.prev = tail;
		 tail = node;
		 return node;
	 }


	public static void main (String[] args) {
		    LRUCache lRUCache = new LRUCache( 2 /* capacity */ );

		lRUCache.put(1, 1); // cache is {1=1}
		lRUCache.put(2, 2); // cache is {1=1, 2=2}
		System.out.println(lRUCache.get(1));    // return 1
		lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
		System.out.println(lRUCache.get(2));    // returns -1 (not found)
		lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
		System.out.println(lRUCache.get(1));    // return -1 (not found)
		System.out.println(lRUCache.get(3));    // return 3
		System.out.println(lRUCache.get(4));    // returns 4
	}



}

