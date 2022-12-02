package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("id"));
        user.setResumeId(rs.getInt("resume_id"));
        user.setEmail(rs.getString("email_address"));
        user.setFirstname(rs.getString("first_name"));
        user.setLastname(rs.getString("last_name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
    
}
