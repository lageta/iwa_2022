package fr.polytech.ig5.CSALaffect.web;

import fr.polytech.ig5.CSALaffect.Action;
import fr.polytech.ig5.CSALaffect.RightChecker;
import fr.polytech.ig5.CSALaffect.Role;
import fr.polytech.ig5.CSALaffect.dao.AffectJdbcImpl;
import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.Offer;
import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.services.AffectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@AllArgsConstructor
public class AffectController {

   @Autowired
   AffectServiceImpl affectService;


    @GetMapping(value = "/affect/offer/{offerId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<ResponseEntity<User>> getUsers(@PathVariable int offerId) {
        return affectService.findAllUsersByOfferId(offerId)
                .delayElements(Duration.ofSeconds(2L))
                .map(user -> ResponseEntity.ok(user))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @GetMapping(value = "/affect/user/{userId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<ResponseEntity<Offer>> getOffers(@PathVariable int userId) {
        return affectService.findAllOffersByUserId(userId)
                .delayElements(Duration.ofSeconds(2L))
                .map(offer -> ResponseEntity.ok(offer))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/nbjobs/{offerId}")
    ResponseEntity<Integer> getNbJobs(@PathVariable int offerId, @RequestHeader("role") String role) {
        if (!RightChecker.CheckRight(Action.READ_OFFER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        int nbJobs = affectService.findNbjobs(offerId);
        if (nbJobs == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(nbJobs, HttpStatus.OK);
    }

    @GetMapping("/nbaffected/{offerId}")
    ResponseEntity<Integer> getNbAffected(@PathVariable int offerId, @RequestHeader("role") String role) {
        if (!RightChecker.CheckRight(Action.READ_OFFER, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        int nbAffected = affectService.findNbAffected(offerId);
        if (nbAffected == -1) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(nbAffected, HttpStatus.OK);
    }


    @PostMapping(value = "/affect/{userId}/{offerId}")
    ResponseEntity<Affect> postAffect(@PathVariable int userId, @PathVariable int offerId, @RequestHeader("role") String role){
        if (!RightChecker.CheckRight(Action.CREATE_AFFECT, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        if (affectService.findNbAffected(offerId) < affectService.findNbjobs(offerId) ){
            Affect affect = affectService.create(offerId, userId);
            if (affect == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(affect, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping (value = "/affect/{userId}/{offerId}/{bool}")
    ResponseEntity<Affect> putAffect(@PathVariable int userId, @PathVariable int offerId, @PathVariable boolean bool, @RequestHeader("role") String role){
        if (!RightChecker.CheckRight(Action.UPDATE_AFFECT, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        Affect affect = affectService.setIsAccepted(offerId,userId,bool);
        if (affect == null) return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(affect, HttpStatus.OK);
    };

    @DeleteMapping(value = "/affect/{userId}/{offerId}")
    ResponseEntity<Integer> delete(@PathVariable int userId, @PathVariable int offerId, @RequestHeader("role") String role){
        if (!RightChecker.CheckRight(Action.DELETE_AFFECT, Role.valueOf(role))) return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        int res = affectService.delete(offerId, userId);
        if (res <= 0) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}