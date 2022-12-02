package fr.polytech.ig5.CSALoffers.web.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;

import java.util.Date;
import java.util.List;

public class CreatePayload {

    private final Offer offer;
    private final List<Keyword> keywords;
    private final List<Advantage> advantages;


    public CreatePayload(@JsonProperty("userId") int user_id, @JsonProperty("address") String address,
                         @JsonProperty("title") String title, @JsonProperty("description") String description,
                         @JsonProperty("startingDate") Date sDate, @JsonProperty("dateEnd") Date eDate,
                         @JsonProperty("nbjobs") int nbjobs, @JsonProperty("salary") int salary,
                         @JsonProperty("keywords") List<Keyword> keywords,
                        @JsonProperty("advantages") List<Advantage> advantages) {
                            this.offer = new Offer(user_id, address, title, description, sDate, eDate, nbjobs, salary);
                            this.keywords = keywords;
                            this.advantages = advantages;
    }

    public Offer getOffer(){
        return offer;
    }

    public List<Keyword> getKeywords(){
        return keywords;
    }

    public List<Advantage> getAdvantages() {
        return advantages;
    }
}
