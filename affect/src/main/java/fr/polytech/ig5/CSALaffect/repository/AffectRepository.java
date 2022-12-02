package fr.polytech.ig5.CSALaffect.repository;

import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.Offer;
import fr.polytech.ig5.CSALaffect.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AffectRepository extends ReactiveCrudRepository<Affect, Integer> {
    @Query(value = "SELECT * FROM users WHERE id IN (SELECT user_id FROM affect where offer_id=:offerId)  ")
    Flux<User> findAllUsersByOfferId(@Param("offerId") int offerId);

    @Query(value = "SELECT * FROM offer WHERE offer_id IN (SELECT offer_id FROM affect where user_id=:userId and is_accepted = false )  ")
    Flux<Offer> findAllOffersByUserId(@Param("userId") int userId);
/*
    @Query(value = "SELECT nb_jobs FROM offer WHERE offer_id =:offerId)  ")
    Mono<Integer> findNbJobs(@Param("offerId") int offerId);

    @Query(value = "SELECT count(*) FROM affect WHERE offer_id =:offerId)  ")
    Mono<Integer> findNbAffected(@Param("offerId") int offerId); */
}
