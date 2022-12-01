package fr.polytech.ig5.CSALaffect.services;

import fr.polytech.ig5.CSALaffect.dao.AffectJdbcImpl;
import fr.polytech.ig5.CSALaffect.model.Affect;
import fr.polytech.ig5.CSALaffect.model.Offer;
import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.repository.AffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AffectServiceImpl implements AffectService {

    @Autowired
    AffectRepository affectRepository;


    @Autowired
    AffectJdbcImpl affectJdbc;

    @Override
    public Flux<User> findAllUsersByOfferId(int offerId){
        return affectRepository.findAllUsersByOfferId(offerId);
    }

    @Override
    public Flux<Offer> findAllOffersByUserId(int userId){
        return affectRepository.findAllOffersByUserId(userId);
    };

    @Override
    public Flux<Affect> findAll(){
      return affectRepository.findAll();
    };

    @Override
    public Mono<Affect> save(Affect affect){
        return affectRepository.save(affect);
    };

    @Override
    public Mono<Void> delete(Affect affect){
        return affectRepository.delete(affect);
    }

    @Override
    public int findNbjobs(int offerId) {
        return affectJdbc.findNbjobs(offerId);
    }

    @Override
    public int findNbAffected(int offerId) {
        return affectJdbc.findNbAffected(offerId);
    }

    @Override
    public Affect create(int offerId, int userId) {
        return affectJdbc.create(offerId,userId);
    }

    @Override
    public Affect setIsAccepted(int offerId, int userid, boolean bool) {
        return affectJdbc.setIsAccepted(offerId,userid,bool);
    }

    @Override
    public int delete(int offerId, int userId) {
        return affectJdbc.delete(offerId,userId);
    }






}
