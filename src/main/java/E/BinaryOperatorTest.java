package E;

import java.util.function.BinaryOperator;

public class BinaryOperatorTest {
    public static void main(String[] args) {
        BinaryOperator<String> bo1 = String::concat;
        BinaryOperator<String> bo2 = (string1, string2) -> string1.concat(string2);
        System.out.println(bo1.apply("One ", "Two"));
        System.out.println(bo2.apply("One ", "Two"));
    }
}
