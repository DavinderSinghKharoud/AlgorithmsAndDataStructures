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
		 } else if (next == null) {
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
		    LRUCache cache = new LRUCache( 2 /* capacity */ );

		    cache.put(1, 1);
		    cache.put(2, 2);
		System.out.println( cache.get(1) );       // returns 1
		    cache.put(3, 3);    // evicts key 2
		System.out.println(cache.get(2));      // returns -1 (not found)
		    cache.put(4, 4);    // evicts key 1
		System.out.println(cache.get(1));      // returns -1 (not found)
		System.out.println(cache.get(3));     // returns 3
		System.out.println(cache.get(4));      // returns 4
	}



}

