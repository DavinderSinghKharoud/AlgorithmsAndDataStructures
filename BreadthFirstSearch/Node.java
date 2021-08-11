package BreadthFirstSearch;

public class Node {

    Node left,right;
    int data;
    Node(int data){
        this.data=data;
        left=right=null;
    }
}
