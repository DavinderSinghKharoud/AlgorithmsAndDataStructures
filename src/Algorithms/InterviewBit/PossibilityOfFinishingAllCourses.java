package Algorithms.InterviewBit;

import java.util.*;

public class PossibilityOfFinishingAllCourses {

    //O(n) time and space complexity ( Breadth first search )
    public static int solve(int countNodes, ArrayList<Integer> pre_requisite, ArrayList<Integer> top) {

        int[] dp = new int[countNodes];
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int num : top) { //Find the number of pre-requisites
            dp[num - 1]++;
        }

        for (int index = 0; index < top.size(); index++) {
            ArrayList<Integer> curr = map.getOrDefault(pre_requisite.get(index), new ArrayList<>());
            curr.add(top.get(index));
            map.put(pre_requisite.get(index), curr);
        }

        for (int index = 0; index < dp.length; index++) { //Find the courses that have no pre-requisites, as can be used as starting points
            if (dp[index] == 0) queue.add(index + 1);
        }

        if (queue.size() == 0) return 0; //No starting point found

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            ArrayList<Integer> lst = map.get(curr);
            if (lst == null || lst.size() == 0) continue;
            for (int num : lst) {
                dp[num - 1]--;
                if (dp[num - 1] == 0) { //If all the pre-requisites are satisfied
                    queue.add(num);
                }
            }
        }

        for (int num : dp) {
            if (num != 0) return 0;
        }

        return 1;
    }

    public static void main(String[] args) {

        ArrayList<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(1);
        lst1.add(2);//lst1.add(5);

        ArrayList<Integer> lst2 = new ArrayList<>();
        lst2.add(2);
        lst2.add(3);
        lst2.add(3);//lst2.add(3);

        System.out.println(solve2(5, lst1, lst2));
    }

    public static int solve2(int countNodes, ArrayList<Integer> pre_requisite, ArrayList<Integer> top) {

        if (countNodes == 0 || pre_requisite.size() == 0) return 0;

        int[] visited = new int[countNodes];

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int index = 0; index < pre_requisite.size(); index++) {

            List<Integer> curr = map.getOrDefault(pre_requisite.get(index), new ArrayList<>());
            curr.add(top.get(index));
            map.put(pre_requisite.get(index), curr);

        }

        for (int num : pre_requisite) {
            if (!canDFS(map, num, visited)) {
                return 0;
            }
        }

        return 1;

    }

    //0 means unvisited, 1 means already processed, 2 means currently in processing
    public static boolean canDFS(Map<Integer, List<Integer>> map, int num, int[] visited) {

        if (visited[num - 1] == 2) { //Currently in processing and will make a cycle
            return false;
        }

        if (visited[num - 1] == 1) { //It meanse already processed
            return true;
        }

        visited[num - 1] = 2; //Set currently in processing

        List<Integer> curr = map.get(num);
        if (curr != null && curr.size() != 0) {
            for (int course : curr) {
                if (!canDFS(map, course, visited)) {
                    return false;

                }
            }
        }


        visited[num - 1] = 1; //It is visited
        return true;

    }
}
