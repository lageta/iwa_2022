package fr.polytech.ig5.CSALUsers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.polytech.ig5.CSALUsers.Action;
import fr.polytech.ig5.CSALUsers.RightChecker;
import fr.polytech.ig5.CSALUsers.Role;
import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import fr.polytech.ig5.CSALUsers.payload.ResumePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;
import fr.polytech.ig5.CSALUsers.payload.RatePayload;
import fr.polytech.ig5.CSALUsers.payload.RegisterPayload;
import fr.polytech.ig5.CSALUsers.payload.UpdatePayload;
import fr.polytech.ig5.CSALUsers.service.IUserService;

@RestController
public class MainController {
     
    @Autowired
    private IUserService userService;

    @RequestMapping("/user/list")
    public ResponseEntity<List<User>> getAll(@RequestHeader("role") String role){
        if (!RightChecker.CheckRight(Action.READ_USER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        List<User> users = userService.getAllUsers();
        if (users == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getOne(@PathVariable int id, @RequestHeader("role") String role){
        if (!RightChecker.CheckRight(Action.READ_USER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        User user = userService.getUserById(id);
        if (user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> doUpdate(@PathVariable int id, @RequestBody UpdatePayload payload, @RequestHeader("role") String role){
        if (!RightChecker.CheckRight(Action.UPDATE_USER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        User u = new User();
        u.setUserId(1);
        u.setFirstname(payload.getFirstname());
        u.setLastname(payload.getLastname());
        u.setPassword(payload.getPassword());
        userService.updateUser(u);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{id}/rate")
    public ResponseEntity<Double> getRate(@PathVariable int id){
        double rate = userService.getRate(id);
        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @PostMapping("/user/{target_id}/rate")
    public ResponseEntity<?> doRate(@PathVariable int target_id, @RequestBody RatePayload payload){
        userService.rateUser(payload.getOrigin_id(), target_id, payload.getScore());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> logout() {
        return new ResponseEntity<>("logged out", HttpStatus.OK);
    }


    @GetMapping("/resume/{resumeId}")
    public ResponseEntity<Resume> getOneResume(@PathVariable int resumeId, @RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.READ_USER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Resume resume = userService.getResumeById(resumeId);
        if(resume==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(resume, HttpStatus.OK);
    }

    @PutMapping("/resume/{resumeId}")
    public ResponseEntity<Resume> updateResume(@PathVariable int resumeId, @RequestBody ResumePayload payload,@RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.READ_USER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Resume resume = new Resume();
        resume.setUser_Id(payload.getUser_id());
        resume.setTitle(payload.getTitle());
        resume.setDescription(payload.getDescription());
        resume.setResumeId(resumeId);
        Resume updatedResume = userService.updateResume(resume);
        if (updatedResume == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(updatedResume, HttpStatus.OK);
    }
    @PostMapping("/resume")
    public ResponseEntity<Resume> createResume(@RequestBody ResumePayload payload,@RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.CREATE_USER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Resume resume = new Resume();
        resume.setUser_Id(payload.getUser_id());
        resume.setTitle(payload.getTitle());
        resume.setDescription(payload.getDescription());
        Resume res = userService.addResume(resume);
        if (res == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/resume/{resumeId}")
    public  ResponseEntity<Integer> deleteResume(@PathVariable int resumeId,@RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.READ_USER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int res = userService.deleteResume(resumeId);
        if (res <=0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}