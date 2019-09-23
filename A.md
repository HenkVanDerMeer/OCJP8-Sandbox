< Back to [index](README.md)

[B. Java Collections and Streams with Lambdas](B.md) &gt;

---
## A. Lambda Expressions
#### 1. Describe and develop code that uses Java inner classes, including nested class, static class, local class, and anonymous classes
A _nested class_ is a class defined within another class. There are four types of nested classes:
* A member inner class [(Example)](src/main/java/A/MemberInnerClass.java): a class defined at the same level as instance variables. They require an instance of the outer class to use, and can access `private` members of that outer class.
* A local inner class [(Example)](src/main/java/A/LocalInnerClass.java): defined within a method. They can access `private` members of the outer class, and also `final` (or effectively final) local variables.
* An anonymous inner class [(Example)](src/main/java/A/AnonymousInnerClass.java): a local inner class without a name. They are required to extend exactly one class by name or implement exactly one `interface`.
* A static nested class [(Example)](src/main/java/A/StaticNestedClass.java): a static class (not an inner class!) defined at the same level as static variables. They can exist without an instance of the outer class.

<table>
    <tr>
        <th></th>
        <th>Member inner class</th>
        <th>Local inner class</th>
        <th>Anonymous inner class</th>
        <th>Static nested class</th>
    </tr>
    <tr>
        <td>Access modifiers allowed</td>
        <td>public, protected, private or default access</td>
        <td>None; already local to method</td>
        <td>None; already local to statement</td>
        <td>public, protected, private or default access</td>
    </tr>
    <tr>
        <td>Can extend any class and any number of interfaces</td>
        <td>Yes</td>
        <td>Yes</td>
        <td>No; must have exactly one superclass or interface</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>Can be abstract</td>
        <td>Yes</td>
        <td>Yes</td>
        <td>N/A (no class definition)</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>Can be final</td>
        <td>Yes</td>
        <td>Yes</td>
        <td>N/A (no class definition)</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>Can access instance members of enclosing class</td>
        <td>Yes</td>
        <td>Yes</td>
        <td>Yes</td>
        <td>Not directly; requires an instance of the enclosing class</td>
    </tr>
    <tr>
        <td>Can access local variables of enclosing class</td>
        <td>No</td>
        <td>Yes; if (effectively) final</td>
        <td>Yes; if (effectively) final</td>
        <td>No</td>
    </tr>
    <tr>
        <td>Can declare static methods</td>
        <td>No</td>
        <td>No</td>
        <td>No</td>
        <td>Yes</td>
    </tr>
</table>

#### 2. Describe and write functional interfaces
A *functional interface* is an interface with exactly one abstract method. It is the primary manner in which lambda expressions are passed between methods.

Example:

```
@FunctionalInterface
public interface Sprint {
   public void sprint(Animal animal);
}
public class Tiger implements Sprint {
   public void sprint (Animal animal) {
   System.out.println("Animal " + animal.toString() + " is sprinting fast");
}
```

The annotation `@FunctionalInterface` is optional, but will throw an exception if the interface is not a functional interface.

#### 3a. Describe a lambda expression
A *lambda expression* is a block of code that gets passed around, like an anonymous method. It has 3 parts:
* A parameter list containing zero or more parameters; parameter types are optional, the list is wrapped in parentheses that can be omitted only if there is exactly one parameter and the parameter type is not specified. If a parameter type is specified, it must be specified for all parameters.
* An arrow operator `->`
* The body calling a single method and returning the result of that method; curly braces are optional, unless the body contains more than one statement. In that case all statements must end with a semicolon.

Examples:
```
() -> new Duck()
d -> {return d.quack();}
(Duck d) -> d.quack()
(Animal a, Duck d) -> d.quack()
(y, z) -> {int x=1; return y+10; }
(String s, int z) -> { return s.length()+z; }
(a, b, c) -> a.getName()
```
* Not all specified parameters have to be used; example:
```
(y, z) -> { int x=1; return y+10; } // COMPILES
```
* Redeclaring a parameter is not allowed; example:
```
(a, b) -> { int a = 0; return 5; } // DOES NOT COMPILE
```

#### 3b. Refactor the code that uses an anonymous inner class to use a lambda expression
... <to do: ch. 4>

#### 3c. Describe type inference and target typing
... <to do: ch. 4>

---
Back to [index](README.md)

[B. Java Collections and Streams with Lambdas](B.md) &gt;
