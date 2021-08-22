package Java.Lambda.Streams;

import Java.Lambda.Person;

import java.util.Arrays;
import java.util.List;

public class Streams {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 40),
                new Person("Lewis", "Carrol", 40),
                new Person("Thomas", "Carlyle", 50),
                new Person("Charlotte", "Bronte", 50),
                new Person("Matthew", "Arnold", 60)

        );

        people.stream()
                .filter( person -> person.getLastName().startsWith("MinimizeDifference"))
                .forEach(System.out::println);

        long count = people.parallelStream()
                .filter( person -> person.getLastName().startsWith("MinimizeDifference"))
                .count();

        System.out.println(count);
    }
}
