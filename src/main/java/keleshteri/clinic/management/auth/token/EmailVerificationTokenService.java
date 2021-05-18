package keleshteri.clinic.management.auth.token;

import keleshteri.clinic.management.exception.InvalidTokenRequestException;
import keleshteri.clinic.management.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmailVerificationTokenService {

    private final  EmailVerificationTokenRepository emailVerificationTokenRepository;

    @Autowired
    public EmailVerificationTokenService(EmailVerificationTokenRepository emailVerificationTokenRepository) {
        this.emailVerificationTokenRepository = emailVerificationTokenRepository;
    }

    /**
     * Create an email verification token and persist it in the database which will be
     * verified by the user
     */
    public void createVerificationToken(User user, String token) {

        EmailVerificationToken emailVerificationToken = new EmailVerificationToken();

        emailVerificationToken.setToken(token);
        emailVerificationToken.setTokenStatus(TokenStatus.STATUS_PENDING);
        emailVerificationToken.setUser(user);
        emailVerificationToken.setCreatedAt(LocalDateTime.now());
        emailVerificationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));

        emailVerificationTokenRepository.save(emailVerificationToken);
    }

    /**
     * Updates an existing token in the database with a new expiration
     */
    public EmailVerificationToken updateExistingTokenWithNameAndExpiry(EmailVerificationToken existingToken) {

        existingToken.setTokenStatus(TokenStatus.STATUS_PENDING);
        existingToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));

        return save(existingToken);
    }

    /**
     * Finds an email verification token by the @NaturalId token
     */
    public Optional<EmailVerificationToken> findByToken(String token) {
        return emailVerificationTokenRepository.findByToken(token);
    }

    /**
     * Saves an email verification token in the repository
     */
    public EmailVerificationToken save(EmailVerificationToken emailVerificationToken) {
        return emailVerificationTokenRepository.save(emailVerificationToken);
    }

    /**
     * Generates a new random UUID to be used as the token for email verification
     */
    public String generateNewToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * Verify whether the token provided has expired or not on the basis of the current
     * server time and/or throw error otherwise
     */
    public void verifyExpiration(EmailVerificationToken token) {
        if (token.getExpiresAt().compareTo(LocalDateTime.now()) < 0) {
            throw new InvalidTokenRequestException("Email Verification Token", token.getToken(), "Expired token. Please issue a new request");
        }
    }


//
//    public int setConfirmedAt(String token) {
//        return confirmationTokenRepository.updateConfirmedAt(
//                token, LocalDateTime.now());
//    }
    //
}
