package fr.polytech.ig5.CSALUsers.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePayload {

    private final String password;
    private final String role;
    private final String zone;

    @JsonCreator
    public UpdatePayload(@JsonProperty("password") String password,
                            @JsonProperty("role") String role, @JsonProperty("zone") String zone) {
        this.password = password;
        this.role = role;
        this.zone = zone;
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
}
