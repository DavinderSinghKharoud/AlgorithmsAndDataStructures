//package algorthims.LeetCode;


public class PalindromeSubstrings {

    //Expand around center ( shorter version )
    public static int countSubstrings(String S) {
		 
        int N = S.length(), ans = 0;
        for (int center = 0; center <= 2*N-1; ++center) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < N && S.charAt(left) == S.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    public static void main (String[] args) {
        System.out.println( manchester("abcc"));
	}

	//Expanded version
    public static int countSubstrings2(String s) {

        int res = 0;

        for (int i = 0; i < s.length(); i++) {

            int count1 = expand_around_center( s, i, i );
            int count2 = expand_around_center( s, i, i + 1 );

            res += count1 + count2;
        }
        return res;
    }

    private static int expand_around_center(String s, int left, int right ) {
        int count = 0;

        while ( left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right) ){
            count++;
            left--;
            right++;
        }
        return count;
    }

    //Time complexity O(n square ) and space complexity O(n)
    public static int countSubstrings3(String s) {
        int count = 0;
	
	boolean[][] dp = new boolean[s.length()][s.length()];
	
	for( int i = 0; i<s.length(); i++ ){
	    dp[i][i] = true;
	}
	
	for( int i = 0; i<s.length(); i++ ){
	    for( int j = i; j>=0; j-- ){
		
		if( s.charAt(i) == s.charAt(j) &&  j - i <= 2 || dp[i - 1][ j - 1] ){
		    dp[i][j] = true;
		    count++;
		}
	    }
	}
	
	return count;
    }

    public static int manchester( String s){
        String str = getModifiedString(s, s.length() );
        int len = ( 2 * s.length() ) + 1;
        int []p = new int[len];
        int c = 0;
        int r = 0;
        int res = 0;

        for (int i = 0; i < len; i++) {
            int mirror = ( 2 * c ) - i;

            if( i < r ){
                p[i] = Math.min( r - i, p[mirror] );
            }

            int a = i + ( 1 + p[i]);
            int b = i - ( 1 + p[i] );
            while(a < len && b >= 0 && str.charAt(a) == str.charAt(b)) {
                p[i]++;
                a++;
                b--;
            }

            if( i + p[i] > r ){
                c = i;
                r = i + p[i];
            }
        }

        for (int i = 0; i <= p.length/2; i++) {
            res += p[i];
        }
        return res ;
    }

    static String getModifiedString(String s, int N){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++){
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");
        return sb.toString();
    }
}

