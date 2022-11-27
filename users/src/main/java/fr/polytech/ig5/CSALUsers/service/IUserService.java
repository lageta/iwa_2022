package fr.polytech.ig5.CSALUsers.service;

import java.util.List;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import fr.polytech.ig5.CSALUsers.jdbc.model.User;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(int userId);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(int userId);
    Resume getResumeById(int resumeId);
    Resume addResume(Resume resume);
    Resume updateResume(Resume resume);
    int deleteResume(int resumeId);

}
