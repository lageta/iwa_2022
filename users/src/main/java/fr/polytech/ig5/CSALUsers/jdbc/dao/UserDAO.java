package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import fr.polytech.ig5.CSALUsers.jdbc.model.User;

@Transactional
@Repository
public class UserDAO implements IUserDAO {

    private static String TABLE_NAME = "users";
    
    @Autowired
    @Qualifier("myJDBCTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String query = "INSERT INTO "+ TABLE_NAME +" (resume_id, username, password, enabled, role, zone) values (?, ?, ?, ?, ?, ?);";
        
        jdbcTemplate.update(query, null, user.getUsername(), user.getPassword(), user.getEnabled(), user.getRole(), user.getZone());
    }

    @Override
    public User getById(int userId) {
        String query = "SELECT * FROM "+ TABLE_NAME +" WHERE user_id = ?;";

        RowMapper<User> rowMapper = new UserRowMapper();
        User user = jdbcTemplate.queryForObject(query, rowMapper, userId);
        return user;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM "+ TABLE_NAME +";";

        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public void update(User user) {
        String query = "UPDATE "+ TABLE_NAME + " SET resume_id=?, username=?, password=?, enabled=?, role=?, zone=? WHERE user_id=? ;";

        jdbcTemplate.update(query, user.getResumeId(), user.getUsername(), user.getPassword(), user.getEnabled(), user.getRole(), user.getZone(), user.getUserId());
    }

    @Override
    public void delete(int userId) {
        String query = "DELETE FROM "+ TABLE_NAME + " WHERE user_id = ? ;";

        jdbcTemplate.update(query, userId);        
    }
    
}
