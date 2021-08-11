package Others;
/**
 * Description:
 In this kata, you must create a digital root function.

A digital root is the recursive sum of all the digits in 
a number. Given n, take the sum of the digits of n. If 
that value has more than one digit, continue reducing in
 this way until a single-digit number is produced. This 
 is only applicable to the natural numbers.
 
 examples:
 digital_root(16)
=> 1 + 6
=> 7

digital_root(942)
=> 9 + 4 + 2
=> 15 ...
=> 1 + 5
=> 6

digital_root(132189)
=> 1 + 3 + 2 + 1 + 8 + 9
=> 24 ...
=> 2 + 4
=> 6
 **/
public class digital_root_recursive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(digital_root(561));
	}

	public static int digital_root(int n) {

		if(String.valueOf(n).length()<=1) {
			return n;
		}else {
			int temp= (n%10)+digital_root(n/10);
			if(String.valueOf(temp).length()>1) {
				temp=digital_root(temp);
			}
			return temp;
		}
	}
}
