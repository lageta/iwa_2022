package fr.polytech.ig5.CSALaffect.web;

import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.services.AffectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@AllArgsConstructor
public class AffectController {

   @Autowired
    AffectService affectService;

    @GetMapping(value = "/affect/offer/{offerId}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    Flux<User> getUsers(@PathVariable int offerId) {
        return affectService.findAllUsersByOfferId(offerId).delayElements(Duration.ofSeconds(2L));
    }
}