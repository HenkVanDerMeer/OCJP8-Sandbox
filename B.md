Back to [index](README.md)

&lt; [A. Lambda Expressions](A.md)

[C. Parallel Streams](C.md) &gt;

---
## B. Java Collections and Streams with Lambdas
#### 1.	Develop code that iterates a collection by using the `forEach()` method and method chaining
The method `forEach` uses a lambda expression to loop through a collection.
Method signature:
```
void forEach(Consumer<? super T>)
```
Example:
```
List<String> numbers = Arrays.asList("One", "Two", "Three");
numbers.forEach(s -> System.out.println(s)); // Using lambda expression
numbers.forEach(System.out::println); // Using method reference
```

Several methods can be chained in a stream. Note that the order of the methods matters! Examples:
```
Stream.generate(() -> "Test")
   .sorted()
   .limit(2)
   .forEach(System.out::println); // Hangs

Stream.generate(() -> "Test")
   .limit(2)
   .sorted()
   .forEach(System.out::println); // Returns "Test", "Test"
``` 

#### 2.	Describe the `Stream` interface and pipelines
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
The primitive types `int`, `long` and `double` have their own equivalent for streams:
- `IntStream`: used for the primitive types `byte`, `char`, `short` and `int`
- `LongStream`: used for the primitive type `long`
- `DoubleStream`: used for the primitive types `float` and `double`

These primitive streams have convenience methods that cannot to executed using the wrapper class versions, because streams allow only one pass.
For example calculating an average:
```
IntStream ints = IntStream.of(12, 21, 33);
ints.average().ifPresent(System.out::println); // Returns 22.0

IntStream empty = IntStream.empty();
empty.average().ifPresent(System.out::println); // Returns nothing
```
Primitive streams can be created using the same methods like a regular `Stream`:
- using the `of()` factory method
```
DoubleStream random = DoubleStream.generate(Math::random);
```
- using `generate()` or `iterate()` to create an infinite stream
```
DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
```
- using the `range` method can generate a range of numbers
```
IntStream.range(1, 4).forEach(System.out::println); // Returns 1, 2, 3
IntStream.rangeClosed(1, 4).forEach(System.out::println); // Returns 1, 2, 3, 4
```
- mapping from another stream type; mapping methods:
<table>
    <tr>
        <th>Source Stream class</th>
        <th>To create <code>Stream</code></th>
        <th>To create <code>DoubleStream</code></th>
        <th>To create <code>IntStream</code></th>
        <th>To create <code>LongStream</code></th>
    </tr>
    <tr>
        <td><code>Stream</code></td>
        <td><code>map()</code></td>
        <td><code>mapToDouble()</code></td>
        <td><code>mapToInt()</code></td>
        <td><code>mapToLong()</code></td>
    </tr>
    <tr>
        <td><code>DoubleStream</code></td>
        <td><code>mapToObj()</code></td>
        <td><code>map()</code></td>
        <td><code>mapToInt()</code></td>
        <td><code>mapToLong()</code></td>
    </tr>
    <tr>
        <td><code>IntStream</code></td>
        <td><code>mapToObj()</code></td>
        <td><code>mapToDouble()</code></td>
        <td><code>map()</code></td>
        <td><code>mapToLong()</code></td>
    </tr>
    <tr>
        <td><code>LongStream</code></td>
        <td><code>mapToObj()</code></td>
        <td><code>mapToDouble()</code></td>
        <td><code>mapToInt()</code></td>
        <td><code>map()</code></td>
    </tr>
</table>

Function parameters when mapping between stream types
<table>
    <tr>
        <th>Source Stream class</th>
        <th>To create <code>Stream</code></th>
        <th>To create <code>DoubleStream</code></th>
        <th>To create <code>IntStream</code></th>
        <th>To create <code>LongStream</code></th>
    </tr>
    <tr>
        <td><code>Stream</code></td>
        <td><code>Function</code></td>
        <td><code>ToDoubleFunction</code></td>
        <td><code>ToIntFunction</code></td>
        <td><code>ToLongFunction</code></td>
    </tr>
    <tr>
        <td><code>DoubleStream</code></td>
        <td><code>DoubleFunction</code></td>
        <td><code>DoubleUnaryOperator</code></td>
        <td><code>DoubleToIntFunction</code></td>
        <td><code>DoubleToLongFunction</code></td>
    </tr>
    <tr>
        <td><code>IntStream</code></td>
        <td><code>IntFunction</code></td>
        <td><code>IntToDoubleFunction</code></td>
        <td><code>IntUnaryOperator</code></td>
        <td><code>IntToLongFunction</code></td>
    </tr>
    <tr>
        <td><code>LongStream</code></td>
        <td><code>LongFunction</code></td>
        <td><code>LongToDoubleFunction</code></td>
        <td><code>LongToIntFunction</code></td>
        <td><code>LongUnaryOperator</code></td>
    </tr>
</table>

