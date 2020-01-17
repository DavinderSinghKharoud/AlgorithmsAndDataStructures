package Graphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    //Each node has list of neighbours
    private HashMap<Node, LinkedList<Node>> adjacencyMap;
    private boolean directed;

    public Graph(boolean directed) {

        this.directed = directed;
        adjacencyMap = new HashMap<>();
    }

    //We also need to check for possible duplicate edges
    public void addEdgeHelper(Node a, Node b) {

        LinkedList<Node> tmp = adjacencyMap.get(a);

        if (tmp != null) {
            tmp.remove(b);
        } else {
            tmp = new LinkedList<>();
        }

        tmp.add(b);
        adjacencyMap.put(a, tmp);
    }

    //Add edge
    public void addEdge(Node source, Node destination) {

        //Check if nodes exist or not, if not then add
        if (!adjacencyMap.keySet().contains(source)) {
            adjacencyMap.put(source, null);
        }
        if (!adjacencyMap.keySet().contains(destination)) {
            adjacencyMap.put(destination, null);
        }

        addEdgeHelper(source, destination);

        //If the graph is undirected, we need to add an edge from destination to source
        if (!directed) {
            addEdgeHelper(destination, source);
        }
    }

    public void printEdges() {

        for (Node node : adjacencyMap.keySet()) {
            System.out.println("Node: " + node.name);
            for (Node neighbour : adjacencyMap.get(node)) {
                System.out.println(neighbour.name + " ");
            }
            System.out.println(" ");
        }

    }

    public boolean hasEdge(Node source, Node destination) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source).contains(destination);
    }

    //Breadth first Search
    private void breadthFirstSearch(Node node) {

        //If node is null
        if (node == null) {
            return;
        }

        //Add the node to the queue
        Queue queue = new ArrayDeque();
        queue.add(node);

        while (!queue.isEmpty()) {

            Node currentNode = (Node) queue.remove();

            if (currentNode.isVisited()) {
                continue;
            } else {
                currentNode.visited();
            }

            System.out.print(currentNode.name + " ");

            LinkedList<Node> allNeighbours = adjacencyMap.get(currentNode);

            for (Node neighbours : allNeighbours) {

                if (!neighbours.isVisited()) {
                    queue.add(neighbours);
                }

            }


        }


    }

    //For the unconnected graph
    void breadthFirstSearchModified(Node node) {

        breadthFirstSearch( node );

        for( Node n : adjacencyMap.keySet() ){

            if(!n.isVisited()){

                breadthFirstSearch( node );
            }
        }


    }


    //Depth First Traversal
    void DepthFirstSearch( Node node ){

        node.visited();
        System.out.print( node.name +" " );

        LinkedList<Node> allNeighbors = adjacencyMap.get(node);

        if( allNeighbors == null){
            return;
        }

        for( Node neighbour: allNeighbors ){

            if( !neighbour.isVisited() ){
                DepthFirstSearch( neighbour );
            }
        }

    }
}
