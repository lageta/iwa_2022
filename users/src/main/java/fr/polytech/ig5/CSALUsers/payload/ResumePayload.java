package fr.polytech.ig5.CSALUsers.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.polytech.ig5.CSALUsers.jdbc.model.Resume;

public class ResumePayload {

    private final int user_id;
    private final String title;
    private final String description;

    @JsonCreator
    public ResumePayload(@JsonProperty("user_id") int userId,
                          @JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.user_id = userId;
        this.title = title;
        this.description = description;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
