package fr.polytech.ig5;

import fr.polytech.ig5.jwt.JWTUtil;
import fr.polytech.ig5.jwt.User;
import fr.polytech.ig5.payload.LoginPayload;
import fr.polytech.ig5.payload.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;


    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getToken(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken loginCredentials =
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUserName(), loginRequest.getPassword());
        Authentication authentication =
                authenticationManager.authenticate(loginCredentials);
        User user = (User) authentication.getPrincipal();
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("email", user.getEmail());
        userMap.put("firstname", user.getFirstName());
        userMap.put("lastname", user.getLastName());
        userMap.put("roles", user.getRoles());

        String jwtToken = jwtUtil.createJWT(user);
        Map<String, Object> body = new HashMap<>();
        body.put("jwt", jwtToken);
        body.put("user", userMap);

        return new ResponseEntity<Map<String, Object>>(body, HttpStatus.OK);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody String userName) {
        // Persist user to some persistent storage
        System.out.println("Info saved...");

        return new ResponseEntity<String>("Registered", HttpStatus.OK);
    }

}