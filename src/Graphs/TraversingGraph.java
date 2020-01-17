package Graphs;


import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class TraversingGraph {

    public static void main(String[] args) {

        Graph graph = new Graph(false);
        Node a = new Node(0, "0");
        Node b = new Node(1, "1");
        Node c = new Node(2, "2");
        Node d = new Node(3, "3");
        Node e = new Node(4, "4");

        graph.addEdge(a,d);
        graph.addEdge(a,b);
        graph.addEdge(a,c);
        graph.addEdge(c,d);
        graph.addEdge(d,e);

        //Printing Edges
        graph.printEdges();

        //BreadthFirstSearch
        System.out.print("Breadth First Search: ");
        graph.breadthFirstSearchModified( a );
    }


}
