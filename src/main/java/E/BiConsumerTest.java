package E;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerTest {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        BiConsumer<String, Integer> bc1 = (string, integer) -> scores.put(string, integer);
        BiConsumer<String, Integer> bc2 = scores::put;
        bc1.accept("Harry", 8);
        bc2.accept("Dick", 9);
        System.out.println(scores); // {Harry=8, Dick=9}
    }
}
