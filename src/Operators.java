/**
 Objective
In this challenge, you'll work with arithmetic operators. 
Check out the Tutorial tab for learning materials and
 an instructional video!
Task
Given the meal price (base cost of a meal), tip percent
 (the percentage of the meal price being added as tip), and tax percent (the percentage of the meal price being added as tax) for a meal, find and print the meal's total cost.

Note: Be sure to use precise values for your calculations, 
or you may end up with an incorrectly rounded result!
 **/
public class Operators {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		solve(10.25, 17, 5);
	}

	// Complete the solve function below.
	static void solve(double meal_cost, int tip_percent, int tax_percent) {
		double tip = Double.valueOf(tip_percent)/100*meal_cost;
		double tax = Double.valueOf(tax_percent)/ 100 * meal_cost;
		double total = Math.round(meal_cost + tip + tax);
		System.out.println((int) total);
	}

}
