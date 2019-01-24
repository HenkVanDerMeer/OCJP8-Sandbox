public class C002_MemberInnerClass {
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
      C002_MemberInnerClass outer = new C002_MemberInnerClass();
      outer.new Inner().go();
   }
}
