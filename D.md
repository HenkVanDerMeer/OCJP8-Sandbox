Back to [index](README.md)

&lt; [C. Parallel Streams](C.md)

[E. Using Built-in Lambda Types](E.md) &gt;

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

#### 2. Define and use a default method of an interface and describe the inheritance rules for the default method
_Default methods_ are additional methods in an interface that are automatically available in the implementations; see `land()` method in previous example.

When an interface with a default method is extended, the default method can be:
- ignored (so the default method is inherited)
- redeclared (which makes it abstract)
- redefined (which overrides it).

Note: Java fails to compile if a class or interface inherits two `default` methods with the same signature and does not provide its own implementation.

---
Back to [index](README.md)

&lt; [C. Parallel Streams](C.md)

[E. Using Built-in Lambda Types](E.md) &gt;
