package main.basics.stringreverse;

public class StringReverser {

    /**
     * Reverses a string using StringBuilder's built-in reverse() method.
     * This is often the most efficient and readable approach.
     *
     * @param str The string to reverse.
     * @return The reversed string, or null if the input was null.
     */
    public static String reverseWithStringBuilderBuiltin(String str) {
        if (str == null) {
          return null;
        }
        // 1. Create a StringBuilder from the input string.
        // 2. Call its reverse() method.
        // 3. Convert it back to a String.
        return new StringBuilder(str).reverse().toString();
    }

    public static void main(String[] args) {
        String original = "hello world";
        String reversed = reverseWithStringBuilderBuiltin(original);

        System.out.println("Original: " + original);
        System.out.println("Reversed (StringBuilder): " + reversed); // Output: dlrow olleh

        String empty = "";
        System.out.println("Original: " + empty);
        System.out.println("Reversed (StringBuilder): " + reverseWithStringBuilderBuiltin(empty)); // Output:

        String nullStr = null;
        System.out.println("Original: " + nullStr);
        System.out.println("Reversed (StringBuilder): " + reverseWithStringBuilderBuiltin(nullStr)); // Output: null
    }
}
