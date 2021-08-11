package InterviewBit;

import java.util.*;
public class CycleInUndirectedGraph {

    public static int solve(int numOfNodes, ArrayList<ArrayList<Integer>> lst) {
		
		Map<Integer, ArrayList<Integer>> map = new HashMap<>();
		
		for( ArrayList<Integer> curr: lst ){
			ArrayList<Integer> temp1 = map.getOrDefault( curr.get(0), new ArrayList<>() );
			temp1.add(curr.get(1));
			map.put(curr.get(0), temp1);
			
			ArrayList<Integer> temp2 = map.getOrDefault( curr.get(1), new ArrayList<>() );
			temp2.add(curr.get(0));
			map.put(curr.get(1), temp2);
		}
		
		int[][] visited = new int[numOfNodes][2];
		
		for( int node = 1; node <= numOfNodes; node++ ){
			if( isCycle( node, map, visited, 1 ) ){
				return 1;
			}
		}
		
		return 0;
    }
    
    public static boolean isCycle(int node, Map<Integer, ArrayList<Integer>> map, int[][] visited, int total ){
		
		visited[node - 1][0] = 1;
		visited[node - 1][1] = total;
		
		ArrayList<Integer> curr = map.get(node);
		
		if( curr != null ){
			
			for( int children: curr ){
				if( (visited[children - 1][0] == 1 && Math.abs(visited[children - 1][1] - total) + 1 >= 3) ) return true;
				if(  visited[children - 1][0] != 1 && isCycle( children, map, visited, total + 1 ) ){
					return true;
				}
			}
		}
		
		visited[node - 1][0] = 0;
		visited[node - 1][1] = 1;
		return false;
	}

    public static void main(String[] args) {

		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
		ArrayList<Integer> curr1 = new ArrayList<>();
		curr1.add(1);
		curr1.add(2);
		ArrayList<Integer> curr2 = new ArrayList<>();
		curr2.add(2);
		curr2.add(3);
		ArrayList<Integer> curr3 = new ArrayList<>();
		curr3.add(3);
		curr3.add(4);
//		ArrayList<Integer> curr4 = new ArrayList<>();
//		curr4.add(2);
//		curr4.add(3);
//		ArrayList<Integer> curr5 = new ArrayList<>();
//		curr5.add(4);
//		curr5.add(5);


		lst.add(curr1);
		lst.add(curr2);
		lst.add(curr3);
//		lst.add(curr4);
//		lst.add(curr5);

		System.out.println( solve(4, lst));
    }
}
