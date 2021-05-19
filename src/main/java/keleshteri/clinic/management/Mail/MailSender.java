package keleshteri.clinic.management.Mail;

import javax.mail.MessagingException;

public interface  MailSender {
    void send(Mail mail) throws MessagingException;
}
