package ho.buddy.config.auth;

import ho.buddy.domain.Member;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
public class CustomUserContext extends User {

    private final Member member;

    public CustomUserContext(Member member,
        Collection<? extends GrantedAuthority> authorities) {

        super(member.getUsername(), member.getPassword(), authorities);

        this.member = member;

    }

}
