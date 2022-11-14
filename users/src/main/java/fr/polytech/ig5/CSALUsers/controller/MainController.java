package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.polytech.ig5.CSALUsers.jdbc.dao.UserDAO;
import fr.polytech.ig5.CSALUsers.service.IUserService;

@Controller
public class MainController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("test",userService.getAllUsers().get(0));
        return "index";
    }

}