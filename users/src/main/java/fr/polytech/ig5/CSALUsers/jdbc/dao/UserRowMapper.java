package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setResumeId(rs.getInt("resume_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setRole(rs.getString("role"));
        user.setZone(rs.getInt("zone"));
        return user;
    }
    
}
