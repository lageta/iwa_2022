package fr.polytech.ig5.CSALaffect.web;

import fr.polytech.ig5.CSALaffect.dao.AffectJdbcImpl;
import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.services.AffectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@AllArgsConstructor
public class AffectController {

   @Autowired
    AffectService affectService;

   @Autowired
   AffectJdbcImpl affectJdbc;

    @GetMapping(value = "/affect/offer/{offerId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<User> getUsers(@PathVariable int offerId) {
        return affectService.findAllUsersByOfferId(offerId).delayElements(Duration.ofSeconds(2L));
    }
    @GetMapping(value = "/affect/user/{userId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<User> getOffers(@PathVariable int userId) {
        return affectService.findAllOffersByUserId(userId).delayElements(Duration.ofSeconds(2L));
    }

    @GetMapping("/nbjobs/{offerId}")
    int getNbJobs(@PathVariable int offerId) {
        return affectJdbc.findNbjobs(offerId);
    }

    @GetMapping("/nbaffected/{offerId}")
    int getNbAffected(@PathVariable int offerId) {
        return affectJdbc.findNbAffected(offerId);
    }


    @PostMapping(value = "/affect/{userId}/{offerId}")
    Affect postAffect(@PathVariable int userId, @PathVariable int offerId){
        if (affectJdbc.findNbAffected(offerId) < affectJdbc.findNbjobs(offerId) ){
            return affectJdbc.create(offerId, userId);
        }
        return null;
    };

    @PutMapping (value = "/affect/{userId}/{offerId}/{bool}")
    Affect putAffect(@PathVariable int userId, @PathVariable int offerId, @PathVariable boolean bool){
        return affectJdbc.setIsAccepted(offerId,userId,bool);
    };

    @DeleteMapping(value = "/affect/{userId}/{offerId}")
    int delete(@PathVariable int userId, @PathVariable int offerId){
        return affectJdbc.delete(offerId,userId);
    }
}