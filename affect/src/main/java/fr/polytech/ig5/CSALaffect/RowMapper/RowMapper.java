package fr.polytech.ig5.CSALaffect.RowMapper;



import fr.polytech.ig5.CSALaffect.model.Affect;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapper {
    public org.springframework.jdbc.core.RowMapper<Integer> rowMapperNbJobs = new org.springframework.jdbc.core.RowMapper<Integer>() {
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
          return resultSet.getInt("nb_jobs");
        }
    };
    public org.springframework.jdbc.core.RowMapper<Integer> rowMapperCount = new org.springframework.jdbc.core.RowMapper<Integer>() {
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("count");
        }
    };

    public org.springframework.jdbc.core.RowMapper<Affect> rowMapperAffect = new org.springframework.jdbc.core.RowMapper<Affect>() {
        public Affect mapRow(ResultSet resultSet, int i) throws SQLException {
            Affect affect = new Affect();
            affect.setUser_id(resultSet.getInt("user_id"));
            affect.setOffer_id(resultSet.getInt("offer_id"));
            affect.setAccepted(resultSet.getBoolean("is_accepted"));
            return affect;
        }
    };
    public org.springframework.jdbc.core.RowMapper<Integer> getRowMapperNbJobs() {
        return rowMapperNbJobs;
    }

    public org.springframework.jdbc.core.RowMapper<Integer> getRowMapperCount() {
        return rowMapperCount;
    }

    public org.springframework.jdbc.core.RowMapper<Affect> getRowMapperAffect() {
        return rowMapperAffect;
    }
}
