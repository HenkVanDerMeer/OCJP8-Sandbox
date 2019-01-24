Back to [index](README.md)

---
# E. Using Built-in Lambda Types
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

#### 1. Describe the interfaces of the [java.util.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) package
...
#### 2. Develop code that uses the [Function](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html) interface
...
#### 3. Develop code that uses the [Consumer](https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html) interface
...
#### 4. Develop code that uses the [Supplier](https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html) interface
...
#### 5. Develop code that uses the [UnaryOperator](https://docs.oracle.com/javase/8/docs/api/java/util/function/UnaryOperator.html) interface
...
#### 6. Develop code that uses the [Predicate](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html) interface
...
#### 7. Develop code that uses the primitive and binary variations of the base interfaces of the java.util.function package
...
#### 8. Develop code that uses a method reference, including refactoring a lambda expression to a method reference
...

---
Back to [index](README.md)
