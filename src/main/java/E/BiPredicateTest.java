package E;

import java.util.function.BiPredicate;

public class BiPredicateTest {
    public static void main(String[] args) {
        BiPredicate<String, String> bp1 = (string, prefix) -> string.startsWith(prefix);
        BiPredicate<String, String> bp2 = String::startsWith;
        System.out.println(bp1.test("JavaScript", "Java"));
        System.out.println(bp2.test("JavaScript", "Java"));
    }
}
