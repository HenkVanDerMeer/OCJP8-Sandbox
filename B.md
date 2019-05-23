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

_Reductions_ are a special type of terminal operation where all of the contents of the stream are combined into a single primitive or `Object`.

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
        <td>Terminates</td>
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

The `allMatch()`, `anyMatch()` and `noneMatch()` methods search a stream and return information about how the stream pertains to the predicate.
Method signatures:
```
boolean anyMatch(Predicate <? super T> predicate)
boolean allMatch(Predicate <? super T> predicate)
boolean noneMatch(Predicate <? super T> predicate)
```
Examples:
```
List<String> list = Arrays.asList("Harry", "Dick", "Tom");
Predicate<String> length = string -> string.length() > 4;
System.out.println(list.stream().anyMatch(length)); // Returns true
System.out.println(list.stream().allMatch(length)); // Returns false
System.out.println(list.stream().noneMatch(length)); // Returns false
```

Note that reuse of a predicate is possible, but reuse of a stream not; this will result in an `IllegalStateException`. 

The `collect()` method is a special type of reduction called a _mutable reduction_.
It is more efficient than a regular reduction because the same mutable object is used while accumulating.
Method signatures:
```
<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner)
<R, A> R collect(Collector<? super T, A, R> collector)
```
...

The <code>count()</code> method determines the number of elements in a finite stream. For an infinite stream it hangs.
Method signature:
```
long count()
```
Examples:
```
Stream<String> finite = Stream.of("One", "Two", "Three");
System.out.println(finite.count()); // Returns 3

Stream<String> infinite = Stream.generate(() -> "Hi");
System.out.println(infinite.count()); // Hangs
```

The <code>findAny()</code> and <code>findFirst()</code> methods return an element of the stream unless the stream is empty.
If the stream is empty they return an empty `Optional`.
Method signature:

```
Optional<T> findAny()
Optional<T> findFirst()
```
Examples:
```
Stream<String> finite = Stream.of("One", "Two", "Three");
finite.findAny().ifPresent(System.out::println); // Returns "One"

Stream<String> infinite = Stream.generate(() -> "Hi");
infinite.findFirst().ifPresent(System.out::println); // Returns "Hi"
```
The `forEach()` method is a terminal operation that loops through the elements of a stream.
It can also be used for any `Collection`. Method signature:
```
void forEach(Consumer<? super T> action)
```
Examples:
```
Stream<String> finite = Stream.of("One", "Two", "Three");
finite.forEach(System.out::println); // Returns "One", "Two", "Three"

Stream<String> infinite = Stream.generate(() -> "Hi");
infinite.forEach(System.out::println); // Hangs

List<Integer> numbers = Arrays.asList(41, 42, 43);
numbers.forEach(System.out::println); // Returns 41, 42, 43
```
* <code>min()</code> / <code>max()</code>

The `reduce()` method combines a stream into a single object. Method signatures:
```
T reduce(T identity, BinaryOperator<T> accumulator)
Optional<T> reduce(BinaryOperator<T> accumulator)
<U> U reduce(U identity, BiFunction<U,? super T,U> accumulator, BinaryOperator<U> combiner)
```

Examples:
```
Stream<String> stream = Stream.of("w", "o", "l", "f");
String word = stream.reduce("", (s, c) -> s + c);
System.out.println(word); // returns "wolf"

BinaryOperator<Integer> op = (a, b) -> a * b;
Stream<Integer> empty = Stream.empty();
Stream<Integer> threeElements = Stream.of(3, 5, 6);
Stream<Integer> oneElement = Stream.of(3);
empty.reduce(op).ifPresent(System.out::println); // no output
oneElement.reduce(op).ifPresent(System.out::println); // Returns 3
threeElements.reduce(op).ifPresent(System.out::println); // Returns 90
```
...

#### 3.	Filter a collection by using lambda expressions
...
#### 4.	Identify the operations, on stream, that are lazy
...

---
Back to [index](README.md)
