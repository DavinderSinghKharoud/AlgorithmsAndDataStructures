package InterviewBit;

/**
 *
 Given a positive integer A, the task is to count the total number of set bits in the binary representation of all the numbers from 1 to A.

 Return the count modulo 109 + 7.



 Problem Constraints
 1 <= A <= 109



 Input Format
 First and only argument is an integer A.



 Output Format
 Return an integer denoting the ( Total number of set bits in the binary representation of all the numbers from 1 to A )modulo 109 + 7.



 Example Input
 Input 1:

 A = 3
 Input 2:

 A = 1


 Example Output
 Output 1:

 4
 Output 2:

 1


 Example Explanation
 Explanation 1:

 DECIMAL    BINARY  SET BIT COUNT
 1          01        1
 2          10        1
 3          11        2
 1 + 1 + 2 = 4
 Answer = 4 % 1000000007 = 4
 Explanation 2:

 A = 1
 DECIMAL    BINARY  SET BIT COUNT
 1          01        1
 Answer = 1 % 1000000007 = 1
 */
public class CountTotalSetBits {

    public static int solve(int num) {
		
		int total = 0;
		int mod = 1000000007;
		
		for( int curr = 1; curr <= num; curr++ ){
			total = (total + countBits( curr ) ) % mod;
		}
		
		return total;
    }
    
    public static int countBits( int num ){
		int mod = 1000000007;
		int total = 0;
		for( int index = 0; index < 32; index++ ){
			int value = ( num & 1 );
			total += value;
			total %= mod;
			num = ( num >> 1 );
		}
		
		return total % mod;
	}
    public static void main(String[] args) {

		System.out.println( solve3(3));
    }

    //Time and Space complexity O(n)
	public static int solve2(int num) {
    	if( num == 0 ) return 0;
		int[] dp = new int[num + 1];
		int total = 0;
		int mod = 1000000007;

		for (int curr = 1; curr <= num; curr++) {
			dp[curr] = ( (curr & 1) == 0 )? dp[curr/ 2]: 1 + dp[curr/2];
			total = total + (dp[curr]) % mod;
			total %= mod;
		}
		return total;
	}

	//Time complexity O( log(n) )

	/**
	 * o, we will iterate till the number of bits in the number. And we don’t have to iterate every single number in the range from 1 to n.
	 * We will perform the following operations to get the desired result.
	 *
	 * , First of all, we will add 1 to the number in order to compensate 0. As the binary number system starts from 0. So now n = n + 1.
	 * We will keep the track of the number of set bits encountered till now. And we will initialise it with n/2.
	 * We will keep one variable which is a power of 2, in order to keep track of bit we are computing.
	 * We will iterate till the power of 2 becomes greater than n.
	 * We can get the number of pairs of 0s and 1s in the current bit for all the numbers by dividing n by current power of 2.
	 * Now we have to add the bits in the set bits count. We can do this by dividing the number of pairs of 0s and 1s by 2 which will give us the number of pairs of 1s only and after that, we will multiply that with the current power of 2 to get the count of ones in the groups.
	 * Now there may be a chance that we get a number as number of pairs, which is somewhere in the middle of the group i.e. the number of 1s are less than the current power of 2 in that particular group. So, we will find modulus and add that to the count of set bits which will be clear with the help of an example.
	 * @param num
	 * @return
	 *
	 * Example: Consider N = 14
	 * From the table above, there will be 28 set bits in total from 1 to 14.
	 * We will be considering 20 as A, 21 as B, 22 as C and 23 as D
	 *
	 * First of all we will add 1 to number N, So now our N = 14 + 1 = 15.
	 *
	 * Calculation for A (20 = 1)
	 * 15/2 = 7
	 * Number of set bits in A = 7 ————> (i)
	 * Calculation for B (2^1 = 2)
	 * 15/2 = 7 => there are 7 groups of 0s and 1s
	 * Now, to compute number of groups of set bits only, we have to divide that by 2.
	 * So, 7/2 = 3. There are 3 set bit groups.
	 * And these groups will contain set bits equal to power of 2 this time, which is 2. So we will multiply number of set bit groups with power of 2
	 * => 3*2 = 6 —>(2i)
	 * Plus
	 * There may be some extra 1s in this because 4th group is not considered, as this division will give us only integer value. So we have to add that as well. Note: – This will happen only when number of groups of 0s and 1s is odd.
	 * 15%2 = 1 —>(2ii)
	 * 2i + 2ii => 6 + 1 = 7 ————>(ii)
	 * Calculation for C (2^2 = 4)
	 * 15/4 = 3 => there are 3 groups of 0s and 1s
	 * Number of set bit groups = 3/2 = 1
	 * Number of set bits in those groups = 1*4 = 4 —> (3i)
	 * As 3 is odd, we have to add bits in the group which is not considered
	 * So, 15%4 = 3 —> (3ii)
	 * 3i + 3ii = 4 + 3 = 7 ————>(iii)
	 * Calculation for D (2^3 = 8)
	 * 15/8 = 1 => there is 1 group of 0s and 1s. Now in this case there is only one group and that too of only 0.
	 * Number of set bit groups = 1/2 = 0
	 * Number of set bits in those groups = 0 * 8 = 0 —> (4i)
	 * As number of groups are odd,
	 * So, 15%8 = 7 —> (4ii)
	 * 4i + 4ii = 0 + 7 = 7 ————>(iv)
	 * At this point, our power of 2 variable becomes greater than the number, which is 15 in our case. (power of 2 = 16 and 16 > 15). So the loop gets terminated here.
	 * Final output = i + ii + iii + iv = 7 + 7 + 7 + 7 = 28
	 * Number of set bits from 1 to 14 are 28.
	 *
	 *
	 *
	 *
	 * https://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n-set-2/
	 */
	public static int solve3(int num) {
		num++;

		int powerOfTwo = 2;

		//EX: Calculation for A (20 = 1)
		//15/2 = 7
		//Number of set bits in A = 7
		int res = num/2; //Minimum half
		int mod = 1000000007;
		while ( powerOfTwo <= num ){
			int totalPairs = num / powerOfTwo;

			// totalPairs/2 gives the complete
			// count of the pairs of 1s
			// Multiplying it with the current power
			// of 2 will give the count of
			// 1s in the current bit
			res += ((( totalPairs / 2 ) % mod) * powerOfTwo) % mod;

			// If the count of pairs was odd then
			// add the remaining 1s which could
			// not be groupped together
			res += ( totalPairs % 2 == 1 )? ( num % powerOfTwo): 0;
			res %= mod;
			// Next power of 2
			powerOfTwo <<= 1;
		}

		return res;
	}
}
