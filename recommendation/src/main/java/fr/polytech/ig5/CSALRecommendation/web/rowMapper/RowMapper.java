package fr.polytech.ig5.CSALRecommendation.web.rowMapper;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapper {
    public org.springframework.jdbc.core.RowMapper<Offer> rowMapperOffer = new org.springframework.jdbc.core.RowMapper<Offer>() {
        public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
            Offer offer = new Offer();
            offer.setOfferId(resultSet.getInt("offer_id"));
            offer.setUserId(resultSet.getInt("user_id"));
            offer.setAddress(resultSet.getString("address"));
            offer.setTitle(resultSet.getString("title_offer"));
            offer.setDescription(resultSet.getString("description_offer"));
            offer.setStartingDate(resultSet.getDate("starting_date"));
            offer.setDateEnd(resultSet.getDate("date_end"));
            offer.setNbjobs(resultSet.getInt("nb_jobs"));
            offer.setSalary(resultSet.getInt("salary"));
            return offer;

        }
    };
    public org.springframework.jdbc.core.RowMapper<User> rowMapperUser = new org.springframework.jdbc.core.RowMapper<User>() {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setResumeId(rs.getInt("resume_id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEnabled(rs.getBoolean("enabled"));
            user.setRole(rs.getString("role"));
            user.setZone(rs.getInt("zone"));
            user.setMail(rs.getString("mail"));
            return user;
        }
    };


    public org.springframework.jdbc.core.RowMapper<Offer> getRowMapperOffer() {
        return rowMapperOffer;
    }
    public org.springframework.jdbc.core.RowMapper<User> getRowMapperUser() {
            return rowMapperUser;
        }


}
