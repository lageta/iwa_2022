package fr.polytech.ig5.CSALRecommendation.web.dto;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

import javax.mail.Multipart;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {

    private String recipient;

    private String body;

    private String subject;

    private String attachment;

    private Offer offer;


    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public MailDto(String recipient, String body, String subject) {
        this.recipient = recipient;
        this.body = body;
        this.subject = subject;
    }



}