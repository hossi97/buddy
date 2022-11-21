package ho.buddy.config.auth;

import ho.buddy.domain.Member;
import ho.buddy.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // username, password 를 가로채 username 이 DB 에 있는지 확인해주면 된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = userRepository.findByUsername(username)
            .orElseThrow(() -> {
                return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다: " + username);
            });
//        return new PrincipalDetail(principal);  // UserDetails 타입으로 시큐리티 세션에 유저정보가 저장된다.

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUserContext(principal, authorities);
    }
}