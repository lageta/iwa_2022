package fr.polytech.ig5.CSALaffect.repository;

import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AffectRepository extends ReactiveCrudRepository<Affect, Integer> {
    @Query(value = "SELECT * FROM users WHERE user_id IN (SELECT user_id FROM affect where offer_id=:offerId)  ")
    Flux<User> findAllUsersByOfferId(@Param("offerId") int offerId);
}
