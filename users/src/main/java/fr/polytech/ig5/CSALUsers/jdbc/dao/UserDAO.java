package fr.polytech.ig5.CSALUsers.jdbc.dao;

import java.util.List;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
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

    private static String TABLE_NAME_USER = "users";
    private static String TABLE_NAME_RESUME = "resume";
    
    @Autowired
    @Qualifier("myJDBCTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(User user) {
        String query = "INSERT INTO "+ TABLE_NAME_USER +" (resume_id, first_name, last_name, email_address, password) values (?, ?, ?, ?, ?);";
        
        jdbcTemplate.update(query, null, user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword());
    }

    @Override
    public User getById(int userId) {
        String query = "SELECT * FROM "+ TABLE_NAME_USER +" WHERE id = ?;";

        RowMapper<User> rowMapper = new UserRowMapper();
        User user = jdbcTemplate.queryForObject(query, rowMapper, userId);
        return user;
    }

    @Override
    public List<User> getAll() {
        String query = "SELECT * FROM "+ TABLE_NAME_USER +";";

        RowMapper<User> rowMapper = new UserRowMapper();
        return this.jdbcTemplate.query(query, rowMapper);
    }

    @Override
    public void update(User user) {
        String query = "UPDATE "+ TABLE_NAME_USER + " SET first_name=?, last_name=?, password=?, WHERE id=? ;";

        jdbcTemplate.update(query, user.getFirstname(), user.getLastname(),  user.getPassword(), user.getUserId());
    }

    @Override
    public void delete(int userId) {
        String query = "DELETE FROM "+ TABLE_NAME_USER + " WHERE id = ? ;";

        jdbcTemplate.update(query, userId);        
    }

    @Override
    public Resume save(Resume resume) {
        String query = "INSERT INTO "+ TABLE_NAME_RESUME +" (id, title_resume, description_resume) values (?, ?, ?) RETURNING *;";
        RowMapper<Resume> rowMapper = new ResumeRowMapper();
        jdbcTemplate.update(query,rowMapper,resume.getUser_Id(),resume.getTitle(), resume.getDescription());
        return resume;
    }

    @Override
    public Resume getResumeById(int resumeId) {
        String query = "SELECT * FROM "+ TABLE_NAME_RESUME +" WHERE resume_id = ?;";
        RowMapper<Resume> rowMapper = new ResumeRowMapper();
        Resume resume = jdbcTemplate.queryForObject(query, rowMapper, resumeId);
        return resume;
    }

    @Override
    public Resume update(Resume resume) {
        String query = "UPDATE "+ TABLE_NAME_RESUME + " SET id=?, title_resume=?, description_resume=? WHERE resume_id=? ;";
        RowMapper<Resume> rowMapper = new ResumeRowMapper();
        jdbcTemplate.update(query, rowMapper,resume.getUser_Id(),resume.getTitle(), resume.getDescription() );
        return resume;
    }

    @Override
    public int deleteResume(int resumeId) {
        String query = "DELETE FROM "+ TABLE_NAME_RESUME + " WHERE resume_id = ? ;";
        return jdbcTemplate.update(query, resumeId);
    }

}
