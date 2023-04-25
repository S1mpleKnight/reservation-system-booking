package by.zelezinsky.reservationsystembooking.service.mailer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailerSenderServiceImpl implements MailerSenderService {

    private final JavaMailSender mailSender;

    @Override
    public void send(String recipient, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(body);
        try {
            mailSender.send(message);
            log.info("Message was successfully sent to the ".concat(recipient));
        } catch (Exception e) {
            log.error("Message sending failed: ".concat(e.getMessage()));
        }

    }
}
