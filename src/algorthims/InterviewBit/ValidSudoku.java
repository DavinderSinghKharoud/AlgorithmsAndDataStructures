package algorthims.InterviewBit;

import java.util.*;

public class ValidSudoku {

    public static int isValidSudoku(final List<String> lst) {
		
		int len = lst.size();
		
		if( len == 0 ) return 0;
		
		Set<String> set = new HashSet<>();
		
		for(int row = 0; row < 9; row++ ){
			for(int col = 0; col < 9; col++ ){
				if( lst.get(row).charAt(col) == '.' ) continue;
				if( !set.add( lst.get(row).charAt(col) + "num found in row: " + row) ||
					!set.add( lst.get(row).charAt(col) + "num found in col: " + col ) ||
					!set.add( lst.get(row).charAt(col) + "num found in cube:" + row/3 + "-" + col/3 )  ){
					return 0;
				}
			}
		}
		
		return 1;
    }
    public static void main(String[] args) {

		System.out.println( isValidSudoku2(Arrays.asList("53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79")));
    }

	public static int isValidSudoku2(final List<String> lst) {

		int len = lst.size();

		if (len == 0) return 0;

		int[] grids = new int[9];
		int[] rows = new int[9];
		int[] cols = new int[9];

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {

				if( lst.get(row).charAt(col) != '.' ){
					int val = Character.getNumericValue(lst.get(row).charAt(col) );
					if( ( rows[row] & (1 << val) ) != 0 ) return 0;
					rows[row] |= ( 1 << val);

					if( ( cols[col] & (1 << val) ) != 0 ) return 0;
					cols[col] |= ( 1 << val );

					int grid = ( row/3 ) * 3 + col/3;
					if( ( grids[grid] & ( 1 << val ) ) != 0 ) return 0;
					grids[grid] |= ( 1 << val );
				}
			}
		}
		return 1;
	}


}
