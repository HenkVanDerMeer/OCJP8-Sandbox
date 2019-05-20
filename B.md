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
...
#### 2.	Describe the Stream interface and pipelines
...
#### 3.	Filter a collection by using lambda expressions
...
#### 4.	Identify the operations, on stream, that are lazy
...

---
Back to [index](README.md)
