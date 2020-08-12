package algorthims.InterviewBit;

import java.util.*;

public class CloneGraph {

    public static UndirectedGraphNode traverse(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {

        if( map.containsKey(node) ) {
            return map.get(node);
        }else {
            UndirectedGraphNode curr = new UndirectedGraphNode(node.label);
            map.put(node, curr);

            for (UndirectedGraphNode temp : node.neighbors) {
                curr.neighbors.add(traverse(temp, map));
            }
            return curr;
        }

    }

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node){
        if( node == null ) return node;

        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();

        return traverse(node, map);
    }
    public static void main(String[] args) {

        UndirectedGraphNode root = new UndirectedGraphNode(1);
        UndirectedGraphNode two = new UndirectedGraphNode(2);
        two.neighbors.add(new UndirectedGraphNode(4));
        two.neighbors.add(new UndirectedGraphNode(5));
        root.neighbors.add(two);
        root.neighbors.add(new UndirectedGraphNode(3));

        UndirectedGraphNode curr = cloneGraph(root);

        System.out.println(curr.label);
        for(UndirectedGraphNode temp: curr.neighbors ){
            System.out.println(temp.label);
        }

    }

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }
    }

    ;
}
