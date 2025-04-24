package main.basics.stringreverse;

public class StringReverser4 {

  /**
   * Reverses a string by swapping characters in its char array in-place.
   * Note: Creates a new String at the end as Strings are immutable.
   *
   * @param str The string to reverse.
   * @return The reversed string, or null if the input was null.
   */
  public static String reverseWithInPlaceSwap(String str) {
    if (str == null) {
      return null;
    }
    char[] chars = str.toCharArray();
    int left = 0;
    int right = chars.length - 1;

    while (left < right) {
      // Swap characters
      char temp = chars[left];
      chars[left] = chars[right];
      chars[right] = temp;

      // Move pointers towards the middle
      left++;
      right--;
    }
    return new String(chars);
  }

  public static void main(String[] args) {
    String original = "Example";
    String reversed = reverseWithInPlaceSwap(original);

    System.out.println("Original: " + original);
    System.out.println("Reversed (In-Place Swap): " + reversed); // Output: elpmaxE
  }
}
