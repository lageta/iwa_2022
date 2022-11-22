package fr.polytech.ig5.CSALUsers.payload;

import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines a JSON Object with the attributes :
 * @param username String
 * @param password String
 * @param role String
 * @param zone String
 */
public class RegisterPayload {

    private final String username;
    private final String password;
    private final String role;
    private final String zone;

    @JsonCreator
    public RegisterPayload(@JsonProperty("username") String username, @JsonProperty("password") String password,
                            @JsonProperty("role") String role, @JsonProperty("zone") String zone) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.zone = zone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getZone() {
        return zone;
    }

    public String toString() {
        return new StringJoiner(", ", RegisterPayload.class.getSimpleName() + "[", "]")
                .add(String.format("username=%s", username))
                .add(String.format("password='%s'", password))
                .add(String.format("role='%s'", role))
                .add(String.format("zone='%s'", zone))
                .toString();
    }
    
}
