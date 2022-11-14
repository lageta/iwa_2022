package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.polytech.ig5.CSALUsers.jdbc.dao.UserDAO;

@Controller
public class MainController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("test",userDao.getAll().get(0));
        return "index";
    }

}