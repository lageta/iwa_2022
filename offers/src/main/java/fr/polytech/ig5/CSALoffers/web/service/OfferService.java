package fr.polytech.ig5.CSALoffers.web.service;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;

import java.util.List;

public interface OfferService {


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


    List<Offer> outdatedOffers();

    void delete(List<Offer> offers);

    void bindKeywords(int offerId, List<Keyword> keywords);


    void bindAdvantages(int offerId, List<Advantage> advantages);


    List<Offer> findAllFromUser(int userId);
}
