package fr.polytech.ig5.CSALRecommendation.web.service;


        import java.io.File;
        import javax.mail.internet.MimeMessage;

        import fr.polytech.ig5.CSALRecommendation.web.dto.MailDto;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.core.io.FileSystemResource;
        import org.springframework.mail.SimpleMailMessage;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.MimeMessageHelper;
        import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public String sendMail(MailDto emailDto) {
        try {
            SimpleMailMessage simpleMailMessage = preparingMailMessage(emailDto);
            javaMailSender.send(simpleMailMessage);
            return "Mail has been sent successfully...";
        }catch (Exception exception){
            exception.printStackTrace();
            return "Error occurred while sending email";
        }
    }

    @Override
    public String sendMailWithAttachment(MailDto emailDto) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailDto.getRecipient());
            mimeMessageHelper.setText(emailDto.getBody());
            mimeMessageHelper.setSubject(emailDto.getSubject());
            mimeMessage.setContent("<!-- #######  HEY, I AM THE SOURCE EDITOR! #########-->\n" +
                            "<h1 style=\"color: #5e9ca0;\">A new recommendation <span style=\"color: #2b2301;\">has arrived</span> ! <img src=\"https://html-online.com/editor/tiny4_9_11/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /><img src=\"https://html-online.com/editor/tiny4_9_11/plugins/emoticons/img/smiley-cool.gif\" alt=\"cool\" /></h1>\n" +
                            "<h2 style=\"color: #2e6c80;\">Check this out !!! -&gt; "+"<a href=\"/offer/"+emailDto.getOffer().getOfferId()+"\">Voir l'offre</a>"+"</h2>\n" +
                            "<p>&nbsp;</p>\n" +
                            "<table style=\"border-collapse: collapse; width: 100%; height: 232px;\" border=\"1\">\n" +
                            "<tbody>\n" +
                            "<tr style=\"height: 78px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 78px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">offerId -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 78px; text-align: center;\">"+emailDto.getOffer().getOfferId()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 46px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 46px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">adress -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 46px; text-align: center;\">"+emailDto.getOffer().getAddress()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 18px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 18px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">title -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 18px; text-align: center;\">"+emailDto.getOffer().getTitle()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 18px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 18px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">description -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 18px; text-align: center;\">"+emailDto.getOffer().getDescription()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 18px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 18px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">starting date -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 18px; text-align: center;\">"+emailDto.getOffer().getStartingDate()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 18px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 18px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">End -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 18px; text-align: center;\">"+emailDto.getOffer().getDateEnd()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 18px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 18px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff;\">number of jobs -&gt;</span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 18px; text-align: center;\">"+emailDto.getOffer().getNbjobs()+"</td>\n" +
                            "</tr>\n" +
                            "<tr style=\"height: 18px;\">\n" +
                            "<td style=\"width: 47.7169%; height: 18px; text-align: center;\">\n" +
                            "<p><span style=\"text-decoration: underline;\"><strong><span style=\"color: #0000ff; text-decoration: underline;\">salary <span style=\"color: #0000ff;\">-&gt;</span></span></strong></span></p>\n" +
                            "</td>\n" +
                            "<td style=\"width: 52.2831%; height: 18px; text-align: center;\">"+emailDto.getOffer().getSalary()+"</td>\n" +
                            "</tr>\n" +
                            "</tbody>\n" +
                            "</table>\n" +
                            "<p><strong>Hurry up and apply !!!!!</strong></p>>",
                    "text/html");
            javaMailSender.send(mimeMessage);// Sending the mail
        }catch (Exception exception){
            return "Exception occurred while sending email with attachments...";
        }
        return "Mail with attachment has been send successfully...";
    }



    private SimpleMailMessage preparingMailMessage(MailDto emailDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(emailDto.getRecipient());
        simpleMailMessage.setText(emailDto.getBody());
        simpleMailMessage.setSubject(emailDto.getSubject());
        return simpleMailMessage;
    }


}