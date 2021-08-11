//package algorthims.LeetCode;
//import java.util.*;
//
//public class FirstUniqueNumber {
//
//	static class Node{
//
//		Node next;
//		Node prev;
//		int val;
//
//		public Node( int val ){
//			this.val = val;
//		}
//	}
//
//
//	HashMap<Integer, Node> map = new HashMap<>();
//	Node head;
//	Node tail;
//
//
//    public FirstUniqueNumber(int[] nums) {
//
//		Node node = new Node( nums[0] );
//		map.put( nums[0], node );
//
//		head = node;
//		tail = node;
//
//
//		for( int i = 1; i<nums.length; i++ ){
//			if( map.containsKey( nums[i] )){
//				remove( map.get( nums[i] ));
//				continue;
//
//			}
//
//			Node next_node = new Node( nums[i] );
//			map.put( nums[i], next_node );
//			add_node( tail, next_node );
//
//		}
//
//
//    }
//
//    public static void remove( Node node ){
//
//	}
//
//	public static void add_node( Node tail, Node node ){
//
//	}
//
//    public static int showFirstUnique() {
//
//    }
//
//    public static void add(int value) {
//
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
