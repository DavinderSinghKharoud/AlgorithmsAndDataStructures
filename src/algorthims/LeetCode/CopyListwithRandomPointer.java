package algorthims.LeetCode;

import java.util.*;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
* */
public class CopyListwithRandomPointer {
	
	
	public static Node copyRandomList(Node head) {
	    
	    Node root = head;
	    Map<Node, Node> map = new HashMap<>();
	    
		while( root != null ){
			Node newNode = new Node(root.val);
			map.put( root, newNode );
			root = root.next;
		}
	    
		root = head;
		Node node = new Node(0);
		Node ref = node;

		node.next = ( root != null ) ? map.get( root ) : null;;
		node = node.next;
		while( root != null ){

			Node n1 = ( root.next != null ) ? map.get( root.next ) : null;
			Node n2 = ( root.random != null ) ? map.get( root.random ) : null;
		
			node.next = n1;
			node.random = n2;
		
			root = root.next;
			node = node.next;
		}
		
		return ref.next;
	}
	
	public static void main (String[] args) {
	    
		Node root = new Node(3);
		root.next = new Node(2);
		root.next.next = new Node(4);
	    
	    root.next.random = root;
	    
	    
	    Node res = copyRandomList( root );
	    
	    while( res != null ){
		System.out.println( res.val );
        res = res.next;

		}
		
	}
	
	    
	static class Node {
	int val;
	Node next;
	Node random;

	public Node(int val) {
	    this.val = val;
	    this.next = null;
	    this.random = null;
	}
	}

}

