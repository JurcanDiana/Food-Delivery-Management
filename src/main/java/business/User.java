package business;

import java.io.Serializable;
import java.util.Objects;

/**
 * User Class
 */
public class User implements Serializable {
    
    private int id;
    private String username;
    private String password;
    private UserType userType;

    /**
     * Constructor
     * @param id ID
     * @param username username
     * @param password password
     * @param userType type
     */
    public User(int id, String username, String password, UserType userType) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    /**
     * Getters
     */
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    /**
     * Overriding equals()
     * @param o object used to compare with
     * @return boolean value
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && username.equals(user.username) && password.equals(user.password) && userType == user.userType;
    }

    /**
     * Overriding hashCode()
     * @return hashCode by the username
     */
    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    /**
     * Overriding toString()
     * @return string with user info
     */
    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                "}\n";
    }
}
