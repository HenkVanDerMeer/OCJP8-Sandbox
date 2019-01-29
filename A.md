Back to [index](README.md)

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

#### 3. Describe a lambda expression; refactor the code that uses an anonymous inner class to use a lambda expression; describe type inference and target typing
A *lambda expression* is a block of code that gets passed around, like an anonymous method. It has 3 parts:
* A parameter list containing zero or more parameters; parameter types are optional, the list is wrapped in parentheses that can be omitted only if there is exactly one parameter and the parameter type is not specified
* An arrow operator `->`
* The body calling a single method and returning the result of that method.

...

---
Back to [index](README.md)
