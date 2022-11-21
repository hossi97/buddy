package ho.buddy.config.data;

import ho.buddy.domain.Member;
import ho.buddy.domain.Role;
import ho.buddy.repository.UserRepository;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }

    @Transactional
    public void init() {
        String password = passwordEncoder.encode("1234");
        Member member = new Member("1234", password, "1234", Role.USER);

        userRepository.save(member);
    }
}

//public class DataInit {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @PostConstruct
//    @Transactional
//    public void init() {
//        String password = passwordEncoder.encode("1234");
//        Member member = new Member("1234", password, "1234", Role.USER);
//
//        userRepository.save(member);
//    }
//}
