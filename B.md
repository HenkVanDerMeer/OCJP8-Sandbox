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

##### 2.1 Terminal stream operations
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
Examples:
```
Stream<String> stream1 = Stream.of("w", "o", "l", "f");
StringBuilder word = stream1.collect(StringBuilder::new,
        StringBuilder::append, StringBuilder::append);
System.out.println(word); // Returns "wolf"

Stream<String> stream2 = Stream.of("w", "o", "l", "f");
TreeSet<String> set1 = stream2.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
System.out.println(set1); // Returns [f, l, o, w]

Stream<String> stream3 = Stream.of("w", "o", "l", "f");
Set<String> set2 = stream3.collect(Collectors.toSet());
System.out.println(set2); // Returns [f, w, l, o]
```

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
The `min()` and `max()` methods use a custom comparator to find the smallest or largest value in a finite stream according to the given sort order.
Method signatures:
```
Optional<T> min(<? super T> comparator)
Optional<T> max(<? super T> comparator)
```
Examples:
```
Stream<String> s = Stream.of("Harry", "Dick", "Tom");
Optional<String> maxLength = s.max((s1, s2) -> s1.length() - s2.length());
maxLength.ifPresent(System.out::println); // Returns "Harry"

Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
minEmpty.ifPresent(System.out::println); // Returns nothing

Stream<Integer> random = Stream.of(12, 42, 3, 15);
Optional<Integer> maxNumber = random.max((s1, s2) -> s1 - s2);
maxNumber.ifPresent(System.out::println); // Returns 42
```
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
##### 2.2 Intermediate stream operations
The `filter()` method returns a Stream with elements that match a given expression.
Method signature:
```
Stream<T> filter(Predicate<? super T> predicate)
```
Example:
```
Stream<String> string = Stream.of("One", "Two", "Three");
string.filter(s -> s.startsWith("T")).forEach(System.out::println); // Returns [Two, Three]
```

The `distinct()` method returns a stream with duplicate values removed.
Method signature:
```
Stream<T> distinct()
```
Example:
```
Stream<Integer> numbers = Stream.of(2, 2, 3, 1, 3, 2, 3, 1);
numbers.distinct().forEach(System.out::println); // Returns 2, 3, 1
```

The `limit()` and `skip()` methods make a finite stream smaller, or make a finite stream out of an infinite stream.
Method signatures:
```
Stream<T> limit(int maxSize)
Stream<T> skip(int n)
```
Example:
```
Stream<Integer> s = Stream.iterate(1, n -> n + 1);
s.skip(5).limit(2).forEach(System.out::print); // Returns 67
```

The `map()` method creates a one-to-one mapping from the elements in the stream to the elements of the next step in the stream.
Method signature:
```
<R> Stream<R> map(Function<? super T, ? extends R> mapper)
```
Example:
```
Stream<String> names = Stream.of("Harry", "Dick", "Tom");
names.map(String::length).forEach(System.out::print); // Returns 543
```

The `flatMap()` method takes each element in the stream and makes any elements it contains top-level elements in a single stream.
Method signature:
```
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
```
Example:
```
List<String> names1 = Arrays.asList("Harry");
List<String> names2 = Arrays.asList();
List<String> names3 = Arrays.asList("Dick", "Tom");
Stream<List<String>> allNames = Stream.of(names1, names2, names3);
allNames.flatMap(s -> s.stream()).forEach(System.out::println); // Returns "Harry", "Dick", "Tom"
```

The `sorted()` method returns a stream with the elements sorted. Method signatures:
```
Stream<T> sorted()
Stream<T> sorted(Comparator<? super T> comparator)
```
Example:
```
Stream<String> string = Stream.of("One", "Two", "Three");
string.sorted().forEach(System.out::println); // Returns "One", "Three", "Two"
```

The `peek()` method allows performing a stream operation without actually changing the stream.
Method signature:
```
Stream<T> peek(Consumer<? super T> action)
```
Examples:
```
Stream<String> string = Stream.of("One", "Two", "Three");
long count = string.filter(s -> s.startsWith("T")).peek(System.out::println).count(); // Returns "Two", "Three"
System.out.println(count); // Returns 2

Stream<Integer> infinite = Stream.iterate(1, x -> x + 1);
infinite.limit(5)
        .peek(System.out::print)
        .filter(x -> x % 2 == 1)
        .forEach(System.out::print); // Returns 11233455
```

##### 2.3 Primitive streams

##### 2.4 Collecting results

#### 3.	Filter a collection by using lambda expressions
...
#### 4.	Identify the operations, on stream, that are lazy
...

---
Back to [index](README.md)