Primitive streams have their own `Optional` types: `OptionalInt`, `OptionalLong` and `OptionalDouble`
<table>
    <tr>
        <th></th>
        <th><code>OptionalDouble</code></th>
        <th><code>OptionalInt</code></th>
        <th><code>OptionalLong</code></th>
    </tr>
    <tr>
        <td>Getting as a primitive</td>
        <td><code>getAsDouble()</code></td>
        <td><code>getAsInt()</code></td>
        <td><code>getAsLong()</code></td>
    </tr>
    <tr>
        <td><code>orElseGet()</code> parameter type</td>
        <td><code>DoubleSupplier</code></td>
        <td><code>IntSupplier</code></td>
        <td><code>LongSupplier</code></td>
    </tr>
    <tr>
        <td>Return type of <code>max()</code></td>
        <td><code>OptionalDouble</code></td>
        <td><code>OptionalInt</code></td>
        <td><code>OptionalLong</code></td>
    </tr>
    <tr>
        <td>Return type of <code>sum()</code></td>
        <td><code>double</code></td>
        <td><code>int</code></td>
        <td><code>long</code></td>
    </tr>
    <tr>
        <td>Return type of <code>avg()</code></td>
        <td><code>OptionalDouble</code></td>
        <td><code>OptionalDouble</code></td>
        <td><code>OptionalDouble</code></td>
    </tr>
</table>

Because only one terminal operation can be run in a stream some standard calculations (minimum, maximum, average, size, number of values) are always performed and collected in summary statistics.
Example:
```
IntStream ints = IntStream.rangeClosed(1,10);
System.out.println(ints.summaryStatistics()); // Returns IntSummaryStatistics{count=10, sum=55, min=1, average=5,500000, max=10}
```
##### 2.4 Collecting results

Different ways to collect the stream results:

<table>
    <tr>
        <th>Collector</th>
        <th>Description</th>
        <th>Return value when passed to <code>collect</code></th>
    </tr>
    <tr>
        <td><code>averagingDouble(ToDoubleFunction f), averagingInt(ToIntFunction f), averagingLong(ToLongFunction f)</code></td>
        <td>Calculates the average for the three core primitive types</td>
        <td><code>Double</code></td>
    </tr>
    <tr>
        <td><code>counting()</code></td>
        <td>Counts number of elements</td>
        <td><code>Long</code></td>
    </tr>
    <tr>
        <td><code>groupingBy(Function f), groupingBy(Function f, Collector dc), groupingBy(Function f, Supplier s, Collector dc)</code></td>
        <td>Calculates a map grouping by the specified function with the optional type and optional downstream collector</td>
        <td><code>Map&lt;K, List&lt;T&gt;&gt;</code></td>
    </tr>
    <tr>
        <td><code>joining(), joining(CharSequence cs)</code></td>
        <td>Creates a single <code>String</code> using <code>cs</code> as a delimiter between elements if one is specified</td>
        <td><code>String</code></td>
    </tr>
    <tr>
        <td><code>maxBy(Comparator c), minBy(Comparator c)</code></td>
        <td>Finds the largest/smallest elements</td>
        <td><code>Optional&lt;T&gt;</code></td>
    </tr>
    <tr>
        <td><code>mapping(Function f, Collector dc)</code></td>
        <td>Adds another level of collectors</td>
        <td><code>Collector</code></td>
    </tr>
    <tr>
        <td><code>partitioningBy(Predicate p), partitioningBy(Predicate p, Collector dc)</code></td>
        <td>Creates a map grouping by the specified predicate with the optional further downstream collector</td>
        <td><code>Map&lt;Boolean, List&lt;T&gt;&gt;</code></td>
    </tr>
    <tr>
        <td><code>summarizingDouble(ToDoubleFunction f), summarizingInt(ToIntFunction f), summarizingLong(ToLongFunction f)</code></td>
        <td>Calculates average, min, max, and so on</td>
        <td><code>DoubleSummaryStatistics, IntSummaryStatistics, LongSummaryStatistics</code></td>
    </tr>
    <tr>
        <td><code>summingDouble(ToDoubleFunction f), summingInt(ToIntFunction f), summingLong(ToLongFunction f)</code></td>
        <td>Calculates the sum for the three core primitive types</td>
        <td><code>Double, Integer, Long</code></td>
    </tr>
    <tr>
        <td><code>toList(), toSet()</code></td>
        <td>Creates an arbitrary type of list or set</td>
        <td><code>List, Set</code></td>
    </tr>
    <tr>
        <td><code>toCollection(Supplier s)</code></td>
        <td>Creates a <code>Collection</code> of the specified type</td>
        <td><code>Collection</code></td>
    </tr>
    <tr>
        <td><code>toMap(Function k, Function v), toMap(Function k, Function v, BinaryOperator m), toMap(Function k, Function v, BinaryOperator m, Supplier s)</code></td>
        <td>Creates a map using functions to map the keys, values, an optional merge function, and an optional type</td>
        <td><code>Map</code></td>
    </tr>
</table>

Examples:

...

#### 3.	Filter a collection by using lambda expressions
...
#### 4.	Identify the operations, on stream, that are lazy
...

---
Back to [index](README.md)

&lt; [A. Lambda Expressions](A.md)

[C. Parallel Streams](C.md) &gt;
