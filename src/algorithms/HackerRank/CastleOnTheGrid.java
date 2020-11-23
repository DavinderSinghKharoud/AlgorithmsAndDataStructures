package algorithms.HackerRank;

/**
 * ou are given a square grid with some cells open (.) and some blocked (X). Your playing piece can move along any row or column until it reaches the edge of the grid or a blocked cell. Given a grid, a start and an end position, determine the number of moves it will take to get to the end position.
 * For example, you are given a grid with sides  described as follows:
 * ...
 * .X.
 * ...
 * Your starting position  so you start in the top left corner. The ending position is . The path is . It takes  moves to get to the goal.
 * Function Description
 * Complete the minimumMoves function in the editor. It must print an integer denoting the minimum moves required to get from the starting position to the goal.
 * minimumMoves has the following parameter(s):
 * grid: an array of strings representing the rows of the grid
 * startX: an integer
 * startY: an integer
 * goalX: an integer
 * goalY: an integer
 * Input Format
 * The first line contains an integer , the size of the array grid.
 * Each of the next  lines contains a string of length .
 * The last line contains four space-separated integers,
 * Constraints
 *
 *
 * Output Format
 * Print an integer denoting the minimum number of steps required to move the castle to the goal position.
 * Sample Input
 * 3
 * .X.
 * .X.
 * ...
 * 0 0 0 2
 * Sample Output
 * 3
 * Explanation
 * Here is a path that one could follow in order to reach the destination in  steps:
 */

import java.util.*;
public class CastleOnTheGrid {


	//O( m * n ) time and space complexity
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY){

		int[][] dp = new int[grid.length][grid[0].length()];
		
		for(int row = 0; row < grid.length; row++ ){
			String curr = grid[row];
			for(int col = 0; col < grid[0].length(); col++ ){
				if( curr.charAt(col) == '.' ){
					dp[row][col] = -1;
				}else{
					dp[row][col] = Integer.MAX_VALUE;
				}
			}
		}
		
		Queue<Point> queue = new LinkedList<>();
		queue.add( new Point(startX, startY) );
		dp[startX][startY] = 0;

		while( !queue.isEmpty() ){
			Point curr = queue.poll();
			int min_distance = dp[curr.x][curr.y];
			
			List<Point> points = getAllPoints( curr, dp );

			for( Point point: points ){
				if( dp[point.x][point.y] == -1 ){
					
					dp[point.x][point.y] = min_distance + 1;
					if( point.x == goalX && point.y == goalY ){
						return dp[point.x][point.y];
					}
					queue.add(point);


				}
			}
		}
		return dp[goalX][goalY];
    }
    
    public static List<Point> getAllPoints( Point point, int[][] dp ){
		
		int x = point.x;
		int y = point.y;
		
		List<Point> lst = new ArrayList<>();
		
		for(int row = x; row >= 0; row-- ){
			if( dp[row][y] == Integer.MAX_VALUE ) break;
			lst.add(new Point(row, y));
		}
		
		for(int row = x + 1; row < dp.length; row++ ){
			if( dp[row][y] == Integer.MAX_VALUE ) break;
			lst.add(new Point(row, y));
		}
		
		for(int col = y; col >= 0; col-- ){
			if( dp[x][col] == Integer.MAX_VALUE ) break;
			lst.add(new Point(x, col));
		}
		
		for(int col = y + 1; col < dp[0].length; col++ ){
			if( dp[x][col] == Integer.MAX_VALUE ) break;
			lst.add(new Point(x, col));
		}
		
		return lst;
	}
    
    
    public static void main(String[] args) {

		System.out.println( minimumMoves(new String[]{".X.", ".X.", "..."}, 0, 0, 0, 2));
    }
    
    static class Point{
		int x;
		int y;
		
		public Point( int x, int y ){
			this.x = x;
			this.y = y;
		}
	}
}
