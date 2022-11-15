package fr.polytech.ig5.CSALoffers.web.dao;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OfferDao {

    List<Offer> findAll();

    Offer findById(int id);

    Offer save(Offer offer);

    Offer update(Offer offer);

    int  delete(int id);

    List<Keyword> findAllKeyword();

    List<Keyword> findAllKeyword(int offerId);

    List<Advantage> findAllAdvantage();

    List<Advantage> findAllAdvantage(int offerId);

    int deleteAdvantages(int offerId, int advantageId);

    int deleteAllAdvantages(int offerId);

    int deleteTags(int offerId, int keywordId);

    int deleteAllTags(int offerId);

    void bindKeyword(int offerId, int keywordId);

    void bindAdvantage(int offerId, int advantageId);
}
