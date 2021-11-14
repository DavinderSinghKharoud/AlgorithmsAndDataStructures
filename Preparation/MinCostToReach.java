package Preparation;

import java.util.*;

/**
 * There is a country of n cities numbered from 0 to n - 1 where all the cities are connected by bi-directional roads. The roads are represented as a 2D integer array edges where edges[i] = [xi, yi, timei] denotes a road between cities xi and yi that takes timei minutes to travel. There may be multiple roads of differing travel times connecting the same two cities, but no road connects a city to itself.
 *
 * Each time you pass through a city, you must pay a passing fee. This is represented as a 0-indexed integer array passingFees of length n where passingFees[j] is the amount of dollars you must pay when you pass through city j.
 *
 * In the beginning, you are at city 0 and want to reach city n - 1 in maxTime minutes or less. The cost of your journey is the summation of passing fees for each city that you passed through at some moment of your journey (including the source and destination cities).
 *
 * Given maxTime, edges, and passingFees, return the minimum cost to complete your journey, or -1 if you cannot complete it within maxTime minutes.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: maxTime = 30, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 11
 * Explanation: The path to take is 0 -> 1 -> 2 -> 5, which takes 30 minutes and has $11 worth of passing fees.
 * Example 2:
 *
 *
 *
 * Input: maxTime = 29, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: 48
 * Explanation: The path to take is 0 -> 3 -> 4 -> 5, which takes 26 minutes and has $48 worth of passing fees.
 * You cannot take path 0 -> 1 -> 2 -> 5 since it would take too long.
 * Example 3:
 *
 * Input: maxTime = 25, edges = [[0,1,10],[1,2,10],[2,5,10],[0,3,1],[3,4,10],[4,5,15]], passingFees = [5,1,2,20,20,3]
 * Output: -1
 * Explanation: There is no way to reach city 5 from city 0 within 25 minutes.
 */
public class MinCostToReach {

    public static void main(String[] args) {
        System.out.println(new MinCostToReach().minCost(
                30, new int[][]{{0, 1, 10}, {1, 2, 10}, {2, 5, 10}, {0, 3, 1}, {3, 4, 10}, {4, 5, 15}},
                new int[]{5, 1, 2, 20, 20, 3}
        ));
    }
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        int len = passingFees.length;
        ArrayDeque<Node>[] tree = new ArrayDeque[len + 1];
        for(int[] edge: edges){
            int a = edge[0], b = edge[1], time = edge[2];
            if(tree[a] == null) tree[a] =  new ArrayDeque<>();
            if(tree[b] == null) tree[b] =  new ArrayDeque<>();
            tree[a].add(new Node(b, time));
            tree[b].add(new Node(a, time));
        }
        int[] fees = new int[len], time = new int[len];
        Arrays.fill(fees, Integer.MAX_VALUE);
        Arrays.fill(time, Integer.MAX_VALUE);
        time[0] = 0;
        fees[0] = passingFees[0];
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) ->{
            int diff = Integer.compare(o1.totalcost, o2.totalcost);
            if(diff != 0 ) return diff;
            return Integer.compare(o1.totalTime, o2.totalTime);
        });

        pq.add(new Node(0, 0, fees[0]));
        while(pq.size() != 0){
            Node curr = pq.poll();
            if(curr.id == len - 1) return curr.totalcost;
            //System.out.println(fees[curr.id] + " " + curr.totalcost);
            if(fees[curr.id] != curr.totalcost) continue;
            if(tree[curr.id] != null){
                for(Node neigh: tree[curr.id]){
                    if( curr.totalTime + neigh.time <= maxTime ){

                        int cost = curr.totalcost + passingFees[neigh.id];
                        //If cost can be decreased
                        if(fees[neigh.id] > cost){
                            fees[neigh.id] = cost;
                            Node newNode = new Node(neigh.id, neigh.time, cost);
                            newNode.totalTime = curr.totalTime + neigh.time;
                            pq.add(newNode);
                        }else if(time[neigh.id] > curr.totalTime + neigh.time){ //time can be decreased
                            fees[neigh.id] = cost;
                            time[neigh.id] = curr.totalTime + neigh.time;
                            Node newNode = new Node(neigh.id, neigh.time, cost);
                            newNode.totalTime = curr.totalTime + neigh.time;
                            pq.add(newNode);
                        }
                    }
                }
            }
        }

        return (fees[len - 1] != Integer.MAX_VALUE? fees[len - 1]: -1);
    }

    static class Node{
        int time, id;
        int totalcost;
        int totalTime;
        public Node( int id, int time){
            this.id = id;
            this.time = time;
        }
        public Node( int id, int time, int totalcost){
            this.id = id;
            this.time = time;
            this.totalcost = totalcost;
        }
    }
}
