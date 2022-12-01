package fr.polytech.ig5.CSALUsers.payload;

import java.util.StringJoiner;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Defines a JSON Object with the attributes :
 * @param username String
 * @param password String
 * @param role String
 * @param zone String
 */
public class RatePayload {

    private final int origin_id;
    private final int score;

    @JsonCreator
    public RatePayload(@JsonProperty("userId") int id, @JsonProperty("score") int score) {
        this.origin_id = id;
        this.score = score;
    }

    public int getOrigin_id() {
        return origin_id;
    }

    public int getScore() {
        return score;
    }

    
}
