package algorthims.InterviewBit;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 *  A height balanced BST : a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example :
 *
 *
 * Given A : 1 -> 2 -> 3
 * A height balanced BST  :
 *
 *       2
 *     /   \
 *    1     3
 */
public class ConvertSortedListToBinaryTree {

    public static void main(String[] args) {

        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(3);
        root.next.next.next = new ListNode(4);
        root.next.next.next.next = new ListNode(5);
        root.next.next.next.next.next = new ListNode(6);
        root.next.next.next.next.next.next = new ListNode(7);
        TreeNode tree = sortedListToBST(root);

        inOrder(tree);

    }

    static void inOrder ( TreeNode node){

        if( node == null ){
            return;
        }

        inOrder( node.left );
        System.out.print( node.val + " ");
        inOrder( node.right );
    }

    public static TreeNode sortedListToBST(ListNode root) {
		
		ListNode middle = getMiddle( root );
		ListNode otherHalf = middle.next;
		
		middle.next = null;
		
		TreeNode node = new TreeNode(middle.val);
		
		node.left = ( root == middle ) ? null: sortedListToBST( root );
		node.right = ( otherHalf == null ) ? null: sortedListToBST( otherHalf );
		
		return node;
    }


	public static ListNode getMiddle( ListNode root ){
		ListNode start = root;
		ListNode end = root;
		ListNode previous = null;
		
		while( end.next != null && end.next.next != null ){
			previous = start;
			start = start.next;
			end = end.next.next;
		}

		if( previous != null ) previous.next = null;
		return start;
	}
	
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
