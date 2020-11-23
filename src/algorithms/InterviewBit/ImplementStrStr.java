package algorithms.InterviewBit;

public class ImplementStrStr {

	//Time complexity O(m * n) and space complexity O(1)
    public static int strStr(final String needle, final String hayStack) {
		
		int len1 = needle.length();
		int len2 = hayStack.length();
		
		if( len1 == 0 || len2 == 0 ){
			return -1;
		}
		
		for( int index = 0; index < len2; index++ ){
			int limit = index + len1;
			
			if( limit <= len2 && needle.equals(hayStack.substring(index, limit ) ) ){
				return index;
			}
		}
		
		return -1;
    }
    public static void main(String[] args) {

		//System.out.println(strStr("hello", "afsdfhello"));
		System.out.println(strStr2("abcabyab","abxabcabcaby"));
    }


    //KMP algorithm
	//Time and space complexity O(n)
	public static int strStr2(final String needle, final String hayStack) {
		int len1 = needle.length();
		int len2 = hayStack.length();

		int[] pattern = new int[len1];

		int start = 0, end = 1;

		//Create a dp
		while ( end < len1 ){

			if(needle.charAt(start) == needle.charAt(end) ){
				pattern[end++] = start + 1;
				start++;
			}else{
				start = (start - 1 < 0 )? 0: pattern[start - 1];
				if(start == 0 ) end++;
			}
		}

		int index = 0;
		start = 0;
		int res;

		while ( index < len2 ){

			if( hayStack.charAt(index) == needle.charAt(start) ){
				res = index - start; //update the res
				start++;
				index++;
				if( start == len1 ) {
					return res;
				}

			}else{
				if( start == 0 ) {
					index++;
					continue;
				}
				start = ( start - 1 > 0 ) ? pattern[start - 1]: 0;

			}
		}

		return -1;


	}
}
