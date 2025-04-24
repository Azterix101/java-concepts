package main.libraries.util.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionsExample {

  public static void main(String[] args) {

    // --- 1. Sorting ---
    System.out.println("--- Sorting ---");
    List<String> names = new ArrayList<>();
    names.add("Charlie");
    names.add("Alice");
    names.add("Bob");
    names.add("David");
    System.out.println("Original list: " + names);

    // Sort using natural order (alphabetical for Strings)
    Collections.sort(names);
    System.out.println("Sorted (natural): " + names);

    // Sort using a specific Comparator (reverse order)
    Collections.sort(names, Collections.reverseOrder());
    System.out.println("Sorted (reverse): " + names);
    System.out.println();

    // --- 2. Shuffling and Reversing ---
    System.out.println("--- Shuffling and Reversing ---");
    List<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    System.out.println("Original numbers: " + numbers);

    Collections.shuffle(numbers);
    System.out.println("Shuffled: " + numbers);

    Collections.reverse(numbers);
    System.out.println("Reversed: " + numbers);
    System.out.println();

    // --- 3. Binary Search (List MUST be sorted first!) ---
    System.out.println("--- Binary Search ---");
    List<String> sortedFruits = new ArrayList<>(List.of("Apple", "Banana", "Cherry", "Date"));
    // List MUST be sorted for binarySearch to work correctly!
    // Collections.sort(sortedFruits); // Already sorted in this example

    int indexBanana = Collections.binarySearch(sortedFruits, "Banana");
    System.out.println("Index of 'Banana': " + indexBanana); // Output: 1

    int indexGrape = Collections.binarySearch(sortedFruits, "Grape");
    System.out.println("Index of 'Grape' (not found): " + indexGrape); // Output: negative number (-(insertion
                                                                       // point) - 1)
    System.out.println();

    // --- 4. Finding Min/Max ---
    System.out.println("--- Min/Max ---");
    List<Integer> scores = List.of(88, 95, 72, 95, 80);
    System.out.println("Scores: " + scores);
    System.out.println("Max score: " + Collections.max(scores)); // Output: 95
    System.out.println("Min score: " + Collections.min(scores)); // Output: 72
    System.out.println();

    // --- 5. Frequency ---
    System.out.println("--- Frequency ---");
    System.out.println("Frequency of 95 in scores: " + Collections.frequency(scores, 95)); // Output: 2
    System.out.println();

    // --- 6. Filling and Copying ---
    System.out.println("--- Filling and Copying ---");
    List<Integer> zeros = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0)); // Must have initial size
    System.out.println("Before fill: " + zeros);
    Collections.fill(zeros, 100);
    System.out.println("After fill(100): " + zeros);

    List<String> source = List.of("A", "B", "C");
    // Destination list must be at least as large as the source
    List<String> destination = new ArrayList<>(Arrays.asList("X", "Y", "Z", "W"));
    System.out.println("Source: " + source);
    System.out.println("Destination before copy: " + destination);
    Collections.copy(destination, source);
    System.out.println("Destination after copy: " + destination); // Output: [A, B, C, W]
    System.out.println();

    // --- 7. Unmodifiable View ---
    System.out.println("--- Unmodifiable View ---");
    List<String> modifiableList = new ArrayList<>(List.of("one", "two"));
    List<String> unmodifiableList = Collections.unmodifiableList(modifiableList);
    System.out.println("Unmodifiable list: " + unmodifiableList);

    // Reading is allowed
    System.out.println("Read element 0: " + unmodifiableList.get(0));

    try {
      unmodifiableList.add("three"); // This will throw UnsupportedOperationException
    } catch (UnsupportedOperationException e) {
      System.out.println("Caught expected exception: " + e);
    }

    // Modifying the original list *does* affect the view
    modifiableList.add("three");
    System.out.println("Original list modified: " + modifiableList);
    System.out.println("Unmodifiable view reflects change: " + unmodifiableList);
    System.out.println();

    // --- 8. Synchronized View ---
    System.out.println("--- Synchronized View ---");
    List<String> unsafeList = new ArrayList<>();
    // Get a thread-safe wrapper
    List<String> safeList = Collections.synchronizedList(unsafeList);
    // Now, individual operations like safeList.add(), safeList.get() are
    // synchronized.
    // However, iteration still needs manual synchronization:
    // synchronized (safeList) {
    // for (String item : safeList) { /* process item */ }
    // }
    safeList.add("ThreadSafeAdd");
    System.out.println("Synchronized list after add: " + safeList);
    System.out.println("(Note: Example doesn't show multithreading, just creation)");
    System.out.println();

    // --- 9. Empty and Singleton Collections ---
    System.out.println("--- Empty/Singleton Collections ---");
    List<String> emptyList = Collections.emptyList();
    Map<String, Integer> emptyMap = Collections.emptyMap();
    System.out.println("Empty list size: " + emptyList.size()); // Output: 0
    // emptyList.add("test"); // Throws UnsupportedOperationException

    List<String> singleItemList = Collections.singletonList("TheOnlyItem");
    Set<Integer> singleItemSet = Collections.singleton(42);
    System.out.println("Singleton list: " + singleItemList);
    System.out.println("Singleton set: " + singleItemSet);
    // singleItemList.add("another"); // Throws UnsupportedOperationException
  }
}