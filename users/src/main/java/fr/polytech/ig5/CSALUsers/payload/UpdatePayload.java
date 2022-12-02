package fr.polytech.ig5.CSALUsers.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatePayload {

    private final String firstname;
    private final String lastname;
    private final String password;

    @JsonCreator
    public UpdatePayload(@JsonProperty("firstname") String firstname,
                            @JsonProperty("lastname") String lastname, @JsonProperty("password") String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
