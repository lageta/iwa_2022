package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.util.List;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;

public interface IUserDAO {
    
    // Create
    public void save(User user);

    // Read
    public User getByUsername(String username);

    // Read All
    public List<User> getAll();

}
