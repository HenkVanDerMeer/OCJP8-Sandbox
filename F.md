Back to [index](README.md)

&lt; [E. Using Built-in Lambda Types](E.md)

[G. Lambda Cookbook](G.md) &gt;

---
# F. Collection Operations with Lambda
#### 1. Develop code to extract data from an object by using the map() method
...
#### 2. Search for data by using methods such as findFirst(), findAny(), anyMatch(), allMatch(), and noneMatch()
...
#### 3. Describe the unique characteristics of the Optional class
An `Optional` is an object that may or may not be empty.
If an `Optional` is empty it returns the value `Optional.empty`. If not, the value can be retrieved using `opt.get()`.
`Optional.of(value)` stores a value in an `Optional`, and `opt.isPresent()` tests if an `Optional` is empty or not.
The following returns `value` (if present) or `Optional.empty` (if not):

<code>
Optional o = Optional.ofNullable(value);
</code>

`Optional` instance methods:
<table>
    <tr>
        <th>Method</th>
        <th>When `Optional` is empty</th>
        <th>When `Optional` contains a value</th>
    </tr>
    <tr>
        <td><code>get()</code></td>
        <td>Throws an exception</td>
        <td>Returns value</td>
    </tr>
    <tr>
        <td><code>ifPresent(Consumer c)</code></td>
        <td>does nothing</td>
        <td>Calls Consumer c with value</td>
    </tr>
    <tr>
        <td><code>isPresent()</code></td>
        <td>Returns <code>false</code></td>
        <td>Returns <code>true</code></td>
    </tr>
    <tr>
        <td><code>orElse(T other)</code></td>
        <td>Returns <code>other</code> parameter</td>
        <td>Returns value</td>
    </tr>
    <tr>
        <td><code>orElseGet(Supplier s)</code></td>
        <td>Returns result of calling Supplier s</td>
        <td>Returns value</td>
    </tr>
    <tr>
        <td><code>orElseThrow(Supplier s)</code></td>
        <td>Throws exception created by calling Supplier s</td>
        <td>Returns value</td>
    </tr>
</table>

#### 4. Perform calculations by using Java Stream methods, such as count(), max(), min(), average(), and sum()
...
#### 5. Sort a collection by using lambda expressions
...
#### 6. Develop code that uses the Stream.collect() method and Collectors class methods, such as averagingDouble(), groupingBy(), joining(), and partitioningBy()
...

---
Back to [index](README.md)

&lt; [E. Using Built-in Lambda Types](E.md)

[G. Lambda Cookbook](G.md) &gt;
