package E;

import java.time.LocalDate;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = () -> LocalDate.now();
        System.out.println(s1.get()); // returns current date
        System.out.println(s2.get()); // returns current date
    }
}
