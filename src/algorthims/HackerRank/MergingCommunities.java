package algorthims.HackerRank;

import java.util.*;

/**
 * People connect with each other in a social network. A connection between Person  and Person  is represented as . When two persons belonging to different communities connect, the net effect is the merger of both communities which  and  belongs to.
 * At the beginning, there are  people representing  communities. Suppose person  and  connected and later  and connected, then ,, and  will belong to the same community.
 * There are two type of queries:
 *  communities containing person  and  merged (if they belong to different communities).
 *  print the size of the community to which person  belongs.
 * Input Format
 * The first line of input will contain integers  and , i.e. the number of people and the number of queries.
 * The next  lines will contain the queries.
 * Constraints :
 *
 *
 * Output Format
 * The output of the queries.
 * Sample Input
 * 3 6
 * Q 1
 * M 1 2
 * Q 2
 * M 2 3
 * Q 3
 * Q 2
 * Sample Output
 * 1
 * 2
 * 3
 * 3
 * Explanation
 * Initial size of each of the community is .
 */
public class MergingCommunities {

    //Time complexity O(LogN) and Space complexity number of people
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int numOfPeople = sc.nextInt();

        Map<Integer, Node> map = new HashMap<>();
        for (int count = 1; count <= numOfPeople; count++) {
            map.put(count, new Node());
        }

        int queries = sc.nextInt();

        for (int index = 0; index < queries; index++) {
            String type = sc.next();
            if (type.equals("M")) {
                int num1 = sc.nextInt();
                int num2 = sc.nextInt();

                if (!map.containsKey(num1) || !map.containsKey(num2)) continue;
                Node parent1 = getParent(map.get(num1));
                Node parent2 = getParent(map.get(num2));

                if (parent1 == parent2) continue;
                if (parent1.count > parent2.count) {

                    parent2.parent = parent1;
                    parent1.count += parent2.count;
                    parent2.count = 0;
                } else {
                    parent1.parent = parent2;
                    parent2.count += parent1.count;
                    parent1.count = 0;
                }

            } else { //If it is Q
                int num = sc.nextInt();
                Node parent = getParent(map.get(num));
                System.out.println(parent.count);
            }
        }
    }

    public static Node getParent(Node node) {
        Node parent = node.parent;
        if (parent != node) {
            node.parent = getParent(node.parent);
            return node.parent;
        }
        return parent;

    }

    public static class Node {
        Node parent;
        int count;

        public Node() {
            parent = this;
            count = 1;
        }
    }
}
