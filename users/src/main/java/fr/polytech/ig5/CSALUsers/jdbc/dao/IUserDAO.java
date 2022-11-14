package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.util.List;

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

}
