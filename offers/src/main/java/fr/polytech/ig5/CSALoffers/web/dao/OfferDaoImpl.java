package fr.polytech.ig5.CSALoffers.web.dao;
import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.RowMapper.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class OfferDaoImpl implements OfferDao{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper rowMapper = new RowMapper();


    @Override
    public List<Offer> findAll() {
        try {
            return jdbcTemplate.query("select * from offer", rowMapper.getRowMapperOffer());
        }
        catch (Exception e) {
            return null;
        }


    }

    @Override
    public Offer findById(int id) {
        try {
            return jdbcTemplate.query("select * from offer where offer_id = ?", rowMapper.getRowMapperOffer(), id).get(0);
        }
        catch (Exception e){
            return null;
        }

    }

    @Override
    public Offer save(Offer offer) {
        try {
           return jdbcTemplate.query("INSERT INTO offer ( user_id , address, title_offer, description_offer , starting_date, date_end, nb_jobs, salary) \n" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING *",
                    rowMapper.getRowMapperOffer(),
                    offer.getUserId(),
                    offer.getAddress(),
                    offer.getTitle(),
                    offer.getDescription(),
                    offer.getStartingDate(),
                    offer.getDateEnd(),
                    offer.getNbjobs(),
                    offer.getSalary()
                    ).get(0);
        }
        catch (Exception e){
            e.printStackTrace();
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
                            "WHERE offer_id = ?", rowMapper.getRowMapperOffer(),offer.getOfferId(),
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
            deleteAllTags(id);
            deleteAllAdvantages(id);
            jdbcTemplate.update("DELETE FROM affect WHERE offer_id = ?", id);
            int res = jdbcTemplate.update("DELETE FROM offer WHERE offer_id = ?", id);
            return res;
        }
        catch (Exception e){
            return -1;
        }

    }

    @Override
    public List<Keyword> findAllKeyword() {
        try {
            return jdbcTemplate.query("select * from keyword", rowMapper.getRowMapperKeyword());
        }
        catch (Exception e) {
            return null;
        }


    }
    @Override
    public List<Keyword> findAllKeyword(int offerId) {
        try {
            return jdbcTemplate.query("SELECT *\n" +
                    "  FROM keyword\n" +
                    " WHERE keyword_id IN (select keyword_id from tags where offer_id = ?) ", rowMapper.getRowMapperKeyword(), offerId);
        }
        catch (Exception e) {
            return null;
        }


    }

    @Override
    public List<Advantage> findAllAdvantage() {
        try {
            return jdbcTemplate.query("select * from advantage", rowMapper.getRowMapperAdvantage());
        }
        catch (Exception e) {
            return null;
        }


    }

    @Override
    public List<Advantage> findAllAdvantage(int offerId) {
        try {
            return jdbcTemplate.query("SELECT *\n" +
                    "  FROM advantage\n" +
                    " WHERE advantage_id IN (select advantage_id from advantages where offer_id = ?) ", rowMapper.getRowMapperAdvantage(), offerId);
        }
        catch (Exception e) {
            return null;
        }


    }

    @Override
    public int deleteAdvantages(int offerId, int advantageId) {
        try {
            int res = jdbcTemplate.update("DELETE FROM advantages WHERE offer_id = ? AND advantage_id = ?", offerId, advantageId);
            return res;
        }
        catch (Exception e){
            return -1;
        }

    }

    @Override
    public int deleteTags(int offerId, int keywordId) {
        try {
            int res = jdbcTemplate.update("DELETE FROM tags WHERE offer_id = ? AND keyword_id = ? ", offerId, keywordId);
            return res;
        }
        catch (Exception e){
            return -1;
        }

    }

    @Override
    public int deleteAllAdvantages(int offerId) {
        try {
            int res = jdbcTemplate.update("DELETE FROM advantages WHERE offer_id = ?", offerId);
            return res;
        }
        catch (Exception e){
            return -1;
        }

    }

    @Override
    public int deleteAllTags(int offerId) {
        try {
            int res = jdbcTemplate.update("DELETE FROM tags WHERE offer_id = ? ", offerId);
            return res;
        }
        catch (Exception e){
            return -1;
        }

    }

    @Override
    public void bindKeyword(int offerId, int keywordId) {
        try {
            jdbcTemplate.update("INSERT INTO tags ( keyword_id , offer_id) \n" +
                            "VALUES (?, ?)",
                    keywordId,
                    offerId
            );

        }
        catch (Exception e){

        }

    }

    @Override
    public void bindAdvantage(int offerId, int advantageId) {
        try {
            jdbcTemplate.update("INSERT INTO advantages ( advantage_id , offer_id) \n" +
                            "VALUES (?, ?)",
                    advantageId,
                    offerId
            );

        }
        catch (Exception e){

        }

    }

    @Override
    public List<Offer> outdatedOffers(){
        try {
            return jdbcTemplate.query("select * from offer where date_end < current_date", rowMapper.getRowMapperOffer());
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public void delete(List<Offer> offers){
        for (Offer offer : offers){
            delete(offer.getOfferId());
        }
    }

    @Override
    public void bindKeywords(Offer offer, List<Keyword> keywords) {
        for (Keyword keyword : keywords){
            bindKeyword(offer.getOfferId(),keyword.getKeywordId());
        }
    }

    @Override
    public List<Offer> findAllFromUser(int userId) {
        try {
            return jdbcTemplate.query("select * from offer where user_id = ?", rowMapper.getRowMapperOffer(),userId);
        }
        catch (Exception e) {
            return null;
        }
    }


}
