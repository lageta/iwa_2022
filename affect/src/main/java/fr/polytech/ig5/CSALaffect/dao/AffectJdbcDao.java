package fr.polytech.ig5.CSALaffect.dao;

import fr.polytech.ig5.CSALaffect.model.Affect;

public interface AffectJdbcDao {
    int findNbjobs(int offerId);
    int findNbAffected(int offerId);
    Affect create(int offerId, int userId);
    Affect setIsAccepted(int offerId, int userid, boolean bool);
    int delete(int offerId, int userId);
}
