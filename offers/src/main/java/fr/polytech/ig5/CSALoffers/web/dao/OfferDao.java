package fr.polytech.ig5.CSALoffers.web.dao;

import fr.polytech.ig5.CSALoffers.model.Offer;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OfferDao {

    List<Offer> findAll();
    Offer findById(int id);
    Offer save(Offer offer);

    Offer update(Offer offer);

    int  delete(int id);
}
