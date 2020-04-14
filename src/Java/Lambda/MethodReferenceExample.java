package Java.Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MethodReferenceExample {
    public static void main(String[] args) {

        Thread thread = new Thread(MethodReferenceExample::printMessage);
        thread.start();

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 40),
                new Person("Lewis", "Carrol", 40),
                new Person("Thomas", "Carlyle", 50),
                new Person("Charlotte", "Bronte", 50),
                new Person("Matthew", "Arnold", 60)

        );


        performConditionally(people, p -> true, System.out::println);
    }

    //you can check other functional interfaces from java website
    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        for (Person p : people) {
            if (predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }

    public static void printMessage(){
        System.out.println("Hello");
    }
}
