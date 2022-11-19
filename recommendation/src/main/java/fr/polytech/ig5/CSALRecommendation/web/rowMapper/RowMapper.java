package fr.polytech.ig5.CSALRecommendation.web.rowMapper;

import fr.polytech.ig5.CSALRecommendation.model.Offer;

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


    public org.springframework.jdbc.core.RowMapper<Offer> getRowMapperOffer() {
        return rowMapperOffer;
    }


}
