package fr.polytech.ig5.CSALoffers.web.controller;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.controller.payload.CreatePayload;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDao;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    @Autowired
    OfferDao offerDao = new OfferDaoImpl();

    @Autowired
    private KafkaTemplate<String, Offer> kafkaTemplate;

    private static final String TOPIC = "New_Offer";

    @GetMapping("/offers")
    public List<Offer> offers(){
        return  offerDao.findAll();
    }

    @GetMapping("/offers/user/{userId}")
    public List<Offer> offersFromUser(@PathVariable int userId){
        return  offerDao.findAllFromUser(userId);
    }

    @GetMapping("/offer/{id}")
    public Offer offer(@PathVariable int id) {
        return  offerDao.findById(id);
    }

    @GetMapping("/offer/{offerId}/keywords")
    public List<Keyword> keywords(@PathVariable int offerId) {
        return  offerDao.findAllKeyword(offerId);
    }

    @GetMapping("/offer/{advantageId}/advantages")
    public List<Advantage> advantages(@PathVariable int advantageId) {
        return  offerDao.findAllAdvantage(advantageId);
    }

    @GetMapping("/keywords")
    public List<Keyword> keywords() {
        return  offerDao.findAllKeyword();
    }

    @GetMapping("/advantages")
    public List<Advantage> advantages() {
        return  offerDao.findAllAdvantage();
    }

    @PostMapping(value = "/offer/{offerId}/keyword/{keywordId}")
    public void bindKeyword(@PathVariable int offerId, @PathVariable int keywordId) {
         offerDao.bindKeyword(offerId, keywordId);
    }

    @PostMapping(value = "/offer/{offerId}/advantage/{advantageId}")
    public void bindAdvantage(@PathVariable int offerId, @PathVariable int advantageId) {
        offerDao.bindAdvantage(offerId, advantageId);
    }

    @PostMapping(value = "/offer")
    public Offer create(@RequestBody CreatePayload payload) {
        Offer offerCreated = offerDao.save(payload.getOffer());
        if (payload.getKeywords() != null) {
            offerDao.bindKeywords(offerCreated.getOfferId(), payload.getKeywords());
            if (offerCreated != null) {
                kafkaTemplate.send(TOPIC, offerCreated);
            }
        }

        if (payload.getAdvantages() != null){
            offerDao.bindAdvantages(offerCreated.getOfferId(), payload.getAdvantages());
        }

        return offerCreated;
    }



    @PutMapping(value = "/offer/{offerId}")
    public Offer update (@PathVariable int offerId,@RequestBody CreatePayload payload) {
        Offer offer = payload.getOffer();
        if (offer == null) return null;
        offer.setOfferId(offerId);
        List<Keyword> keywords = payload.getKeywords();
        if (keywords != null){
            offerDao.deleteAllTags(offerId);
            if (keywords.size() > 0){
                offerDao.bindKeywords(offerId, keywords);
            }
        }
        List<Advantage> advantages = payload.getAdvantages();
        if (advantages != null){
            offerDao.deleteAllAdvantages(offerId);
            if (advantages.size() > 0){
                offerDao.bindAdvantages(offerId, advantages);
            }
        }
        return offerDao.update(offer);
    }

    @DeleteMapping("/offer/{id}")
    public void delete(@PathVariable int id){
         offerDao.delete(id);
    }

    @DeleteMapping("/offer/{offerId}/keyword/{keywordId}")
    public void deleteKeyword(@PathVariable int offerId, @PathVariable int keywordId){
        offerDao.deleteTags(offerId, keywordId);
    }
    @DeleteMapping("/offer/{offerId}/advantage/{advantageId}")
    public void deleteAdvantage(@PathVariable int offerId, @PathVariable int advantageId){
        offerDao.deleteAdvantages(offerId, advantageId);
    }
    @DeleteMapping("/offer/{offerId}/keywords")
    public void deleteKeyword(@PathVariable int offerId){
        offerDao.deleteAllTags(offerId);
    }
    @DeleteMapping("/offer/{offerId}/advantages")
    public void deleteAdvantage(@PathVariable int offerId){
        offerDao.deleteAllAdvantages(offerId);
    }

    @DeleteMapping("/offers/outdated")
    public void deleteOutdated(){
        offerDao.delete(offerDao.outdatedOffers());
    }

}
