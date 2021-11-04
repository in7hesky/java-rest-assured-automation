package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public String username;
    public String password;

    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
