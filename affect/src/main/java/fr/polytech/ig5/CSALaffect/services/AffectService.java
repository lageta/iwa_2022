package fr.polytech.ig5.CSALaffect.services;

import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.model.Offer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AffectService {

    Flux<User> findAllUsersByOfferId(int offerId);
    Flux<Offer> findAllOffersByUserId(int userId);
    Flux<Affect> findAll();
    Mono<Affect> save(Affect affect);
    Mono<Void> delete(Affect affect);
    int findNbjobs(int offerId);
    int findNbAffected(int offerId);
    Affect create(int offerId, int userId);
    Affect setIsAccepted(int offerId, int userid, boolean bool);
    int delete(int offerId, int userId);
}
