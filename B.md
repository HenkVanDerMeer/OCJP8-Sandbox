Back to [index](README.md)

---
## B. Java Collections and Streams with Lambdas
#### 1.	Develop code that iterates a collection by using the forEach() method and method chaining
The method `forEach` uses a lambda expression to loop through a collection.
Method signature:
<pre>
void forEach(Consumer&lt;? super T&gt;)
</pre>
Example:
<pre>
List&lt;String&gt; numbers = Arrays.asList("One", "Two", "Three");
numbers.forEach(s -> System.out.println(s)); // Using lambda expression
numbers.forEach(System.out::println); // Using method reference
</pre>
... <to do: ch. 4>
#### 2.	Describe the Stream interface and pipelines
A _stream_ in Java is a sequence of data. A _stream pipeline_ is the operations that run on a stream to produce a result.
Streams can be finite or infinite.

A stream pipeline has three parts:
1. a _source_: where the stream originates from
2. _intermediate operations_: transform the stream into another one.
They use lazy evaluation, so the intermediate operations do not run until the terminal operation runs. 
3. a _terminal operation_: returns a result. After the terminal operation completes the stream is no longer valid.

Differences between intermediate and terminal operations
<table>
    <tr>
        <th>Scenario</th>
        <th>For intermediate operations</th>
        <th>For terminal operations</th>
    </tr>
    <tr>
        <td>Required part of a useful pipeline?</td>
        <td>No</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>Can exist multiple times in a pipeline?</td>
        <td>Yes</td>
        <td>No</td>
    </tr>
    <tr>
        <td>Return type is a stream type?</td>
        <td>Yes</td>
        <td>No</td>
    </tr>
    <tr>
        <td>Executed upon method call?</td>
        <td>No</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>Stream valid after call?</td>
        <td>Yes</td>
        <td>No</td>
    </tr>
</table>

Several ways to create a finite stream:
```
Stream<String> s1 = Stream.empty(); // Creates an empty stream
Stream<String> s2 = Stream.of("Hi"); // Creates a stream with 1 element
Stream<String> s3 = Arrays.asList(1, 2, 3).stream(); // Creates a stream with 3 elements
```
Terminal stream operations
<table>
    <tr>
        <th>Method</th>
        <th>What happens for infinite streams</th>
        <th>Return value</th>
        <th>Reduction</th>
    </tr>
    <tr>
        <td><code>allMatch()</code> / <code>anyMatch()</code> / <code>noneMatch()</code></td>
        <td>Sometimes terminates</td>
        <td><code>boolean</code></td>
        <td>No</td>
    </tr>
    <tr>
        <td><code>collect()</code></td>
        <td>Does not terminate</td>
        <td>Varies</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td><code>count()</code></td>
        <td>Does not terminate</td>
        <td>long</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td><code>findAny()</code> / <code>findFirst()</code></td>
        <td>Does not terminate</td>
        <td><code>Optional&lt;T&gt;</code></td>
        <td>No</td>
    </tr>
    <tr>
        <td><code>forEach()</code></td>
        <td>Does not terminate</td>
        <td><code>void</code></td>
        <td>No</td>
    </tr>
    <tr>
        <td><code>min()</code> / <code>max()</code></td>
        <td>Does not terminate</td>
        <td><code>Optional&lt;T&gt;</code></td>
        <td>Yes</td>
    </tr>
    <tr>
        <td><code>reduce()</code></td>
        <td>Does not terminate</td>
        <td>Varies</td>
        <td>Yes</td>
    </tr>
</table>
...

#### 3.	Filter a collection by using lambda expressions
...
#### 4.	Identify the operations, on stream, that are lazy
...

---
Back to [index](README.md)
