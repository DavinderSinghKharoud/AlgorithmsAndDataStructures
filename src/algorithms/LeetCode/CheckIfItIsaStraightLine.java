package algorithms.LeetCode;

/**
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point. Check if these points make a straight line in the XY plane.
 */
public class CheckIfItIsaStraightLine {

	//using slope O(n) time complexity
	public static boolean checkStraightLine(int[][] coordinates) {
        
        int len = coordinates.length;
        if( len <= 1 ){
			return true;
		}

        double slope = findSlope( coordinates[0], coordinates[1] );
        for( int i = 2; i < len; i++ ){
        	if( findSlope( coordinates[i], coordinates[i - 1] ) != slope ){
        		return false;
			}
		}
		return true;
    }

    public static double findSlope (int[] p1, int[] p2 ){
		double y = p2[1] - p1[1];
		double x = p2[0] - p1[0];

		return y/x;
	}
	public static void main (String[] args) {
		System.out.println( checkStraightLine( new int[][]{
			{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}
		}));

		System.out.println(checkStraightLine( new int[][]{
				{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}
		}));
	}
}

