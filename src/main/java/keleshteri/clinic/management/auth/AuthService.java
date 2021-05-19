package keleshteri.clinic.management.auth;

import keleshteri.clinic.management.auth.request.RegistrationRequest;
import keleshteri.clinic.management.user.User;
import keleshteri.clinic.management.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }




}
