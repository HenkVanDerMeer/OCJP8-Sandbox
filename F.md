Back to [index](README.md)

&lt; [E. Using Built-in Lambda Types](E.md)

[G. Lambda Cookbook](G.md) &gt;

---
# F. Collection Operations with Lambda
#### 1. Develop code to extract data from an object by using the `map()` method
A `map` is a collection that maps keys to values, with no duplicate keys allowed.
The elements in a map are key/value pairs.

`Map` does not extend `Collection`. Some common `Map` methods:
<table>
    <tr>
        <th>Method</th>
        <th>Description</th>
    </tr>
    <tr>
        <td><code>void clear()</code></td>
        <td>Removes all keys and values from the map.</td>
    </tr>
    <tr>
        <td><code>boolean isEmpty()</code></td>
        <td>Returns whether the map is empty.</td>
    </tr>
    <tr>
        <td><code>int size()</code></td>
        <td>Returns the number of entries (key/value pairs) in the map.</td>
    </tr>
    <tr>
        <td><code>V get(Object key)</code></td>
        <td>Returns the value mapped by key or <code>null</code> if none is mapped.</td>
    </tr>
    <tr>
        <td><code>V put(K key, V value)</code></td>
        <td>Adds or replaces a key/value pair. Returns previous value or <code>null</code>.</td>
    </tr>
    <tr>
        <td><code>V remove(Object key)</code></td>
        <td>Removes and returns value mapped to key. Returns <code>null</code> if none.</td>
    </tr>
    <tr>
        <td><code>boolean containsKey(Object key)</code></td>
        <td>Returns whether key is in map.</td>
    </tr>
    <tr>
        <td><code>boolean containsValue(Object)</code></td>
        <td>Returns whether value is in map.</td>
    </tr>
    <tr>
        <td><code>Set<K> keySet()</code></td>
        <td>Returns a set of all keys.</td>
    </tr>
    <tr>
        <td><code>Collection<V> values()</code></td>
        <td>Returns <code>Collection</code> of all values.</td>
    </tr>
</table> 

New Java 8 `Map` methods:
* `putIfAbsent(K key, V value)`: sets a value in the map, but skips it if the value is already set to a non-<code>null</code> value
* `merge()`: uses a BiFunction to apply to a value of a map entry
* `computeIfPresent()`: see [G. Lambda Cookbook](G.md)
* `computeIfAbsent()`: see [G. Lambda Cookbook](G.md)

The `Stream` interface has a map() method, see [B.2.2 Intermediate stream operations](B.md#22-intermediate-stream-operations).

#### 2. Search for data by using methods such as `findFirst()`, `findAny()`, `anyMatch()`, `allMatch()`, and `noneMatch()`
See [B.2.1 Terminal stream operations](B.md#21-terminal-stream-operations).

#### 3. Describe the unique characteristics of the `Optional` class
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

#### 4. Perform calculations by using Java Stream methods, such as `count()`, `max()`, `min()`, `average()`, and `sum()`
...
#### 5. Sort a collection by using lambda expressions
...
#### 6. Develop code that uses the `Stream.collect()` method and `Collectors` class methods, such as `averagingDouble()`, `groupingBy()`, `joining()`, and `partitioningBy()`
...

---
Back to [index](README.md)

&lt; [E. Using Built-in Lambda Types](E.md)

[G. Lambda Cookbook](G.md) &gt;
