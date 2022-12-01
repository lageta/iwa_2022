package fr.polytech.ig5.CSALRecommendation.web.service;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.model.User;
import fr.polytech.ig5.CSALRecommendation.web.dao.RecommendationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    RecommendationDao recommendationDao;

    @Override
    public List<Offer> recommendateOffers(int userid) {
        return recommendationDao.recommendateOffers(userid);
    }

    @Override
    public List<User> recommendateUsers(int offerid) {
        return recommendationDao.recommendateUsers(offerid);
    }
}
