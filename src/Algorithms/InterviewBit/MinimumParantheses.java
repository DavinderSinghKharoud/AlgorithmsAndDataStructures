package Algorithms.InterviewBit;


public class MinimumParantheses {


    public static int solve(String str) {
		int len = str.length();
		if( len == 0 ) return 0;
		int res = 0;
		
		int open = 0;
		int close = 0;
		
		for(char c: str.toCharArray() ){
			
			if( c == '(' ){
				open++;
			}else{
				
				if( open == close ){
					res++;
					continue;
				}
				
				close++;
			}
		}
		
		return res + Math.abs(open - close);
		
    }
    public static void main(String[] args) {

        System.out.println( solve("))(())"));
    }
}
