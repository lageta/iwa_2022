package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;
import fr.polytech.ig5.CSALUsers.payload.RegisterPayload;
import fr.polytech.ig5.CSALUsers.payload.UpdatePayload;
import fr.polytech.ig5.CSALUsers.service.IUserService;

@RestController
public class MainController {
     
    @Autowired
    private IUserService userService;

    @RequestMapping("/user/list")
    public List<User> getAll(@RequestHeader("email") String email, @RequestHeader("role") String role){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getOne(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PutMapping("/user/update")
    public String doUpdate(@RequestBody UpdatePayload payload){
        return "updated";
    }

    @DeleteMapping("/logout")
    public String logout() {
        return "logged out";
    }

}