package InterviewBit;

/**
 * There are A islands and there are M bridges connecting them. Each bridge has some cost attached to it.
 * <p>
 * We need to find bridges with minimal cost such that all islands are connected.
 * <p>
 * It is guaranteed that input data will contain at least one possible scenario in which all islands are connected with each other.
 * <p>
 * Input Format:
 * <p>
 * The first argument contains an integer, A, representing the number of islands.
 * The second argument contains an 2-d integer matrix, B, of size M x 3:
 * => Island B[i][0] and B[i][1] are connected using a bridge of cost B[i][2].
 * Output Format:
 * <p>
 * Return an integer representing the minimal cost required.
 * Constraints:
 * <p>
 * 1 <= A, M <= 6e4
 * 1 <= B[i][0], B[i][1] <= A
 * 1 <= B[i][2] <= 1e3
 * Examples:
 * <p>
 * Input 1:
 * A = 4
 * B = [   [1, 2, 1]
 * [2, 3, 4]
 * [1, 4, 3]
 * [4, 3, 2]
 * [1, 3, 10]  ]
 * <p>
 * Output 1:
 * 6
 * <p>
 * Explanation 1:
 * We can choose bridges (1, 2, 1), (1, 4, 3) and (4, 3, 2), where the total cost incurred will be (1 + 3 + 2) = 6.
 * <p>
 * Input 2:
 * A = 4
 * B = [   [1, 2, 1]
 * [2, 3, 2]
 * [3, 4, 4]
 * [1, 4, 3]   ]
 * <p>
 * Output 2:
 * 6
 * <p>
 * Explanation 2:
 * We can choose bridges (1, 2, 1), (2, 3, 2) and (1, 4, 3), where the total cost incurre
 */

import java.util.*;

public class CommutableIslands_Prims_Kruskal_Union_Disjoint_Sets {

    //O(V square) time complexity and O( E * V ) space complexity ( Prims )
    public static int solve(int numOfNodes, ArrayList<ArrayList<Integer>> lst) {

        Map<Integer, ArrayList<Pair>> map = new HashMap<>();

        for (ArrayList<Integer> curr : lst) {
            ArrayList<Pair> childrens = map.getOrDefault(curr.get(0), new ArrayList<>());
            childrens.add(new Pair(curr.get(1), curr.get(2)));
            map.put(curr.get(0), childrens);

            childrens = map.getOrDefault(curr.get(1), new ArrayList<>());
            childrens.add(new Pair(curr.get(0), curr.get(2)));
            map.put(curr.get(1), childrens);

        }

        Set<Integer> set = new HashSet<>();
        int res = 0;
        set.add(lst.get(0).get(0));

        while (set.size() != numOfNodes) {

            int[] min = findMin(set, map);

            set.add(min[0]);
            res += min[1];

        }
        return res;
    }

    private static int[] findMin(Set<Integer> set, Map<Integer, ArrayList<Pair>> map) {
        int[] min = new int[2];
        Arrays.fill(min, Integer.MAX_VALUE);
        for (int num : set) {
            ArrayList<Pair> curr = map.get(num);

            for (Pair pair : curr) {
                if (!set.contains(pair.node)) {
                    if (min[0] == Integer.MAX_VALUE || pair.weight < min[1]) {
                        min[0] = pair.node;
                        min[1] = pair.weight;
                    }
                }
            }

        }

        return min;
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(2);
        lst1.add(1);
        res.add(lst1);

        ArrayList<Integer> lst2 = new ArrayList<>();
        lst2.add(2);
        lst2.add(3);
        lst2.add(4);
        res.add(lst2);
        ArrayList<Integer> lst3 = new ArrayList<>();
        lst3.add(1);
        lst3.add(4);
        lst3.add(3);
        res.add(lst3);
        ArrayList<Integer> lst4 = new ArrayList<>();
        lst4.add(4);
        lst4.add(3);
        lst4.add(2);
        res.add(lst4);
        ArrayList<Integer> lst5 = new ArrayList<>();
        lst5.add(1);
        lst5.add(3);
        lst5.add(10);
        res.add(lst5);

        System.out.println(solve2(4, res));
    }

    //O(E Log V) time complexity and O( E * V ) space complexity ( Prims )
    public static int solve2(int numOfNodes, ArrayList<ArrayList<Integer>> lst) {

        int cost = 0;
        Map<Integer, ArrayList<Pair>> map = new HashMap<>();

        for (ArrayList<Integer> curr : lst) {
            ArrayList<Pair> childrens = map.getOrDefault(curr.get(0), new ArrayList<>());
            childrens.add(new Pair(curr.get(1), curr.get(2)));
            map.put(curr.get(0), childrens);

            childrens = map.getOrDefault(curr.get(1), new ArrayList<>());
            childrens.add(new Pair(curr.get(0), curr.get(2)));
            map.put(curr.get(1), childrens);

        }

        boolean[] visited = new boolean[numOfNodes];
        visited[lst.get(0).get(0) - 1] = true;

        PriorityQueue<Pair> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.weight)));

        pq.addAll(map.get(lst.get(0).get(0)));

        while (!pq.isEmpty()) {
            Pair p = pq.poll();

            if (visited[p.node - 1]) continue;

            visited[p.node - 1] = true;
            cost += p.weight;

            pq.addAll(map.get(p.node));

        }

        return cost;
    }
    
    //Krushkal ( O( E * log_V ) time complexity and O(E) space complexity )
    //O(log V) time complexity and O( V ) space complexity for Union Disjoint sets
    public static int solve3(int numOfNodes, ArrayList<ArrayList<Integer>> lst) {

        PriorityQueue<ArrayList<Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.get(2)));
        pq.addAll(lst);


        Map<Integer, Node> map = new HashMap<>(); // to store the nodes for union - set
        makeSet(map, numOfNodes);

        int res = 0;
        int countOfEdges = 0;
        while (countOfEdges != numOfNodes - 1) {

            ArrayList<Integer> curr = pq.poll();

            Node parent1 = getParent(map.get(curr.get(0)));
            Node parent2 = getParent(map.get(curr.get(1)));

            if (parent1 == parent2) { //We should not include as it will create a cycle
                continue;
            }

            if (parent1.rank >= parent2.rank) {
                parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
                parent2.parent = parent1;
            } else {
                parent1.parent = parent2;
            }

            res += curr.get(2);
            countOfEdges++;
        }

        return res;

    }

    public static Node getParent(Node node) {
        Node parent = node.parent;
        if (parent == node) return parent; // As it is a absolute parent

        node.parent = getParent(parent); //Comressing the path while finding the parent node
        return node.parent;
    }

    public static void makeSet(Map<Integer, Node> map, int numOfNodes) {

        for (int count = 1; count <= numOfNodes; count++) {
            Node curr = new Node();
            curr.data = count;
            curr.rank = 0;
            curr.parent = curr;

            map.put(count, curr); //Add the node in to Map
        }
    }

    static class Node {
        int data;
        int rank;
        Node parent;

        public Node() {
        }
    }

    static class Pair {
        int node;
        int weight;

        public Pair(int node, int weight) {
            this.node = node;
            this.weight = weight;

        }
    }
}
