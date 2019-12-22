package algorthims;

/**
 Complete the method/function so that it 
 converts dash/underscore delimited words into 
 camel casing. The first word within the output should be 
 capitalized only if the original word was capitalized 
(known as Upper Camel Case, also often referred to as Pascal case).

 Examples
toCamelCase("the-stealth-warrior"); // returns "theStealthWarrior"

toCamelCase("The_Stealth_Warrior"); // returns "TheStealthWarrior"
 **/
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(toCamelCase("The_hello_wow"));

	}

	public static String toCamelCase(String s) {
		String result = "";
		boolean checkIndexFirst = true;

		if (s.contains("-")) {
			String[] arrOfStr = s.split("-");
			for (String part : arrOfStr) {

				if (checkIndexFirst == true) {
					checkIndexFirst = false;
					result += part;
				} else {
					result += camelCaseWord(part);
				}

			}

			return result;
		}
		else if(s.contains("_")) {
			String[] arrOfStr = s.split("_");
			for (String part : arrOfStr) {

				if (checkIndexFirst == true) {
					checkIndexFirst = false;
					result += part;
				} else {
					result += camelCaseWord(part);
				}

			}

			return result;
		}
		
		else {
			return s;
		}

	}

	/**
	 * Takes an text and convert in to camel case.
	 * 
	 * @param text
	 * @return
	 */
	private static String camelCaseWord(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
	}

}

