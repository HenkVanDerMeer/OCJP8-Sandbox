Back to [index](README.md)

---
# G. Lambda Cookbook
#### 1. Develop code that uses Java SE 8 collection improvements, including Collection.removeIf(), List.replaceAll(), Map.computeIfAbsent(), and Map.computeIfPresent() methods

The method `removeIf` makes it possible to specify what should be deleted in a collection using a block of code.
Method signature:
<pre>
boolean removeIf(Predicate&lt;? super E&gt; filter)
</pre>
Example:
<pre>
List&lt;String&gt; numbers = Arrays.asList("One", "Two", "Three");
numbers.removeIf(s -> s.startsWith("T")); // Removes "Two" and "Three"
</pre>

The method `replaceAll` applies a lambde expression to all elements in a list. The result replaces the current value of that element.
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

The method `forEach` uses a lambda expression to loop through a collection.
Method signature:
<pre>
void forEach(Consumer&lt;? super String&gt;)
</pre>
Example:
<pre>
List&lt;String&gt; numbers = Arrays.asList("One", "Two", "Three");
numbers.forEach(s -> System.out.println(s)); // Using lambda expression
numbers.forEach(System.out::println); // Using method reference
</pre>

...
#### 2. Develop code that uses Java SE 8 I/O improvements, including Files.find(), Files.walk(), and lines() methods
...
#### 3. Use flatMap() methods in the Stream API
...
#### 4. Develop code that creates a stream by using the Arrays.stream() and IntStream.range() methods
...

---
Back to [index](README.md)
