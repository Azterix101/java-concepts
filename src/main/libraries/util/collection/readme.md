## Overview of `java.util.Collections`

`java.util.Collections` is a utility class in the Java Collections Framework that provides a set of static methods for operating on or returning collections. This class cannot be instantiated because its constructor is private. It serves as a toolbox for common algorithms and operations on collections like `List`, `Set`, and `Map`.

### Key Functionalities

#### 1. Sorting and Ordering
- **sort(List<T> list)**: Sorts the specified list into ascending order according to the natural ordering of its elements.
- **sort(List<T> list, Comparator<? super T> c)**: Sorts the specified list according to the order induced by the specified `Comparator`.
- **reverse(List<?> list)**: Reverses the order of the elements in the specified list.
- **shuffle(List<?> list)**: Randomly permutes the specified list using a default source of randomness.
- **shuffle(List<?> list, Random rnd)**: Randomly permutes the list using the specified source of randomness.
- **rotate(List<?> list, int distance)**: Rotates the elements in the list by the specified distance.

#### 2. Searching
- **binarySearch(List<? extends Comparable<? super T>> list, T key)**: Searches the specified sorted list for the specified object using the binary search algorithm.
- **binarySearch(List<? extends T> list, T key, Comparator<? super T> c)**: Searches the specified sorted list using a `Comparator`.

#### 3. Modifying Contents
- **fill(List<? super T> list, T obj)**: Replaces all elements of the specified list with the specified element.
- **copy(List<? super T> dest, List<? extends T> src)**: Copies all elements from the source list to the destination list.
- **swap(List<?> list, int i, int j)**: Swaps the elements at the specified positions in the specified list.
- **replaceAll(List<T> list, T oldVal, T newVal)**: Replaces all occurrences of one specified value in a list with another.

#### 4. Finding Extreme Values
- **min(Collection<? extends T> coll)**: Returns the minimum element of the given collection according to the natural ordering of its elements.
- **min(Collection<? extends T> coll, Comparator<? super T> comp)**: Returns the minimum element according to the specified `Comparator`.
- **max(Collection<? extends T> coll)**: Returns the maximum element of the given collection according to the natural ordering of its elements.
- **max(Collection<? extends T> coll, Comparator<? super T> comp)**: Returns the maximum element according to the specified `Comparator`.

#### 5. Creating Unmodifiable Views
- **unmodifiableCollection(Collection<? extends T> c)**
- **unmodifiableList(List<? extends T> list)**
- **unmodifiableSet(Set<? extends T> s)**
- **unmodifiableSortedSet(SortedSet<T> s)**
- **unmodifiableMap(Map<? extends K, ? extends V> m)**
- **unmodifiableSortedMap(SortedMap<K, ? extends V> m)**

These methods return an unmodifiable view of the specified collection/map. Attempts to modify the returned collection/map result in an `UnsupportedOperationException`.

#### 6. Creating Synchronized Views
- **synchronizedCollection(Collection<T> c)**
- **synchronizedList(List<T> list)**
- **synchronizedSet(Set<T> s)**
- **synchronizedSortedSet(SortedSet<T> s)**
- **synchronizedMap(Map<K, V> m)**
- **synchronizedSortedMap(SortedMap<K, V> m)**

These methods return a thread-safe (synchronized) view of the specified collection/map. Each method in the returned wrapper synchronizes on the wrapper object itself.

#### 7. Creating Checked Views
- **checkedCollection(Collection<E> c, Class<E> type)**
- **checkedList, checkedSet, checkedMap, etc.**

These methods return a dynamically typesafe view of the specified collection. Attempts to add an element of the wrong type result in an immediate `ClassCastException`.

#### 8. Miscellaneous Utilities
- **frequency(Collection<?> c, Object o)**: Returns the number of elements in the specified collection equal to the specified object.
- **disjoint(Collection<?> c1, Collection<?> c2)**: Returns `true` if the two specified collections have no elements in common.
- **addAll(Collection<? super T> c, T... elements)**: Adds all the specified elements to the specified collection.
- **newSetFromMap(Map<E, Boolean> map)**: Returns a set backed by the specified map.

#### 9. Empty/Singleton Collections (Immutable)
- **emptyList(), emptySet(), emptyMap()**: Return immutable empty collections/maps.
- **singletonList(T o), singletonSet(T o), singletonMap(K key, V value)**: Return an immutable collection/map containing only the specified single element/entry.

### Summary
`java.util.Collections` is a fundamental utility class providing essential algorithms (sorting, searching), wrappers (unmodifiable, synchronized), and factory methods for common collection-related tasks in Java. It enhances the functionality and usability of the Java Collections Framework.