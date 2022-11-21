package fr.polytech.ig5.CSALRecommendation.web.dao;
import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.model.User;

import java.util.List;

public interface  RecommendationDao {

    List<Offer> recommendateOffers(int userid);
    List<User> recommendateUsers(int offerid);
}
