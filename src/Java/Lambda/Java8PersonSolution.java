package Java.Lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Java8PersonSolution {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 40),
                new Person("Lewis", "Carrol", 40),
                new Person("Thomas", "Carlyle", 50),
                new Person("Charlotte", "Bronte", 50),
                new Person("Matthew", "Arnold", 60)

        );

        //Sort the list by last name
        Collections.sort( people, ( p1, p2 ) -> p1.getLastName().compareTo(p2.getLastName()));


        //Create a method that prints all people that have last name beginning with C
//        printConditionally( people, new Condition() {
//            @Override
//            public boolean test(Person p) {
//                return p.getLastName().startsWith("C");
//            }
//        });

        System.out.println("Printing all the persons with last beginning");
        printConditionally( people, (p) -> p.getLastName().startsWith("C"));

        System.out.println("Print all persons");
        printConditionally( people, p -> true);

        System.out.println("Perform conditionally");
        performConditionally( people, p -> p.getLastName().startsWith("C"), p -> System.out.println(p));

    }


    //we can use inbuilt predicate functional interface
    private static void printConditionally(List<Person> people, Predicate<Person> predicate) {
        for( Person p: people ){
            if( predicate.test(p)){
                System.out.println(p);
            }
        }
    }



    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer ) {
        for( Person p: people ){
            if( predicate.test(p)){
                consumer.accept(p);
            }
        }
    }
}
