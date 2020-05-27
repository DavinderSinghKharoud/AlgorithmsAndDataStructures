/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
 */

/*
 * // The idea is that for s2 to be scramble of s1 we consider the tree
// representation of the strings and they will be scramble only if
// every subtree in their representations is either same or mirror
// Catch is that for node equality, the strings can be equal or anagrams.
 * */
public class  ScrambleString {
	
	public static boolean isScramble(String s1, String s2) {
		
		int len1 = s1.length();
		int len2 = s2.length();
		
		if( len1 != len2 ) return false;
		if( len1 == 0 ) return true;
		
		if( s1.equals(s2) ) return true;
		
		int[]freq = new int[26];
		
		for( int index = 0; index < len1; index++ ){
			freq[ s1.charAt(index) - 'a' ]++;
			freq[ s2.charAt(index) - 'a' ]--;
		}
	 
		//check if characters in both the string are not of equal number
		for( int index = 0; index < len1; index++ ){
			if( freq[s1.charAt(index) - 'a' ] != 0 ) return false;
		}
		
		//Now we need to check the mirror trees
		for( int index = 1; index< len1; index++ ){
			
			if( isScramble( s1.substring(index), s2.substring(index) ) && isScramble( s1.substring(0, index), s2.substring(0, index) ) ){
				return true;
			}
			
			if( isScramble( s1.substring(0, index), s2.substring(len1 - index) ) &&
					isScramble( s1.substring(index), s2.substring(0, len1 - index ) ) ){
						return true;
			}	
			
		}
	 
        return false;
    }
    
	public static void main (String[] args) {
		
		System.out.println( isScramble( "abcd", "caebd" ) );
	}
}

