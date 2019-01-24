package A;

public class StaticNestedClass {
    static class Nested {
        int price = 6;
    }
    public static void main(String[] args) {
        System.out.println(new Nested().price);
    }
}
