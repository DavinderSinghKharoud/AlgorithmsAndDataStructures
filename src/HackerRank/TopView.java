package HackerRank;

import java.util.HashMap;

import java.util.*;

public class TopView {


    public static void main(String[] args) {

        //Node root = new Node(1);
//    	root.right = new Node(2);
//    	root.right.right = new Node(5);
//    	root.right.right.right = new Node(6);
//    	root.right.right.left = new Node(3);
//		root.right.right.left.right = new Node(4);

//		root.right = new Node(3);
//		root.left =new Node(2);
//		root.left.right = new Node(4);
//		root.left.right.right =new Node(5);
//		root.left.right.right.right =new Node(6);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);

        topView(root);
    }

    public static void topView(Node root) {
        if (root == null) return;
        Queue<ObjectNode> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        queue.add(new ObjectNode(root, 0));

        while (!queue.isEmpty()) {
            ObjectNode curr = queue.poll();
            if (!map.containsKey(curr.vertical)) {
                map.put(curr.vertical, curr.node.data);
                min = Math.min(min, curr.vertical);
                max = Math.max(max, curr.vertical);
            }

            if (curr.node.left != null) {
                queue.add(new ObjectNode(curr.node.left, curr.vertical - 1));
            }
            if (curr.node.right != null) {
                queue.add(new ObjectNode(curr.node.right, curr.vertical + 1));
            }
        }
        if (min == max) {
            System.out.print(map.get(min) + " ");
            return;
        }
        for (int count = min; count <= max; count++) {
            System.out.print(map.get(count) + " ");
        }


    }

    static class ObjectNode {
        Node node;
        int vertical;

        public ObjectNode(Node node, int vertical) {
            this.node = node;
            this.vertical = vertical;
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
