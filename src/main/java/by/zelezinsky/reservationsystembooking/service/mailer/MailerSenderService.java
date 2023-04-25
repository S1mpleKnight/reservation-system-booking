package by.zelezinsky.reservationsystembooking.service.mailer;

public interface MailerSenderService {

    void send(String recipient, String subject, String body);
}
