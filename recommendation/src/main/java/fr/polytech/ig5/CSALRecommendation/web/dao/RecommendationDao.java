package fr.polytech.ig5.CSALRecommendation.web.dao;
import fr.polytech.ig5.CSALRecommendation.model.Offer;

import java.util.List;

public interface  RecommendationDao {

    List<Offer> recommendate(int userid);
}
