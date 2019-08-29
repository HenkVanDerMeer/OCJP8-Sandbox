Back to [index](README.md)

---
# G. Lambda Cookbook
#### 1. Develop code that uses Java SE 8 collection improvements, including Collection.removeIf(), List.replaceAll(), Map.computeIfAbsent(), and Map.computeIfPresent() methods

The method `Collection.removeIf` makes it possible to specify what should be deleted in a collection using a block of code.
Method signature:
<pre>
boolean removeIf(Predicate&lt;? super E&gt; filter)
</pre>
Example:
<pre>
List&lt;String&gt; numbers = Arrays.asList("One", "Two", "Three");
numbers.removeIf(s -> s.startsWith("T")); // Removes "Two" and "Three"
</pre>

The method `List.replaceAll` applies a lambda expression to all elements in a list. The result replaces the current value of that element.
Method signature:
<pre>
void replaceAll(UnaryOperator&lt;E&gt; o)
</pre>
Example:
<pre>
List&lt;Integer&gt; intList = Arrays.asList(1, 2, 3);
integerList.replaceAll(i -> 2 * i);
System.out.println(intList); // Returns [2, 4, 6]
</pre>

The method `Map.computeIfPresent` uses the `BiFunction` on a Map element if the requested key is found.
Method signature:
<pre>
V computeIfPresent(K key, BiFunction&lt;? super K, ? super V, ? extends V&gt; remappingFunction)
</pre>

Example:
<pre>
Map&lt;String, Integer&gt; scores = new HashMap<>();
BiFunction&lt;String, Integer, Integer&gt mapper = (k, v) -> v + 1;
scores.put("Harry", 10);
scores.put("Dick", 10);
scores.computeIfPresent("Harry", mapper);
scores.computeIfPresent("Tom", mapper);
System.out.println(scores); // {Harry=11, Dick=10}
</pre>

The method `Map.computeIfAbsent` uses the `Function` on a Map element if the requested key is not found or `null`.
Method signature:
<pre>
V computeIfAbsent(K key, Function&lt;? super K, ? extends V&gt; mappingFunction)
</pre>

Example:
<pre>
Map&lt;String, Integer&gt; scores = new HashMap<>();
Function&lt;String, Integer&gt; mapper = (k) -> 9;
scores.put("Harry", 10);
scores.put("Dick", null);
scores.computeIfAbsent("Harry", mapper);
scores.computeIfAbsent("Dick", mapper);
scores.computeIfAbsent("Tom", mapper); // {Tom=9, Harry=10, Dick=9}
</pre>

Compute methods
<table>
    <tr>
        <th>Scenario</th>
        <th><code>computeIfPresent</code></th>
        <th><code>computeIfAbsent</code></th>
    </tr>
    <tr>
        <td>Key already in map</td>
        <td>Result of function</td>
        <td>No action</td>
    </tr>
    <tr>
        <td>Key not already in map</td>
        <td>No action</td>
        <td>Result of function</td>
    </tr>
    <tr>
        <td>Functional interface used</td>
        <td><code>BiFunction</code>: takes key and existing value, returns new value</td>
        <td><code>Function</code>: takes key and returns new value</td>
    </tr>
</table>

Compute methods when `null`s are involved
<table>
    <tr>
        <th>Key has</th>
        <th>Mapping functions returns</th>
        <th><code>computeIfPresent</code></th>
        <th><code>computeIfAbsent</code></th>
    </tr>
    <tr>
        <td><code>null</code> value in map</td>
        <td><code>null</code></td>
        <td>Do not change map</td>
        <td>Do not change map</td>
    </tr>
    <tr>
        <td><code>null</code> value in map</td>
        <td>Not <code>null</code></td>
        <td>Do not change map</td>
        <td>Add key to map with mapping function result as value</td>
    </tr>
    <tr>
        <td>Non-<code>null</code> value in map</td>
        <td><code>null</code></td>
        <td>Remove key from map</td>
        <td>Do not change map</td>
    </tr>
    <tr>
        <td>Non-<code>null</code> value in map</td>
        <td>Not <code>null</code></td>
        <td>Set key to mapping function result</td>
        <td>Do not change map</td>
    </tr>
    <tr>
        <td>Key not in map</td>
        <td><code>null</code></td>
        <td>Do not change map</td>
        <td>Do not change map</td>
    </tr>
    <tr>
        <td>Key not in map</td>
        <td>Not <code>null</code></td>
        <td>Do not change map</td>
        <td>Add key to map with mapping function result as value</td>
    </tr>
</table>

#### 2. Develop code that uses Java SE 8 I/O improvements, including Files.find(), Files.walk(), and lines() methods
The `java.nio.file.Path` interface is the primary entry point for working with the NIO.2 API.
It represents a hierarchical path on the storage system to a file or directory.

![alt text](https://github.com/HenkVanDerMeer/OCJP8-Sandbox/java.ico "Java icon")

...
#### 3. Use flatMap() methods in the Stream API
...
#### 4. Develop code that creates a stream by using the Arrays.stream() and IntStream.range() methods
...

---
Back to [index](README.md)
