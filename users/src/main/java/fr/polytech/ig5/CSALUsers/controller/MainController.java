package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import fr.polytech.ig5.CSALUsers.payload.ResumePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/user/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable int id, @RequestBody UpdatePayload payload){
        User u = new User();
        u.setUserId(1);
        u.setFirstname(payload.getFirstname());
        u.setLastname(payload.getLastname());
        u.setPassword(payload.getLastname());
        userService.updateUser(u);
        return ResponseEntity.ok().build();
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