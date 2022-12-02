package fr.polytech.ig5.CSALRecommendation.web.controller;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.model.User;
import fr.polytech.ig5.CSALRecommendation.web.dao.RecommendationDao;
import fr.polytech.ig5.CSALRecommendation.web.dto.MailDto;
import fr.polytech.ig5.CSALRecommendation.web.service.MailService;
import fr.polytech.ig5.CSALRecommendation.web.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecommendationController {
    @Autowired
    RecommendationService recommendationService;

    @Autowired
    private MailService mailService;

    @GetMapping("/recommendate/{userId}")
    public List<Offer> recommendateOffers(@PathVariable int userId){
        return  recommendationService.recommendateOffers(userId);
    }
    @GetMapping("/recommendate/{offerId}")
    public List<User> recommendateUsers(@PathVariable int offerId){
        return  recommendationService.recommendateUsers(offerId);
    }

    @GetMapping("/mail")
    public String mail(){
        String recipient = "axel.laget@gmail.com";
        String body = "test";
        String subject = "This new offer correspond to your profile !";
        MailDto mail = new MailDto();
        mail.setRecipient(recipient);
        mail.setBody(body);
        mail.setSubject(subject);
        String emailStatus = mailService.sendMail(mail);
        return emailStatus;
    }
}
