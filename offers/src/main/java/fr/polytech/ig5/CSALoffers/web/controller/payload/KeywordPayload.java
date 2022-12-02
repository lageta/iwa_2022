package fr.polytech.ig5.CSALoffers.web.controller.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.ig5.CSALoffers.model.Advantage;
import fr.polytech.ig5.CSALoffers.model.Keyword;
import fr.polytech.ig5.CSALoffers.model.Offer;

import java.util.Date;
import java.util.List;

public class KeywordPayload {

    private final List<Keyword> keywords;


    public KeywordPayload(@JsonProperty("keywords") List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public List<Keyword> getKeywords(){
        return keywords;
    }

}
