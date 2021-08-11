/*
Given an expression, A, with operands and operators (OR , AND , XOR), in how many ways can you evaluate the expression to true, by grouping in different ways?

Operands are only true and false.

Return the number of ways to evaluate the expression modulo 103 + 3.



Input Format:

The first and the only argument of input will contain a string, A.

The string A, may contain these characters:
    '|' will represent or operator 
    '&' will represent and operator
    '^' will represent xor operator
    'T' will represent true operand
    'F' will false
Output:

Return an integer, representing the number of ways to evaluate the string.
Constraints:

1 <= length(A) <= 150
Example:

Input 1:
    A = "T|F"

Output 1:
    1

Explanation 1:
    The only way to evaluate the expression is:
        => (T|F) = T 

Input 2:
    A = "T^T^F"
    
Output 2:
    0
    
Explanation 2:
    There is no way to evaluate A to a true statement.
 */

//https://www.youtube.com/watch?v=oyj9tRZhmis
public class EvaluateExpressionToTrue {
	
	public static int cnttrue(String a) {
		StringBuilder symbols = new StringBuilder();
		StringBuilder operators = new StringBuilder();
		
		for( char c: a.toCharArray() ){
			if( c == 'T' || c == 'F' ){
				symbols.append(c);
			}else{
				operators.append(c);
			}
		}
		
		return helper( symbols.toString().toCharArray(), operators.toString().toCharArray() ) % 1003;
    }
    
    public static int helper( char[] exp, char[] op ){
		
		int n = exp.length;
		int[][] t = new int[n][n];
		int[][] f = new int[n][n];
		
		//diagonals
		for(int i = 0; i < n; ++i ){
			if( exp[i] == 'T' ){
				t[i][i] = 1;
			}else{
				f[i][i] = 1;
			}
		}
		
		for( int gap = 1; gap < n; ++gap ){
			for( int i = 0, j = gap; j < n; ++i, ++j ){
				
				for( int g = 0; g < gap; ++g ){
					int k = i + g;
					
					switch( op[k] ){
						case '&': 
							t[i][j] += t[i][k] * t[k + 1][j];
							f[i][j] += t[i][k] * f[k + 1][j] + f[i][k] * t[k + 1][j] + f[i][k] * f[k + 1][j];
							break;
						
						case '|':
							t[i][j] += t[i][k] * t[k + 1][j] + t[i][k] * f[k + 1][j] + f[i][k] * t[k + 1][j];
							f[i][j] += f[i][k] * f[k + 1][j];
							break;
						
						case '^':
							t[i][j] += t[i][k] * f[k + 1][j] + f[i][k] * t[k + 1][j];
							f[i][j] += t[i][k] * t[k + 1][j] + f[i][k] * f[k + 1][j];
							break;
					 }
					 
					 t[i][j] %= 1003;
					 f[i][j] %= 1003;
				}
			}

		}

		return t[0][n - 1];
	}

	public static void main (String[] args) {

		System.out.println( cnttrue("F|T^T&F"));
	}
}

