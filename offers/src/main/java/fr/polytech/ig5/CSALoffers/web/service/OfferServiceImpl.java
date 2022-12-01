package fr.polytech.ig5.CSALoffers.web.service;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDao;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements  OfferService{
    @Autowired
    OfferDao offerDao = new OfferDaoImpl();

    @Override
    public List<Offer> findAll() {
        return offerDao.findAll();
    }

    @Override
    public Offer findById(int id) {
        return offerDao.findById(id);
    }

    @Override
    public Offer save(Offer offer) {
        return offerDao.save(offer);
    }

    @Override
    public Offer update(Offer offer) {
        return offerDao.update(offer);
    }

    @Override
    public int delete(int id) {
        return offerDao.delete(id);
    }

    @Override
    public List<Keyword> findAllKeyword() {
        return offerDao.findAllKeyword();
    }

    @Override
    public List<Keyword> findAllKeyword(int offerId) {
        return offerDao.findAllKeyword(offerId);
    }

    @Override
    public List<Advantage> findAllAdvantage() {
        return offerDao.findAllAdvantage();
    }

    @Override
    public List<Advantage> findAllAdvantage(int offerId) {
        return offerDao.findAllAdvantage(offerId);
    }

    @Override
    public int deleteAdvantages(int offerId, int advantageId) {
        return deleteAdvantages(offerId,advantageId);
    }

    @Override
    public int deleteAllAdvantages(int offerId) {
        return offerDao.deleteAllAdvantages(offerId);
    }

    @Override
    public int deleteTags(int offerId, int keywordId) {
        return offerDao.deleteTags(offerId, keywordId);
    }

    @Override
    public int deleteAllTags(int offerId) {
        return offerDao.deleteAllTags(offerId);
    }

    @Override
    public List<Offer> outdatedOffers() {
        return offerDao.outdatedOffers();
    }

    @Override
    public void delete(List<Offer> offers) {
        offerDao.delete(offers);
    }

    @Override
    public void bindKeywords(int offerId, List<Keyword> keywords) {
        offerDao.bindKeywords(offerId, keywords);
    }

    @Override
    public void bindAdvantages(int offerId, List<Advantage> advantages) {
        offerDao.bindAdvantages(offerId,advantages);
    }

    @Override
    public List<Offer> findAllFromUser(int userId) {
        return offerDao.findAllFromUser(userId);
    }
}
