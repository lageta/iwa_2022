package fr.polytech.ig5.payload;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}