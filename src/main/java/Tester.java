import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Tester implements Sprint {

    public static void main(String[] args) {
        IntStream ints = IntStream.rangeClosed(1,10);
        System.out.println("Stats for ints stream: " + ints.summaryStatistics());
        Stream<String> animals = Stream.of("lions", "tigers", "bears");
        System.out.println("Animals:");
        // 1. Print animals as a list
        System.out.println("[" + animals.collect(Collectors.joining(", ")) + "]"); // lions, tigers, bears
        // 2. Print animals as a stream
//        animals.forEach(System.out::println);
    }

    public void tussensprint(String text) {
        System.out.println("Text: " + text);
    }

    public void sprint(String text) {

    }
}
