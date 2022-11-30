package fr.polytech.ig5.CSALUsers.service;

import java.util.List;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.polytech.ig5.CSALUsers.jdbc.dao.IUserDAO;
import fr.polytech.ig5.CSALUsers.jdbc.model.User;

@Service
public class UserService implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserDAO userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public User getUserById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public boolean addUser(User user) {
        // TODO: Test if username && email already exist

        // Encode before saving in db
        userDao.save(user);
        return true;
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.delete(userId);        
    }

    @Override
    public Resume getResumeById(int resumeId) {
        return userDao.getResumeById(resumeId);
    }

    @Override
    public Resume addResume(Resume resume) {
        return userDao.save(resume);
    }

    @Override
    public Resume updateResume(Resume resume) {
        return userDao.update(resume);
    }

    @Override
    public int deleteResume(int resumeId) {
        return userDao.deleteResume(resumeId);
    }

}
