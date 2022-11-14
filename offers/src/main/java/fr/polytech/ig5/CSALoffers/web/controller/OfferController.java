package fr.polytech.ig5.CSALoffers.web.controller;

import fr.polytech.ig5.CSALoffers.model.Offer;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDao;
import fr.polytech.ig5.CSALoffers.web.dao.OfferDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    @Autowired
    OfferDao offerDao = new OfferDaoImpl();

    @GetMapping("/offers")
    public List<Offer> offers(){
        return  offerDao.findAll();
    }

    @GetMapping("/offer/{id}")
    public Offer offer(@PathVariable int id) {
        return  offerDao.findById(id);
    }
    @PostMapping(value = "/offer")
    public Offer create(@RequestBody Offer offer) {
        return offerDao.save(offer);
    }
    @PostMapping(value = "/test")
    public String test() {
        return "Test";
    }
    @PutMapping(value = "/offer")
    public Offer update (@RequestBody Offer offer) {
        return offerDao.update(offer);
    }
    @DeleteMapping("/offer/{id}")
    public void delete(@PathVariable int id){
         offerDao.delete(id);
    }
}
