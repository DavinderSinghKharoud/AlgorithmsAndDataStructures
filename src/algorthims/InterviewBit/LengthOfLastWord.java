package algorthims.InterviewBit;

public class LengthOfLastWord {

    public static int lengthOfLastWord(final String str) {
		
		int res = 0;
		int len = str.length();
		if( len == 0 ) return 0;

		for (int index = len - 1; index >= 0; index--) {
			if( str.charAt(index) != ' ' ){
				res++;
			}else{
				if (res != 0 ){
					break;
				}
			}
		}

		return res;
    }
    public static void main(String[] args) {

		System.out.println( lengthOfLastWord("Hello world    "));
    }
}
