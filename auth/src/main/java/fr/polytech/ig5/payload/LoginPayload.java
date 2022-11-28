package fr.polytech.ig5.payload;

import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines a JSON Object with the attributes :
 * @param username String
 * @param password String
 */
public class LoginPayload {

    private final String username;
    private final String password;
    @JsonCreator
    public LoginPayload(@JsonProperty("username") String username, @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String toString() {
        return new StringJoiner(", ", LoginPayload.class.getSimpleName() + "[", "]")
                .add(String.format("username=%s", username))
                .add(String.format("password='%s'", password))
                .toString();
    }

}
