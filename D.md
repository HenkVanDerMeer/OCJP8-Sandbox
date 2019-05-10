Back to [index](README.md)

---
# D. Method Enhancements
#### 1. Add static methods to interfaces
An _interface_ is an abstract data type, similar to a class, that defines a list of `public` abstract methods that any class implementing the interface must provide.
An interface may also include constant `public static final` variables, `default` methods and `static` methods.

Example:
```
public interface Fly {
   public int getWingSpan() throws Exception;
   public static final int MAX_SPEED = 100;
 
   public default void land() {
      System.out.println('Animal is landing');
   }
   public static double calculateSpeed(float distance, double time) {
      return distance/time;
   }
}
 
public class Eagle implements Fly {
   public int getWingSpan() {
      return 15;
   }
   public void land() {
      System.out.println('Eagle is diving fast');
   }
}
```
...
#### 2. Define and use a default method of an interface and describe the inheritance rules for the default method
...

---
Back to [index](README.md)
