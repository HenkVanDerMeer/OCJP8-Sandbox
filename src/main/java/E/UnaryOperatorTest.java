package E;

import java.util.function.UnaryOperator;

public class UnaryOperatorTest {
    public static void main(String[] args) {
        UnaryOperator<Integer> p = x -> ++x;
        UnaryOperator<String> u = x -> x.toUpperCase();
        System.out.println(p.apply(42));     // returns 43
        System.out.println(u.apply("Test")); // returns "TEST"
    }
}
