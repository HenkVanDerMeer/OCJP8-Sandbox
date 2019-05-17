package E;

import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> p1 = x -> x.isEmpty();
        Predicate<String> p2 = String::isEmpty;
        System.out.println(p1.test("Hi")); // returns false
        System.out.println(p2.test(""));   // returns true
    }
}
  