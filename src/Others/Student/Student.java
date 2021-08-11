package Others.Student;

public class Student extends Person {

    private static int[] testScores;
    int n=0;

    //Constructor
    Student(String firstName, String lastName, int identification, int[] testScore) {
        super(firstName, lastName, identification);
        this.testScores = testScore;
    }

    static char calculate(){
        int sum=0;
        for(int index=0; index<testScores.length; index++){
            sum+=testScores[index];
        }

        int average = sum/testScores.length;

        char grade = 'T';

        if(average>=90 && average<=100){
            grade = 'O';
        }else if(average>=80 && average<90){
            grade = 'E';
        }else if(average>=70 && average<80){
            grade = 'A';
        }else if(average>=55 && average<70){
            grade = 'P';
        }else if(average>=40 && average<55){
            grade = 'D';
        }

        return grade;
    }

}
