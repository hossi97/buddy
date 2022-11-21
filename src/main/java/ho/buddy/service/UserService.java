package ho.buddy.service;

import static ho.buddy.domain.Role.USER;

import ho.buddy.domain.Member;
import ho.buddy.repository.UserRepository;
import java.security.Security;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Member user) {
        System.out.println("User Service 실행");

        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole(USER);
        userRepository.save(user);

        System.out.println("User Service 실행완료");
    }

    @Transactional
    public Member findMember(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("사용자 정보를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void update(Long id, Member user) {

        Member findUser = findMember(id);

        findUser.setEmail(user.getEmail());
        findUser.setUsername(user.getUsername());

        String rawPassword = user.getPassword();

        if (rawPassword != null && rawPassword != "") {
            String encPassword = passwordEncoder.encode(rawPassword);
            findUser.setPassword(encPassword);
        } else {

        }

    }

//    @Transactional(readOnly = true)
//    public Member login(Member user) {
//        try {
//            return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
