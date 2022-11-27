package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import fr.polytech.ig5.CSALUsers.payload.ResumePayload;
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

    @RequestMapping("/users")
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getOne(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/user/register")
    public String doRegister(@RequestBody RegisterPayload payload) {
        User user = new User();
        user.setUsername(payload.getUsername());
        user.setPassword(payload.getPassword());
        user.setRole(payload.getRole());
        // TODO: set to false after email confirmation implemented
        user.setEnabled(true);

        userService.addUser(user);

        return "created";
    }

    @PutMapping("/user/update")
    public String doUpdate(@RequestBody UpdatePayload payload){
        return "updated";
    }

    @DeleteMapping("/logout")
    public String logout() {
        return "logged out";
    }

    @GetMapping("/resume/{resumeId}")
    public Resume getOneResume(@PathVariable int resumeId){
        return userService.getResumeById(resumeId);
    }
    @PutMapping("/resume/{resumeId}")
    public Resume updateResume(@PathVariable int resumeId, @RequestBody ResumePayload payload){
        Resume resume = new Resume();
        resume.setUser_Id(payload.getUser_id());
        resume.setTitle(payload.getTitle());
        resume.setDescription(payload.getDescription());
        resume.setResumeId(resumeId);
        return userService.updateResume(resume);
    }
    @PostMapping("/resume")
    public Resume createResume(@RequestBody ResumePayload payload){
        Resume resume = new Resume();
        resume.setUser_Id(payload.getUser_id());
        resume.setTitle(payload.getTitle());
        resume.setDescription(payload.getDescription());
        return userService.addResume(resume);
    }

    @DeleteMapping("/resume/{resumeId}")
    public  int deleteResume(@PathVariable int resumeId){
        return userService.deleteResume(resumeId);
    }

}