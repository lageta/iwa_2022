package fr.polytech.ig5.CSALaffect.dao;

import fr.polytech.ig5.CSALaffect.RowMapper.RowMapper;
import fr.polytech.ig5.CSALaffect.model.Affect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AffectJdbcImpl implements AffectJdbcDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper rowMapper = new RowMapper();

    @Override
    public int findNbjobs(int offerId) {
        try {
            return jdbcTemplate.query("select nb_jobs from offer where offer_Id = ?", rowMapper.getRowMapperNbJobs(), offerId).get(0);
        }
        catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int findNbAffected(int offerId) {
        try {
            return jdbcTemplate.query("select count(*) from affect where offer_Id = ?", rowMapper.getRowMapperCount(), offerId).get(0);
        }
        catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Affect create(int offerId, int userId) {
                try {
                    return jdbcTemplate.queryForObject("INSERT INTO affect ( offer_id, user_id) " +
                            "VALUES (?, ?) RETURNING *", rowMapper.getRowMapperAffect(), offerId, userId);
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }


    }

    @Override
    public Affect setIsAccepted(int offerId, int userId, boolean bool) {
        try {
            return jdbcTemplate.query("UPDATE affect \n" +
                    "SET is_accepted = ? \n" +
                    "WHERE offer_id = ? and user_id = ? \n" +
                    "RETURNING *", rowMapper.getRowMapperAffect(), bool, offerId, userId).get(0);
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }    }

    @Override
    public int delete(int offerId, int userId) {
        try {
            return jdbcTemplate.update(            "Delete from affect where offer_id = ? and user_id = ?"
            , offerId,userId);

        }
        catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }
}
