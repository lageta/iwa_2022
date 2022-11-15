package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;
import fr.polytech.ig5.CSALUsers.service.IUserService;

@RestController
public class MainController {

    @Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public User index(){
        return userService.getAllUsers().get(0);
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute String user) {
        return "";
    }

}