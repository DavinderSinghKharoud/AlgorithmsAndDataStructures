package algorthims.LeetCode;

import java.util.*;

public class ShortestPathCountBFS{

    public static int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot){
	boolean [][]visited = new boolean[numRows][numColumns];
	
	int[][] dir = new int[][]{
	    {1, 0}, {0, 1}, {-1, 0}, {0, -1}
	};
	
	int path = 0;
	Queue<int[]> que = new LinkedList<>();
	
	que.add( new int[]{0,0} );
	    
	while( !que.isEmpty() ) {

		int size = que.size();

		path++;

		for (int point = 0; point < size; point++) {

			int[] arr = que.remove();

			if (lot.get(arr[0]).get(arr[1]) == 9) {
				return path - 1;
			}

			visited[arr[0]][arr[1]] = true;

			for (int i = 0; i < dir.length; i++) {
				if (isValid(numRows, numColumns, arr[0] + dir[i][0], arr[1] + dir[i][1])
						&& (lot.get(arr[0] + dir[i][0]).get(arr[1] + dir[i][1]) != 0)
						&& !visited[arr[0] + dir[i][0]][arr[1] + dir[i][1]]) {

					que.add(new int[]{
							arr[0] + dir[i][0], arr[1] + dir[i][1]
					});

				}
			}

		}
	}
	
	return -1;
    }
    
    public static boolean isValid( int numRows, int numColumns, int row, int col ){
	if( row < 0 || col < 0 || row >= numRows || col >= numColumns ){
	    return false;
	}
	return true;
    }
    public static void main( String []args){
	
	
        List<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(0);
        lst1.add(0);
        List<Integer> lst2 = new ArrayList<>();
        lst2.add(1);
        lst2.add(1);
        lst2.add(0);
        List<Integer> lst3 = new ArrayList<>();
        lst3.add(1);
        lst3.add(1);
        lst3.add(9);
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(lst1);
        lst.add(lst2);
        lst.add(lst3);

        System.out.println( removeObstacle(3,3,lst));
    }
}
