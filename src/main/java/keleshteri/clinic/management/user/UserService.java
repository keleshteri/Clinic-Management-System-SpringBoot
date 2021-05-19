package keleshteri.clinic.management.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

//    public UserDetails loadUserById(Long id) {
//        userRepository.findById(id)
//                .orElseThrow(() ->
//                        new UsernameNotFoundException(
//                                String.format(USER_NOT_FOUND_MSG, id)));
//    }
    ///

    public List<User> all(){
        return userRepository.findAll();
    }


    public void create(User user){
        userRepository.save(user);
    }


    public void seeder(){
        if (userRepository.count() == 0) {

            User tom = new User("tom",passwordEncoder.encode("password"),null);
            userRepository.save(tom);
        }
        System.out.println(userRepository.count());
    }


}
