package sk.posam.hoursalpha.application.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sk.posam.hoursalpha.domain.Employee;
import sk.posam.hoursalpha.domain.VerificationToken;
import sk.posam.hoursalpha.domain.service.IEmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    VerificationTokenService verificationTokenService;

    @Autowired
    JavaMailSender javaMailSender;

    @Override
    public void sendVerificationEmailWithToken(Employee employee) throws MessagingException {
        VerificationToken verificationToken = verificationTokenService.findByEmployee(employee);

        String token = verificationToken.getToken();

        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Verifing your email address</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\".container\">\n" +
                "      <h3>Verication for your email adress</h3><br>\n" +
                "      <p>Thank you for signing up. Please click on the button to verify your email address!</p><br>\n" +
                "      <a href=\"[[URL]]\">Click to verify your email </a>\n" +
                "      <h3>See you soon!</h3>\n" +
                "      \n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        body = body.replace("[[URL]]", "http://localhost:4200" +"/verifyEmail/"+token);


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(employee.getEmail());
        helper.setSubject("Email Address Verification!");
        helper.setText(body,true);
        javaMailSender.send(message);
    }
}
