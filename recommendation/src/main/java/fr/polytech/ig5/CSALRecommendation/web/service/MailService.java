package fr.polytech.ig5.CSALRecommendation.web.service;


import fr.polytech.ig5.CSALRecommendation.web.dto.MailDto;

public interface MailService {

    String sendMail(MailDto emailDto);

    String sendMailWithAttachment(MailDto emailDto);

}