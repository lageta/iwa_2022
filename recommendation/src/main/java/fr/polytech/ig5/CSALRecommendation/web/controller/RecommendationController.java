package fr.polytech.ig5.CSALRecommendation.web.controller;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.web.dao.RecommendationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {
    @Autowired
    RecommendationDao recommendationDao;

    @GetMapping("/recommendate/{userId}")
    public List<Offer> recommendate(@PathVariable int userId){
        return  recommendationDao.recommendate(userId);
    }
}
