package A;

public class MemberInnerClass {
    private String greeting = "Hi";
    class Inner {
        public int repeat = 3;
        public void go() {
            for (int i = 1; i <= repeat; i++) {
                System.out.println(greeting + " " + i);
            }
        }
    }
    public static void main(String[] args) {
        MemberInnerClass outer = new MemberInnerClass();
        outer.new Inner().go();
    }
}
