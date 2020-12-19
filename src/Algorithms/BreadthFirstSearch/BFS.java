package Algorithms.BreadthFirstSearch;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class BFS {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter the height of the tree: ");
        int T=sc.nextInt();
        Node root=null;
        while(T-->0){
            System.out.print("Enter the node data: ");
            int data=sc.nextInt();
            root=insert(root,data);
        }
        levelOrder(root);
    }

    //Implementing Breadth First Search
    private static void levelOrder(Node root) {
        //creating queue
        Queue queue = new ArrayDeque();

        //Adding first Node
        queue.add(root);

        while (!queue.isEmpty()){
            Node nodeOut = (Node) queue.remove();
            System.out.print(nodeOut.data+" ");

            if(nodeOut.left != null) {
                queue.add(nodeOut.left);
            }
            if(nodeOut.right != null){
                queue.add(nodeOut.right);
            }

        }


    }

    public static Node insert(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        else{
            Node cur;
            if(data<=root.data){
                cur=insert(root.left,data);
                root.left=cur;
            }
            else{
                cur=insert(root.right,data);
                root.right=cur;
            }
            return root;
        }
    }
}
