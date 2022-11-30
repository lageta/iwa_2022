package fr.polytech.ig5.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String role;

    @Override
    public String toString() {
        return firstname+ " " + lastname + " " + username + " " + password + " " + role;
    }
}