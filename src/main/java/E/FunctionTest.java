package E;

import java.util.function.Function;

public class FunctionTest {
    public static void main (String[] args) {
        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();
        System.out.println(f1.apply("Hi"));    // returns 2
        System.out.println(f2.apply("Hello")); // returns 5
    }
}
