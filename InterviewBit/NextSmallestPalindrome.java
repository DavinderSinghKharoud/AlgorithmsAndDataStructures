/*
 Given a numeric string FindGreatestCommonDivisor representing a large number you need to find the next smallest palindrome greater than this number.



Problem Constraints
1 <= |FindGreatestCommonDivisor| <= 100

FindGreatestCommonDivisor doesn't start with zeroes and always contain digits from 0-9.



Input Format
First and only argument is an string FindGreatestCommonDivisor.



Output Format
Return a numeric string denoting the next smallest palindrome greater than FindGreatestCommonDivisor.



Example Input
Input 1:

 FindGreatestCommonDivisor = "23545"
Input 2:

 FindGreatestCommonDivisor = "999"


Example Output
Output 1:

 "23632"
Output 2:

 "1001"
 */


public class NextSmallestPalindrome {
	
	public static String solve(String s) {
		int len = s.length();
		
		StringBuilder res = new StringBuilder();

		if( len == 1 ){
			int num = Integer.parseInt(s);
			return "11";
		}


		char[] arr = s.toCharArray();


		for( int index = 0; index < len; index++ ){
			if( index == len - 1 && arr[index] == '9'){
				res.append('1');
				for(int count = 0; count < len - 1; count++ ){
					res.append('0');
				}
				res.append('1');

				return res.toString();

			}else if( arr[index] != '9' ){
				break;
			}

		}

		int mid = len/2;
		int left = mid - 1;
		int right = ( len % 2 == 0 ) ? mid: mid + 1;
		
		boolean leftSmaller = false; //to check if copy of left side to righ


		while( left >= 0 && right < len && s.charAt(left) == s.charAt(right) ){
			left--;
			right++;
		}

		//Checking if middle element need to be incremented or not
		if ( left < 0 || Character.getNumericValue(arr[left]) < Character.getNumericValue(arr[right]) ){
			leftSmaller = true;
		}

		while( left >= 0 ){
			arr[right++] = arr[left--];
		}

		if( leftSmaller ){
			int carry = 1;

			if( len % 2 != 0 ){
				int midVal = Character.getNumericValue(arr[mid]) + 1;
				arr[mid] = Character.forDigit( midVal % 10, 10);
				carry = mid/ 10;
			}

			left = mid - 1;
			right = ( len % 2 == 0 )? mid: mid + 1;

			while ( left >= 0 && carry > 0 ){
				int leftVal = Character.getNumericValue(arr[left]);
				arr[left] = Character.forDigit( (leftVal + carry)%10, 10);
				carry = ( leftVal + carry )/ 10;
				arr[right++] = arr[left--];
			}
		}

		for(char c: arr){
			res.append(c);
		}
		return res.toString();
		
	}
	public static void main (String[] args) {

		System.out.println( solve("9" ) );
	}
}

