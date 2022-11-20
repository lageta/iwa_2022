package fr.polytech.ig5.CSALRecommendation.web.service;

import fr.polytech.ig5.CSALRecommendation.model.Offer;
import fr.polytech.ig5.CSALRecommendation.web.dto.MailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    @Autowired
    private MailService mailService;

    @KafkaListener(topics = "New_Offer", groupId = "group_json",
            containerFactory = "offerKafkaListenerFactory")
    public void consume(Offer offer) {
        System.out.println("Consumed JSON Message: " + offer.getTitle());
    }
}
