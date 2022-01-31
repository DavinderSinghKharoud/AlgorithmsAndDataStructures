package Preparation;

public class FlattenMutlilevelDoublyLinkedList {

    Node root;

    public Node flatten(Node head) {
        if (head == null) return null;
        root = new Node();
        Node ans = root;

        helper(head);
        // System.out.println(ans.next.val);
        if (ans.next != null) ans.next.prev = null;
        return ans.next;
    }

    public void helper(Node node) {
        if (node == null) return;

        Node next = node.next;

        //Set the pointers for global
        root.next = node;
        node.prev = root;
        root = node;

        if (node.child != null) {
            helper(node.child);
            node.child = null;
        }
        helper(next);
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}
