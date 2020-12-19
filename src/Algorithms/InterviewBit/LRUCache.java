package Algorithms.InterviewBit;

import java.util.*;

/**
 * Design and implement a data structure for LRU (Least Recently Used) cache. It should support the following operations: get and set.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least recently used item before inserting the new item.
 * The LRU Cache will be initialized with an integer corresponding to its capacity. Capacity indicates the maximum number of unique keys it can hold at a time.
 *
 * Definition of “least recently used” : An access to an item is defined as a get or a set operation of the item. “Least recently used” item is the one with the oldest access time.
 *
 *  NOTE: If you are using any global variables, make sure to clear them in the constructor.
 * Example :
 *
 * Input :
 *          capacity = 2
 *          set(1, 10)
 *          set(5, 12)
 *          get(5)        returns 12
 *          get(1)        returns 10
 *          get(10)       returns -1
 *          set(6, 14)    this pushes out key = 5 as LRU is full.
 *          get(5)        returns -1
 */
public class LRUCache {

    Map<Integer, Integer> map;
    Map<Integer, Node> mapedNodes;
    Node head;
    Node end;
    int capacity;
    
    public LRUCache(int capacity) {
		map = new HashMap<>();
		mapedNodes = new HashMap<>();
		this.capacity = capacity;
    }

    public int get(int key) {
		
		if( mapedNodes.containsKey(key) ){
			updateList( mapedNodes.get(key) );
			return map.get(key);
		}
		
		return -1;
    }

	public void updateList( Node update){
		Node previous = update.previous;
		Node successor = update.next;
		
		// If it is a starting Node
		if( previous == null ){
			return;
		}else if( successor == null ){
			//If it is a ending Node
			
			previous.next = null;
			end = previous;

		}else{
			
			//If it is in a middle 
			
			previous.next = successor;
			successor.previous = previous;
		}
					
			update.next = head;
			head.previous = update;
			update.previous = null;
			head = update;
	}
	
    public void set(int key, int value) {
		
		Node curr = new Node(key);

		if( map.containsKey(key)){
			updateList(mapedNodes.get(key));
			map.put(key, value);
			return;
		}
		if(  map.size() >=  capacity){
			int keyToRemove = removeFromList();
			map.remove(keyToRemove);
			mapedNodes.remove(keyToRemove);
		}
		
		map.put(key, value);
		mapedNodes.put(key, curr);
		curr.next = head;
		if( head != null ){	
			head.previous = curr;
		}
		head = curr;
		if( end == null ){
			end = curr;
		}
    }

	public int removeFromList( ){
		Node previous = end.previous;
		int val = end.val;
		if( previous == null ){
			head = null;
			end = null;
			return val;
		}
		
		previous.next = null;
		end = previous;
		return val;
	}

	public Node printList(){
    	return head;
	}
    public static void main(String[] args) {

    	LRUCache lRUCache = new LRUCache(2);
		System.out.println(lRUCache.get(2));
		lRUCache.set(2, 6);
		System.out.println(lRUCache.get(1));
		lRUCache.set(1, 5);
		lRUCache.set(1, 2);
		System.out.println(lRUCache.get(1));
		System.out.println(lRUCache.get(2));

	}
    
      static class Node{
		
		Node previous;
		Node next;
		int val;
		
		public Node( int val ){
			this.val = val;
		}
	}
}
