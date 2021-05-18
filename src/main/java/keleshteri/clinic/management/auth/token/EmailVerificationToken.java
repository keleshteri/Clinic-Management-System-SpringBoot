package keleshteri.clinic.management.auth.token;

import keleshteri.clinic.management.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "EMAIL_VERIFICATION_TOKEN")
public class EmailVerificationToken {

    @Id
    @Column(name = "TOKEN_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_token_seq")
    @SequenceGenerator(name = "email_token_seq", allocationSize = 1)
    private Long id;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    //@ManyToOne
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    @Column(name = "TOKEN_STATUS")
    @Enumerated(EnumType.STRING)
    private TokenStatus tokenStatus;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;


    ///Constructor


    public EmailVerificationToken(String token, User user, TokenStatus tokenStatus, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime confirmedAt) {
        this.token = token;
        this.user = user;
        this.tokenStatus = tokenStatus;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
    }
}
