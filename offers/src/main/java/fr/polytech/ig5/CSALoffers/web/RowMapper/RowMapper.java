package fr.polytech.ig5.CSALoffers.web.RowMapper;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;

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

    public org.springframework.jdbc.core.RowMapper<Keyword> rowMapperKeyword = new org.springframework.jdbc.core.RowMapper<Keyword>() {
        public Keyword mapRow(ResultSet resultSet, int i) throws SQLException {
            Keyword keyword = new Keyword();
            keyword.setKeywordId(resultSet.getInt("keyword_id"));
            keyword.setLibelle(resultSet.getString("libelle"));
            return keyword;

        }
    };

    public org.springframework.jdbc.core.RowMapper<Advantage> rowMapperAdvantage = new org.springframework.jdbc.core.RowMapper<Advantage>() {
        public Advantage mapRow(ResultSet resultSet, int i) throws SQLException {
            Advantage advantage = new Advantage();
            advantage.setAdvantageId(resultSet.getInt("advantage_id"));
            advantage.setLibelle(resultSet.getString("libelle_avantage"));
            return advantage;

        }
    };

    public org.springframework.jdbc.core.RowMapper<Offer> getRowMapperOffer() {
        return rowMapperOffer;
    }

    public org.springframework.jdbc.core.RowMapper<Keyword> getRowMapperKeyword() {
        return rowMapperKeyword;
    }

    public org.springframework.jdbc.core.RowMapper<Advantage> getRowMapperAdvantage() {
        return rowMapperAdvantage;
    }
}
