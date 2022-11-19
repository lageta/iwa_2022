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
            FileSystemResource file = new FileSystemResource(new File(emailDto.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
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