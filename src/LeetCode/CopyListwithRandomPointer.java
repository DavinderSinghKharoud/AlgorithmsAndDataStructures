package LeetCode;

import java.util.*;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.
* */
public class CopyListwithRandomPointer {
	
	//O(n) time and space complexity
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
	    
	    
	    Node res = copyRandomList2( root );
	    
	    while( res != null ){
		System.out.println( res.val );
        res = res.next;

		}
		
	}


	//O(n) time complexity and O(1) constant space time complexity
	public static Node copyRandomList2(Node head) {

		//setting up duplicate nodes in between
		if (head == null) return head;
		Node curr = head;
		while (curr != null) {
			Node node = new Node(curr.val);
			Node next = curr.next;
			curr.next = node;
			node.next = next;
			curr = next;
		}

		//setting up random for duplicates
		Node p = head;
		while (p != null) {
			curr = p.next;
			curr.random = p.random == null ? null : p.random.next;
			p = curr.next;
		}

		//removing the duplicate list of nodes from the modified
		Node res = new Node(-1), prev = res;
		p = head;
		while (p != null) {
			curr = p.next;
			prev.next = curr;
			prev = curr;
			p.next = p.next.next;
			p = p.next;
		}
		return res.next;
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

