package Graphs;

class Node {
    int num;
    String name;
    boolean visited;

    Node(int num, String name){
        this.num = num;
        this.name = name;
        visited = false;
    }

    void visited(){
        visited = true;
    }

    void unvisit(){
        visited = false;
    }

    boolean isVisited(){
        return visited;
    }
}
