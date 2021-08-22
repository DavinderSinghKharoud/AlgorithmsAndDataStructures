package Others.Student;

import java.util.Scanner;

/**
 * Task
 * You are given two classes, Person and Student, where Person is the base class and Student
 * is the derived class. Completed code for Person and a declaration for Student are provided
 * for you in the editor. Observe that Student inherits all the properties of Person.
 *
 * Complete the Student class by writing the following:
 *
 * FindGreatestCommonDivisor Student class constructor, which has  parameters:
 * FindGreatestCommonDivisor string, .
 * FindGreatestCommonDivisor string, .
 * An integer, .
 * An integer array (or vector) of test scores, .
 * FindGreatestCommonDivisor char calculate() method that calculates a Student object's average and returns the grade
 * character representative of their calculated average:
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter firstName: ");
        String firstName = scan.next();

        System.out.print("Enter lastName: ");
        String lastName = scan.next();

        System.out.print("Enter id: ");
        int id = scan.nextInt();

        System.out.print("Enter numScoreLength: ");
        int numScores = scan.nextInt();



        int[] testScores = new int[numScores];
        for(int i = 0; i < numScores; i++){
            System.out.print("Enter Score: ");
            testScores[i] = scan.nextInt();
        }
        scan.close();

        Student s = new Student(firstName, lastName, id, testScores);
        s.printPerson();
        System.out.println("Grade: " + s.calculate() );
    }
}
