package fr.polytech.ig5.CSALRecommendation.web.dao;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.model.User;
import fr.polytech.ig5.CSALRecommendation.web.rowMapper.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendationDaoImpl implements  RecommendationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper rowMapper = new RowMapper();

    @Override
    public List<Offer> recommendateOffers(int userid){
        try {
            return jdbcTemplate.query("Select offer.* from tags join \n" +
                    "(select * from interest where user_id = ?) as t1\n" +
                    "on tags.keyword_id = t1.keyword_id \n" +
                    "join offer on offer.offer_id = tags.offer_id\n" +
                    "group by offer.offer_id order by count(t1.keyword_id) DESC LIMIT 5\n" +
                    "\n", rowMapper.getRowMapperOffer(), userid);
        }
        catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<User> recommendateUsers(int userid){
        try {
            return jdbcTemplate.query("Select * from ("
                          + " Select users.*,count(t1.keyword_id) from interest join"
                          + " (select * from tags where offer_id = ?) as t1"
                          + " on interest.keyword_id = t1.keyword_id"
                          + " join users on users.user_id = interest.user_id"
                          + " group by users.user_id ) as reco where count>=3"
                    , rowMapper.getRowMapperUser(), userid);
        }
        catch (Exception e) {
            return null;
        }
    }
}
