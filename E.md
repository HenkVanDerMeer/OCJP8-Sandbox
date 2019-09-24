Back to [index](README.md)

&lt; [D. Method Enhancements](D.md)

[F. Collection Operations with Lambda](F.md) &gt;

---
# E. Using Built-in Lambda Types

#### 1. Describe the interfaces of the [`java.util.function`](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) package
<table>
    <tr>
        <th>Functional interfaces</th>
        <th># parameters</th>
        <th>Return type</th>
        <th>Single abstract method</th>
    </tr>
    <tr>
        <td>Supplier&lt;T&gt;</td>
        <td>0</td>
        <td>T</td>
        <td>get</td>
    </tr>
    <tr>
        <td>Consumer&lt;T&gt;</td>
        <td>1 (T)</td>
        <td>void</td>
        <td>accept</td>
    </tr>
    <tr>
        <td>BiConsumer&lt;T, U&gt;</td>
        <td>2 (T, U)</td>
        <td>void</td>
        <td>accept</td>
    </tr>
    <tr>
        <td>Predicate&lt;T&gt;</td>
        <td>1 (T)</td>
        <td>boolean</td>
        <td>test</td>
    </tr>
    <tr>
        <td>BiPredicate&lt;T, U&gt;</td>
        <td>2 (T, U)</td>
        <td>boolean</td>
        <td>test</td>
    </tr>
    <tr>
        <td>Function&lt;T, R&gt;</td>
        <td>1 (T)</td>
        <td>R</td>
        <td>apply</td>
    </tr>
    <tr>
        <td>BiFunction&lt;T, U, R&gt;</td>
        <td>2 (T, U)</td>
        <td>R</td>
        <td>apply</td>
    </tr>
    <tr>
        <td>UnaryOperator&lt;T&gt;</td>
        <td>1 (T)</td>
        <td>T</td>
        <td>apply</td>
    </tr>
    <tr>
        <td>BinaryOperator&lt;T, T&gt;</td>
        <td>2 (T, T)</td>
        <td>T</td>
        <td>apply</td>
    </tr>
</table>

#### 2. Develop code that uses the [`Function`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html) interface
The `Function` interface takes a value of any type and returns a value of any (possibly the same) type.

Example: [FunctionTest](src/main/java/E/FunctionTest.java)

<pre>
@FunctionalInterface
public interface Function&lt;T, R&gt; {
    R apply(T t);
}
</pre>

#### 3. Develop code that uses the [`Consumer`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html) interface
The `Consumer` interface takes a single value of any type and returns `void`.

Example: [ConsumerTest](src/main/java/E/ConsumerTest.java)

<pre>
@FunctionalInterface
public interface Consumer&lt;T&gt; {
    void accept(T t);
}
</pre>

#### 4. Develop code that uses the [`Supplier`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html) interface
The `Supplier` interface supplies values without taking any input.

Example: [SupplierTest](src/main/java/E/SupplierTest.java)

<pre>
@FunctionalInterface
public interface Supplier&lt;T&gt; {
    T get();
}
</pre>

#### 5. Develop code that uses the [`UnaryOperator`](https://docs.oracle.com/javase/8/docs/api/java/util/function/UnaryOperator.html) interface
The `UnaryOperator` interface takes any type and returns a value of the same type (for example: incrementing by 1).

Example: [UnaryOperatorTest](src/main/java/E/UnaryOperatorTest.java)

<pre>
@FunctionalInterface
public interface UnaryOperator&lt;T&gt; extends Function&lt;T, T&gt; {
    T apply (T t);
}
</pre>

#### 6. Develop code that uses the [`Predicate`](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html) interface
The `Predicate` interface takes any type and returns a boolean.

Example: [PredicateTest](src/main/java/E/PredicateTest.java)

<pre>
@FunctionalInterface
public interface Predicate&lt;T&gt; {
    Boolean test(T t);
}
</pre>

#### 7. Develop code that uses the primitive and binary variations of the base interfaces of the `java.util.function` package
The functional interfaces `Consumer`, `Predicate`, `Function` and `Operator` all have variants that take 2 parameters instead of 1:
- `BiConsumer`

<pre>
@FunctionalInterface
public interface BiConsumer&lt;T, U&gt; {
    void accept(T t, U u);
} 
</pre>

Example: [BiConsumerTest](src/main/java/E/BiConsumerTest.java)

- `BiPredicate`

<pre>
@FunctionalInterface
public interface BiPredicate&lt;T, U&gt; {
    Boolean test (T t, U u);
}
</pre>

Example: [BiPredicateTest](src/main/java/E/BiPredicateTest.java)

- `BiFunction`

<pre>
@FunctionalInterface
public interface BiFunction&lt;T, U, R&gt; {
    R apply (T t, U u);
}
</pre>

Example: [BiFunctionTest](src/main/java/E/BiFunctionTest.java)

- `BinaryOperator`

<pre>
@FunctionalInterface
public interface BinaryOperator&lt;T, T&gt; {
    T apply (T t1, T t2);
}
</pre>
 
Example: [BinaryOperatorTest](src/main/java/E/BinaryOperatorTest.java)

...
#### 8. Develop code that uses a method reference, including refactoring a lambda expression to a method reference
A _method reference_ is a compact lambda expression for referring to an existing method.
There are four types of method references:

1\. Reference to a static method

Format: <code>ContainingClass::staticMethodName</code>

Example:
<pre>
Consumer&lt;List&lt;Integer&gt;&gt; lambda = list -> Collections.sort(list);
Consumer&lt;List&lt;Integer&gt;&gt; methodRef = Collections::sort;
</pre>

2\. Reference to an instance method of a particular type

Format: <code>containingObject::instanceMethodName</code>

Example:
<pre>
String str = "abc";
Predicate&lt;String&gt; lambda = s -> s.startsWith(str);
Predicate&lt;String&gt; methodRef = str::startsWith;
</pre>

3\. Reference to an instance method of an arbitrary method of a particular type

Format: <code>ContainingType::methodName</code>

Example:
<pre>
Predicate&lt;String&gt; lambda = s -> s.isEmpty();
Predicate&lt;String&gt; methodRef = String::isEmpty;
</pre>

4\. Reference to a constructor

Format: <code>Classname::new</code>

Example:
<pre>
Supplier&lt;ArrayList&gt; lambda = () -> new ArrayList<>();
Supplier&lt;ArrayList&gt; methodRef = ArrayList::new;
</pre>

... <to do: ch. 4>

---
Back to [index](README.md)

&lt; [D. Method Enhancements](D.md)

[F. Collection Operations with Lambda](F.md) &gt;
