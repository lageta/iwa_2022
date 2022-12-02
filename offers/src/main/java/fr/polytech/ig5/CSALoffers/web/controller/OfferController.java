package fr.polytech.ig5.CSALoffers.web.controller;

import fr.polytech.ig5.CSALoffers.Action;
import fr.polytech.ig5.CSALoffers.RightChecker;
import fr.polytech.ig5.CSALoffers.Role;
import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.controller.payload.CreatePayload;
import fr.polytech.ig5.CSALoffers.web.controller.payload.KeywordPayload;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDao;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import fr.polytech.ig5.CSALoffers.web.service.OfferService;
import fr.polytech.ig5.CSALoffers.web.service.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Objects;

@RestController
@EnableWebMvc
public class OfferController {

    @Autowired
    OfferService offerService = new OfferServiceImpl();

    @Autowired
    private KafkaTemplate<String, Offer> kafkaTemplate;

    private static final String TOPIC = "New_Offer";

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> offers( @RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.READ_OFFER, Role.valueOf(role))) return new ResponseEntity<>( HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        List<Offer> offers = offerService.findAll();
        if (offers == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return  new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/offers/user/{userId}")
    public ResponseEntity<List<Offer>> offersFromUser(@PathVariable int userId, @RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.READ_OFFER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Offer> offers = offerService.findAllFromUser(userId);
        if (offers ==  null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/offer/{id}")
    public ResponseEntity<Offer> offer(@PathVariable int id, @RequestHeader("role") String role) {
        try {
            if (!RightChecker.CheckRight(Action.READ_OFFER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Offer offer = offerService.findById(id);
        if (offer ==  null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping("/offer/{offerId}/keywords")
    public ResponseEntity<List<Keyword>> keywords(@PathVariable int offerId, @RequestHeader("role") String role) {
        try {
            if (!RightChecker.CheckRight(Action.READ_KEYWORD, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Keyword> keywords = offerService.findAllKeyword(offerId);
        if (keywords == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    @GetMapping("/offer/{advantageId}/advantages")
    public ResponseEntity<List<Advantage>> advantages(@PathVariable int advantageId, @RequestHeader("role") String role) {
    try {

        if (!RightChecker.CheckRight(Action.READ_Advantage, Role.valueOf(role)))
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }catch (IllegalArgumentException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
        List<Advantage> advantages = offerService.findAllAdvantage(advantageId);
        if (advantages == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(advantages, HttpStatus.OK);
    }

    @GetMapping("/keywords")
    public ResponseEntity<List<Keyword>> keywords( @RequestHeader("role") String role) {
        try {
            if (!RightChecker.CheckRight(Action.READ_KEYWORD, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Keyword> keywords = offerService.findAllKeyword();
        if (keywords == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    @GetMapping("/keywords/user/{userId}")
    public List<Keyword> keywordsOfUser(@PathVariable int userId) {
        return  offerService.findAllKeywordOfUser(userId);
    }

    @PostMapping("/keywords/user/{userId}")
    public void bindKeywordsToUser(@PathVariable int userId, @RequestBody KeywordPayload keywords){
        for(Keyword keyword : keywords.getKeywords()) offerService.bindKeywordToUser(keyword, userId);
    }
    @DeleteMapping("/keywords/user/{userId}")
    public void deleteKeywordsOfUser(@PathVariable int userId){
        offerService.deleteKeywordsOfUser(userId);
    }

    @GetMapping("/advantages")
    public ResponseEntity<List<Advantage>> advantages(@RequestHeader("role") String role) {
        try {
            if (!RightChecker.CheckRight(Action.READ_Advantage, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Advantage> advantages =  offerService.findAllAdvantage();
         if (advantages == null) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         return new ResponseEntity<>(advantages, HttpStatus.OK);
    }


    @PostMapping(value = "/offer")
    public ResponseEntity<Offer> create(@RequestBody CreatePayload payload, @RequestHeader("role") String role) {
        try {
            if (!RightChecker.CheckRight(Action.CREATE_OFFER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Offer offerCreated = offerService.save(payload.getOffer());
        if (offerCreated == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (payload.getKeywords() != null) {
            offerService.bindKeywords(offerCreated.getOfferId(), payload.getKeywords());
            if (offerCreated != null) {
                kafkaTemplate.send(TOPIC, offerCreated);
            }
        }

        if (payload.getAdvantages() != null){
            offerService.bindAdvantages(offerCreated.getOfferId(), payload.getAdvantages());
        }

        return new ResponseEntity<>(offerCreated, HttpStatus.CREATED);
    }



    @PutMapping(value = "/offer/{offerId}")
    public ResponseEntity<Offer> update (@PathVariable int offerId,@RequestBody CreatePayload payload, @RequestHeader("role") String role) {
        try {
            if (!RightChecker.CheckRight(Action.UPDATE_OFFER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Offer offer = payload.getOffer();
        if (offer == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
        offer.setOfferId(offerId);
        List<Keyword> keywords = payload.getKeywords();
        if (keywords != null){
            offerService.deleteAllTags(offerId);
            if (keywords.size() > 0){
                offerService.bindKeywords(offerId, keywords);
            }
        }
        List<Advantage> advantages = payload.getAdvantages();
        if (advantages != null){
            offerService.deleteAllAdvantages(offerId);
            if (advantages.size() > 0){
                offerService.bindAdvantages(offerId, advantages);
            }
        }
        Offer offerUpdated =  offerService.update(offer);
        if (offerUpdated == null ) return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(offerUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/offer/{id}")
    public ResponseEntity<String> delete(@PathVariable int id, @RequestHeader("role") String role){
        try {
            if (!RightChecker.CheckRight(Action.DELETE_OFFER, Role.valueOf(role)))
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        int res = offerService.delete(id);
         if (res < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         if (res == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/offer/{offerId}/keyword/{keywordId}")
    public ResponseEntity<String> deleteKeyword(@PathVariable int offerId, @PathVariable int keywordId, @RequestHeader("role") String role){
      try {
          if (!RightChecker.CheckRight(Action.UPDATE_OFFER, Role.valueOf(role)))
              return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }catch (IllegalArgumentException e) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
        int res = offerService.deleteTags(offerId, keywordId);
        if ( res < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (res == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity(null, HttpStatus.OK);
    }
    @DeleteMapping("/offer/{offerId}/advantage/{advantageId}")
    public ResponseEntity<String> deleteAdvantage(@PathVariable int offerId, @PathVariable int advantageId, @RequestHeader("role") String role){
       try {
           if (!RightChecker.CheckRight(Action.UPDATE_OFFER, Role.valueOf(role)))
               return new ResponseEntity<>(HttpStatus.FORBIDDEN);
       }catch (IllegalArgumentException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        int res = offerService.deleteAdvantages(offerId, advantageId);
        if ( res < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (res  == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity(null, HttpStatus.OK);
    }
    @DeleteMapping("/offer/{offerId}/keywords")
    public ResponseEntity<String> deleteKeyword(@PathVariable int offerId, @RequestHeader("role") String role){
        try {if (!RightChecker.CheckRight(Action.UPDATE_OFFER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int res = offerService.deleteAllTags(offerId);
        if (res < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (res == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity(null, HttpStatus.OK);
    }
    @DeleteMapping("/offer/{offerId}/advantages")
    public ResponseEntity<String> deleteAdvantage(@PathVariable int offerId, @RequestHeader("role") String role){
       try { if (!RightChecker.CheckRight(Action.UPDATE_OFFER, Role.valueOf(role))) return new ResponseEntity<>( HttpStatus.FORBIDDEN);
       }catch (IllegalArgumentException e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        int res = offerService.deleteAllAdvantages(offerId);
        if (res < 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (res == 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/offers/outdated")
    public ResponseEntity<String> deleteOutdated( @RequestHeader("role") String role){
      try {  if (!RightChecker.CheckRight(Action.DELETE_OFFER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }catch (IllegalArgumentException e) {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
        offerService.delete(offerService.outdatedOffers());
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
