package fr.polytech.ig5.CSALRecommendation.web.service;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.model.User;
import fr.polytech.ig5.CSALRecommendation.web.dao.RecommendationDao;
import fr.polytech.ig5.CSALRecommendation.web.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Multipart;
import java.util.List;

@Service
public class KafkaConsumer {
    @Autowired
    private MailService mailService;

    @Autowired
    RecommendationDao recommendationDao;

    @KafkaListener(topics = "New_Offer", groupId = "group_json",
            containerFactory = "offerKafkaListenerFactory")
    public void consume(Offer offer) {
        System.out.println("Consumed JSON Message: " + offer.getTitle() + " "+ offer.getOfferId());
        for (User user : recommendationDao.recommendateUsers(offer.getOfferId())){
            String userMail = user.getMail();
            String recipient = userMail;
            String body = "This new offer is matching your profile \n" +"\n" + offer.toString(); // TODO
            String subject = "This new offer correspond to your profile !";
            MailDto mail = new MailDto();
            mail.setRecipient(recipient);
            mail.setBody(body);
            mail.setSubject(subject);
            mail.setOffer(offer);
            System.out.println("Sending to: " + userMail);
            String emailStatus = mailService.sendMailWithAttachment(mail);
        }

    }
}
