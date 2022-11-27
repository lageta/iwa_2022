package fr.polytech.ig5.CSALUsers.jdbc.dao;

import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class ResumeRowMapper implements RowMapper<Resume> {

    @Override
    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
        Resume resume = new Resume();
        resume.setResumeId(rs.getInt("resume_id"));
        resume.setUser_Id(rs.getInt("user_id"));
        resume.setTitle(rs.getString("title_resume"));
        resume.setDescription(rs.getString("description_resume"));
        return resume;
    }

}