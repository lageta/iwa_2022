package fr.polytech.ig5.CSALoffers.web.dao;
import fr.polytech.ig5.CSALoffers.model.Offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OfferDaoImpl implements OfferDao{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Offer> rowMapper = new RowMapper<Offer>() {
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

    @Override
    public List<Offer> findAll() {
        try {
            return jdbcTemplate.query("select * from offer", rowMapper);
        }
        catch (Exception e) {
            return null;
        }


    }

    @Override
    public Offer findById(int id) {
        try {
            return jdbcTemplate.query("select * from offer where offer_id = ?", rowMapper, id).get(0);
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public Offer save(Offer offer) {
        try {
            jdbcTemplate.update("INSERT INTO offer ( user_id , address, title_offer, description_offer , starting_date, date_end, nb_jobs, salary) \n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    offer.getUserId(),
                    offer.getAddress(),
                    offer.getTitle(),
                    offer.getDescription(),
                    offer.getStartingDate(),
                    offer.getDateEnd(),
                    offer.getNbjobs(),
                    offer.getSalary()
                    );
            return offer;
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public Offer update(Offer offer){
        try {
            return jdbcTemplate.query("UPDATE offer \n" +
                            "SET user_id = ?, address= ?, title_offer= ?,\n" +
                            "description_offer = ?, starting_date= ?, date_end= ?,\n" +
                            "nb_jobs= ?, salary= ?" +
                            "WHERE offer_id = ?", rowMapper,offer.getOfferId(),
                    offer.getUserId(),
                    offer.getAddress(),
                    offer.getTitle(),
                    offer.getDescription(),
                    offer.getStartingDate(),
                    offer.getDateEnd(),
                    offer.getNbjobs(),
                    offer.getSalary(),
                    offer.getOfferId()).get(0);
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public int delete(int id) {
        try {
            int res = jdbcTemplate.update("DELETE FROM offer WHERE offer_id = ?", id);
            return res;
        }
        catch (Exception e){
            return -1;
        }

    }
}
