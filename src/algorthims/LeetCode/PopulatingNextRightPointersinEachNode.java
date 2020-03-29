import java.util.*;
/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
* Input: root = [1,2,3,4,5,6,7]
Output: [1,#,2,3,#,4,5,6,7,#]
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * */
 
 
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

//Recursive approach
public class PopulatingNextRightPointersinEachNode{
     
    public static Node connect(Node root) {
	Node pointer = root;
	
	while( pointer != null && pointer.left != null ){
	    populateLevel( pointer );
	    pointer = pointer.left;
	}
	
	return root;
        
    }
    
    public static void populateLevel( Node root ){
	
	while( root != null ){
	    root.left.next = root.right;
	    if( root.next != null ) {
		root.right.next = root.next.left;
	    }
	    root = root.next;
	}
    }
    
    
     public static void main(String[] args) {
	 
	 Node root = new Node(1);
	 root.left = new Node(2);
	 root.right = new Node(3);
	 root.left.left = new Node(4);
	 root.left.right = new Node(5);
	 root.right.left = new Node(6);
	 root.right.right = new Node(7);
	 
	 connect( root );
	 
     }
     
     //Using Queue
     public static Node connect2(Node root) {
	 if( root == null) return root;
	Queue<Node> que = new LinkedList<>();
	que.offer( root );
	
	while( !que.isEmpty() ){
	    Node curr = que.poll();
	    int size = que.size();
	    
	    if( curr.left != null ) que.offer( curr.left );
	    if( curr.right != null ) que.offer( curr.right );
	    
	    for( int i = 0; i<size; i++ ){
		Node chil = que.poll();
		curr.next = chil;
		curr = curr.next;
		
	      if( chil.left != null ) que.offer( chil.left );
	      if( chil.right != null ) que.offer( chil.right );
	      
		
	    }
	    
	    curr.next = null;
	}
	return root;
     }
			
}
