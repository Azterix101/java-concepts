package main.basics.stringreverse;

public class StringReverser3 {

  /**
   * Reverses a string by converting it to a char array,
   * creating a new array, and filling it in reverse.
   *
   * @param str The string to reverse.
   * @return The reversed string, or null if the input was null.
   */
  public static String reverseWithCharArray(String str) {
    if (str == null) {
      return null;
    }
    char[] originalChars = str.toCharArray();
    char[] reversedChars = new char[originalChars.length];

    for (int i = 0; i < originalChars.length; i++) {
      reversedChars[i] = originalChars[originalChars.length - 1 - i];
    }
    // Create a new String from the reversed character array
    return new String(reversedChars);
  }

  public static void main(String[] args) {
    String original = "Programming";
    String reversed = reverseWithCharArray(original);

    System.out.println("Original: " + original);
    System.out.println("Reversed (Char Array): " + reversed); // Output: gnimmargorP
  }
}
