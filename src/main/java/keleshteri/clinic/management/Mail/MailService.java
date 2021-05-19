package keleshteri.clinic.management.Mail;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
//@AllArgsConstructor
public class MailService  implements MailSender{

    private final static Logger LOGGER = LoggerFactory
            .getLogger(MailService.class);

    private final JavaMailSender mailSender;

    private final Configuration templateConfiguration;

    @Value("${app.velocity.templates.location}")
    private String basePackagePath;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @Value("${app.token.password.reset.duration}")
    private Long expiration;
    @Autowired
    public MailService(JavaMailSender mailSender, Configuration templateConfiguration) {
        this.mailSender = mailSender;
        this.templateConfiguration = templateConfiguration;
    }

    /**
     *  Send the  EmailVerification user's mail
     */
    public void sendEmailVerification(String emailVerificationUrl, String to)
            throws IOException, TemplateException, MessagingException {
        Mail mail = new Mail();
        mail.setSubject("Email Verification [Team CEP]");
        mail.setTo(to);
        mail.setFrom(mailFrom);
        mail.getModel().put("userName", to);
        mail.getModel().put("userEmailTokenVerificationLink", emailVerificationUrl);

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("email-verification.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        send(mail);
    }

    /**
     * Setting the mail parameters.Send the reset link to the respective user's mail
     */
    public void sendResetLink(String resetPasswordLink, String to)
            throws IOException, TemplateException, MessagingException {
        Long expirationInMinutes = TimeUnit.MILLISECONDS.toMinutes(expiration);
        String expirationInMinutesString = expirationInMinutes.toString();
        Mail mail = new Mail();
        mail.setSubject("Password Reset Link [Team CEP]");
        mail.setTo(to);
        mail.setFrom(mailFrom);
        mail.getModel().put("userName", to);
        mail.getModel().put("userResetPasswordLink", resetPasswordLink);
        mail.getModel().put("expirationTime", expirationInMinutesString);

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("reset-link.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        send(mail);
    }

    /**
     * Send an email to the user indicating an account change event with the correct
     * status
     */
    public void sendAccountChangeEmail(String action, String actionStatus, String to)
            throws IOException, TemplateException, MessagingException {
        Mail mail = new Mail();
        mail.setSubject("Account Status Change [Team CEP]");
        mail.setTo(to);
        mail.setFrom(mailFrom);
        mail.getModel().put("userName", to);
        mail.getModel().put("action", action);
        mail.getModel().put("actionStatus", actionStatus);

        templateConfiguration.setClassForTemplateLoading(getClass(), basePackagePath);
        Template template = templateConfiguration.getTemplate("account-activity-change.ftl");
        String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, mail.getModel());
        mail.setContent(mailContent);
        send(mail);
    }


    /**
     * Sends a simple mail as a MIME Multipart message
     */
    @Override
    @Async
    public void send(Mail mail) throws MessagingException {
        try {
           MimeMessage message = mailSender.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

            helper.setTo(mail.getTo());
            helper.setText(mail.getContent(), true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());
            mailSender.send(message);
        } catch (MessagingException e) {
          LOGGER.error("failed to send email", e);
          throw new IllegalStateException("failed to send email");
        }
    }

}
