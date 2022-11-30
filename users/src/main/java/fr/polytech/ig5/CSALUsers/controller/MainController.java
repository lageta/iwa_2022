package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;
import fr.polytech.ig5.CSALUsers.payload.RegisterPayload;
import fr.polytech.ig5.CSALUsers.payload.UpdatePayload;
import fr.polytech.ig5.CSALUsers.service.IUserService;

@RestController
public class MainController {
     
    @Autowired
    private IUserService userService;

    @RequestMapping("/user/list")
    public List<User> getAll(){
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