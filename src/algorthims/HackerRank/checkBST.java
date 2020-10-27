package algorthims.HackerRank;

public class checkBST {

    //O(n) time and space(due to recursion) complexity
    static boolean checkBST(Node root) {

        return check(root, null, null);
    }

    private static boolean check(Node node, Integer lower, Integer upper) {

        if (node == null) return true;

        int val = node.data;

        //Check if current node value is inBetween lower and upper
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!check(node.left, lower, val)) return false;

        return check(node.right, val, upper);
    }

    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(1);
        root.left.right = new Node(4);
        root.right.right = new Node(6);

        System.out.println(checkBST(root));
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
