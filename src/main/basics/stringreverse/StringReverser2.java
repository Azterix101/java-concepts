package main.basics.stringreverse;

public class StringReverser2 {

  /**
   * Reverses a string by manually iterating backwards and appending
   * characters to a StringBuilder.
   *
   * @param str The string to reverse.
   * @return The reversed string, or null if the input was null.
   */
  public static String reverseManuallyWithStringBuilder(String str) {
    if (str == null) {
      return null;
    }
    StringBuilder reversed = new StringBuilder(str.length());
    for (int i = str.length() - 1; i >= 0; i--) {
      reversed.append(str.charAt(i));
    }
    return reversed.toString();
  }

  public static void main(String[] args) {
    String original = "Java";
    String reversed = reverseManuallyWithStringBuilder(original);

    System.out.println("Original: " + original);
    System.out.println("Reversed (Manual SB): " + reversed); // Output: avaJ
  }
}
