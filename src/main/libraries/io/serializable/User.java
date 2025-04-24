package main.libraries.io.serializable;
import java.io.Serializable;

public class User implements Serializable {
    // Explicitly declared serialVersionUID
    private static final long serialVersionUID = 1L; // Or any other long value

    private String username;
    private transient String password; // Example of a transient field

    // Constructor, getters, setters...
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}