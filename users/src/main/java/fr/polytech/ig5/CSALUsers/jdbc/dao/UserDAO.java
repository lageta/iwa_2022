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
    
    @Autowired
    @Qualifier("myJDBCTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public User getByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAll() {
        String query = "select * from Users;";
        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(query, rowMapper);
    }
    
}
