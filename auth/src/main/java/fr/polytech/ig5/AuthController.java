package fr.polytech.ig5;

import fr.polytech.ig5.jwt.JWTUtil;
import fr.polytech.ig5.jwt.Role;
import fr.polytech.ig5.jwt.User;
import fr.polytech.ig5.jwt.UserService;
import fr.polytech.ig5.payload.LoginRequest;
import fr.polytech.ig5.payload.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

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
        userMap.put("userId", user.getId());
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

    @PostMapping(path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        System.out.println(registerRequest);
        // Persist user to some persistent storage
        User u = new User();
        u.setFirstName(registerRequest.getFirstname());
        u.setLastName(registerRequest.getLastname());
        u.setEmail(registerRequest.getUsername());
        Role r = new Role();
        if(registerRequest.getRole().equals("Seeker")){
            r.setName("ROLE_SEEKER");
        }else{
            r.setName("ROLE_EMPLOYER");
        }
        Set<Role> roles = new HashSet<>();
        roles.add(r);
        u.setRoles(roles);
        u.setPassword(registerRequest.getPassword());

        userService.save(u);

        return new ResponseEntity<String>("Registered", HttpStatus.OK);
    }

}