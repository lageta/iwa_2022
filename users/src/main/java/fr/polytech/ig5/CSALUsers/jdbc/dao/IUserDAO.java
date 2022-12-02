package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.util.List;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import fr.polytech.ig5.CSALUsers.jdbc.model.User;

public interface IUserDAO {

    // Create
    public void save(User user);

    // Read
    public User getById(int userId);

    // Read All
    public List<User> getAll();

    // Update
    public void update(User user);

    // Delete
    public void delete(int userId);

    //Create
    public int save(Resume resume);

    public void setResume(int r, int u);

    //Read
    public  Resume getResumeById(int resumeId);

    // Update
    public Resume update(Resume resume);

    // Delete
    public int deleteResume(int resumeId);

    public void rateUser(int origin, int target, int score);

    public double getRate(int userId);


}