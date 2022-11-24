package fr.polytech.ig5.CSALaffect.services;

import fr.polytech.ig5.CSALaffect.model.User;
import fr.polytech.ig5.CSALaffect.repository.AffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AffectService {

    @Autowired
    AffectRepository affectRepository;

    public Flux<User> findAllUsersByOfferId(int offerId){
        return affectRepository.findAllUsersByOfferId(offerId);
    }
}
