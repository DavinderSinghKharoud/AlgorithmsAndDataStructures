package algorithms.InterviewBit;

/**
 * If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.
 *
 * You may assume that the version strings are non-empty and contain only digits and the . character.
 *
 * The . character does not represent a decimal point and is used to separate number sequences.
 *
 * For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
 *
 * You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.
 *
 *
 *
 * Example 1:
 *
 * Input: version1 = "0.1", version2 = "1.1"
 * Output: -1
 * Example 2:
 *
 * Input: version1 = "1.0.1", version2 = "1"
 * Output: 1
 * Example 3:
 *
 * Input: version1 = "7.5.2.4", version2 = "7.5.3"
 * Output: -1
 */
import java.math.BigInteger;

public class CompareVersionNumbers {

    public static int compareVersion(String version1, String version2) {
			
		int len1 = version1.length();
		int len2 = version2.length();
		boolean isInversed = false;
		if( len1 < len2 ){
			String temp = version1;
			version1 = version2;
			version2 = temp;
			isInversed = true;
			len1 = version1.length();
			len2 = version2.length();
		}
		
		int index1 = 0;
		int index2 = 0;
		
		while( index1 < len1 ){
			
			StringBuilder sbr = new StringBuilder();
			
			while( index1 < len1 && version1.charAt(index1) != '.'){
				sbr.append( version1.charAt(index1++));
			}

			index1++;
			BigInteger bigInteger1 = new BigInteger(sbr.toString());

			sbr = new StringBuilder();
			
			while( index2 < len2 && version2.charAt(index2) != '.' ){
				sbr.append(version2.charAt(index2++));
			}

			index2++;
			BigInteger bigInteger2 = (sbr.length() == 0 ) ? new BigInteger("0"): new BigInteger(sbr.toString());


			int res = bigInteger1.compareTo(bigInteger2);
			if(res > 0){
				return (isInversed)? -1: 1;
			}else if(res < 0){
				return (isInversed)? 1: -1;
			}
			
		}
		
		return 0;

    }



    public static void main(String[] args) {

		System.out.println( compareVersion("4444371174137455", "5.168"));
    }

	public int compareVersion2(String version1, String version2) {

		String[] version1_list = version1.split("\\.");
		String[] version2_list = version2.split("\\.");
		int v1_size = version1_list.length, v2_size = version2_list.length, i1, i2;

		for(int i=0; i < Math.max(v1_size, v2_size); i++) {
			i1 = i < v1_size ? Integer.parseInt(version1_list[i]) : 0;
			i2 = i < v2_size ? Integer.parseInt(version2_list[i]) : 0;
			if(i1 != i2) {
				return i1 > i2 ? 1 : -1;
			}
		}
		return 0;
	}
}
