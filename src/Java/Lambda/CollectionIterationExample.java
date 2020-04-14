package Java.Lambda;

import java.util.Arrays;
import java.util.List;

public class CollectionIterationExample {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 40),
                new Person("Lewis", "Carrol", 40),
                new Person("Thomas", "Carlyle", 50),
                new Person("Charlotte", "Bronte", 50),
                new Person("Matthew", "Arnold", 60)

        );

        //people.forEach( p -> System.out.println(p));
        people.forEach(System.out::println);
    }
}
