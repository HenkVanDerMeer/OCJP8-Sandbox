package E;

import java.util.function.BiFunction;

public class BiFunctionTest {
    public static void main(String[] args) {
        BiFunction<String, String, Integer> bf = (string1, string2) -> string1.length() * string2.length();
        System.out.println(bf.apply("One", "Two"));
    }
}
