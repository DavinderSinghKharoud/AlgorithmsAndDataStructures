package algorthims.InterviewBit;

import java.util.*;

/**
 * Given an N x M matrix A of non-negative integers representing the height of each unit cell in a continent, the "Blue lake" touches the left and top edges of the matrix and the "Red lake" touches the right and bottom edges.
 *
 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
 *
 * Find the number of cells from where water can flow to both the Blue and Red lake.
 */
public class WaterFlow {

    
    public static boolean isValid(int row, int col, int rowLength, int colLength ){
		return row >= 0 && row < rowLength && col >= 0 && col < colLength;
	}
	
    public static void main(String[] args) {
		List<List<Integer>> lst = new ArrayList<>();
		lst.add(Arrays.asList(1, 2, 2, 3, 5));
		lst.add(Arrays.asList(3, 2, 3, 4, 4));
		lst.add(Arrays.asList(2, 4, 5, 3, 1));
		lst.add(Arrays.asList(6, 7, 1, 4, 5));
		lst.add(Arrays.asList(5, 1, 1, 2, 4));

		System.out.println(solve2(lst));
    }
    

	static class PairLakes{
		boolean canReachBlue;
		boolean canReachRed;
		int row;
		int col;

		public PairLakes( int row, int col){
			canReachBlue = false;
			canReachRed = false;
			this.row = row;
			this.col = col;
		}
	}

	public static int solve2(List<List<Integer>> lst) {
		int len1 = lst.size();
		int len2 = lst.get(0).size();

		PairLakes[][] dp = new PairLakes[len1][len2];

		for (int row = 0; row < lst.size(); row++) {
			for (int col = 0; col < lst.get(0).size(); col++) {
				dp[row][col] =new PairLakes(row, col);
			}
		}
		bfsForBlue( lst, dp );
		bfsForRed( lst, dp );

		int res = 0;
		for (int row = 0; row < lst.size(); row++) {
			for (int col = 0; col < lst.get(0).size(); col++) {

				PairLakes curr = dp[row][col];
				if(curr.canReachBlue && curr.canReachRed ){
					res++;
				}

			}
		}
		return res;
	}

	private static void bfsForRed(List<List<Integer>> lst, PairLakes[][] dp) {

    	int[][] direc = new int[][]{ {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
    	Queue<PairLakes> queue = new LinkedList<>();

    	Set<String> visited = new HashSet<>();

		for (int row = lst.size() - 1; row >= 0; row--) {
			visited.add( row + "-" + (lst.get(0).size() - 1) );
			PairLakes curr = dp[row][lst.get(0).size() - 1];
			curr.canReachRed = true;
			queue.add( curr );
		}

		for (int col = lst.get(0).size() - 1; col >= 0; col --) {
			visited.add((lst.size() - 1) + "-" + col);
			PairLakes curr = dp[lst.size() - 1][col];
			curr.canReachRed = true;
			queue.add(curr);
		}

		while ( !queue.isEmpty() ){
			PairLakes curr = queue.poll();
			int row = curr.row;
			int col = curr.col;
			int currValue = lst.get(row).get(col);

			for(int[] dir: direc){
				int modifiedRow = row + dir[0];
				int modifiedCol = col + dir[1];

				if( isValid(modifiedRow, modifiedCol, lst.size(), lst.get(0).size())
				 && lst.get(modifiedRow).get( modifiedCol ) >= currValue){

					if( !visited.contains( modifiedRow + "-" + modifiedCol) ){
						PairLakes temp = dp[modifiedRow][modifiedCol];
						temp.canReachRed = true;
						visited.add(modifiedRow + "-" + modifiedCol);
						queue.add(temp);
					}
				}

			}

		}
	}

	private static void bfsForBlue(List<List<Integer>> lst, PairLakes[][] dp) {
		int[][] direc = new int[][]{ {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
		Queue<PairLakes> queue = new LinkedList<>();

		Set<String> visited = new HashSet<>();

		for (int row = 0; row < lst.size(); row++) {
			visited.add( row + "-" + 0);
			PairLakes curr = dp[row][0];
			curr.canReachBlue = true;
			queue.add( curr );
		}

		for (int col = 1; col < lst.get(0).size(); col ++) {
			visited.add(0 + "-" + col);
			PairLakes curr = dp[0][col];
			curr.canReachBlue = true;
			queue.add(curr);
		}

		while ( !queue.isEmpty() ){
			PairLakes curr = queue.poll();
			int row = curr.row;
			int col = curr.col;
			int currValue = lst.get(row).get(col);

			for(int[] dir: direc){
				int modifiedRow = row + dir[0];
				int modifiedCol = col + dir[1];

				if( isValid(modifiedRow, modifiedCol, lst.size(), lst.get(0).size())
						&& lst.get(modifiedRow).get( modifiedCol ) >= currValue){

					if( !visited.contains( modifiedRow + "-" + modifiedCol) ){
						PairLakes temp = dp[modifiedRow][modifiedCol];
						temp.canReachBlue = true;
						visited.add(modifiedRow + "-" + modifiedCol);
						queue.add(temp);
					}
				}

			}

		}


	}
}
