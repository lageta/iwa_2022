package fr.polytech.ig5.CSALoffers.web.controller;

import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.controller.payload.CreatePayload;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDao;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    //TODO Liste des offres d'un autheur
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
        offerDao.bindKeywords(offerCreated, payload.getKeywords());
        if (offerCreated != null)  {kafkaTemplate.send(TOPIC, offerCreated);}
        return offerCreated;
    }



    @PutMapping(value = "/offer")
    public Offer update (@RequestBody Offer offer) {
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
