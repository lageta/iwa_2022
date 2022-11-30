package fr.polytech.ig5.CSALaffect.services;

import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.repository.AffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AffectService {

    @Autowired
    AffectRepository affectRepository;

    public Flux<User> findAllUsersByOfferId(int offerId){
        return affectRepository.findAllUsersByOfferId(offerId);
    }


    public Flux<User> findAllOffersByUserId(int userId){
        return affectRepository.findAllOffersByUserId(userId);
    };

    /*public Mono<Integer> findNbJobs(int offerId){
        return affectRepository.findNbJobs(offerId);
    };

    public Mono<Integer> finNbAffected(int offerId){
        return affectRepository.findNbAffected(offerId);
    }*/

    public Flux<Affect> findAll(){
      return affectRepository.findAll();
    };

    public Mono<Affect> save(Affect affect){
        return affectRepository.save(affect);
    };

    public Mono<Void> delete(Affect affect){
        return affectRepository.delete(affect);
    };


}
