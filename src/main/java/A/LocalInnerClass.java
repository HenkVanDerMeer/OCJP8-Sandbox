package A;

public class LocalInnerClass {
    private int length = 5;
    private void calculate() {
        final int width = 20;
        class Inner {
            public int multiply() {
                return length * width;
            }
        }
        Inner inner = new Inner();
        System.out.println(inner.multiply());
    }
    public static void main(String[] args) {
        LocalInnerClass outer = new LocalInnerClass();
        outer.calculate();
    }
}
