package main.libraries.io.serializable;

import java.io.*;

/*In essence, `java.io.Serializable` is the entry point to Java's built-in object serialization mechanism,
  enabling object persistence and transfer.
 Remember to handle `serialVersionUID` and the `transient` keyword appropriately. */

// Assume User class from above implements Serializable

public class SerializationExample {

  public static void main(String[] args) {
    User userToSave = new User();
    userToSave.setUsername("johndoe");
    userToSave.setPassword("secret"); // Password is transient, won't be saved

    // --- Serialization ---
    try (FileOutputStream fos = new FileOutputStream("user.ser"); // .ser is a common extension
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {

      oos.writeObject(userToSave); // Serialize the object
      System.out.println("User object serialized successfully.");

    } catch (IOException e) {
      System.err.println("Serialization error: " + e.getMessage());
    }

    // --- Deserialization ---
    User loadedUser = null;
    try (FileInputStream fis = new FileInputStream("user.ser");
        ObjectInputStream ois = new ObjectInputStream(fis)) {

      loadedUser = (User) ois.readObject(); // Deserialize and cast
      System.out.println("User object deserialized successfully.");
      System.out.println("Loaded Username: " + loadedUser.getUsername());
      //
      System.out.println("Loaded Password: " + loadedUser.getPassword()); // Will
      // be null

    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Deserialization error: " + e.getMessage());
    }
  }
}