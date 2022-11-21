package ho.buddy.controller.api;

import static org.springframework.http.HttpStatus.OK;

import ho.buddy.config.auth.CustomUserContext;
import ho.buddy.domain.Member;
import ho.buddy.dto.ResponseDto;
import ho.buddy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/auth/signup")
    public ResponseDto<Integer> save(@RequestBody Member user) {

        userService.register(user);

        return new ResponseDto<Integer>(OK, OK.value(), 1);
    }

    @PutMapping("/api/user/info/edit")
    public ResponseDto<Integer> update(@RequestBody Member user, @AuthenticationPrincipal
    CustomUserContext principal) {

        userService.update(principal.getMember().getId(), user);

        /* 변경된 세션 등록 */
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            user.getUsername(), principal.getPassword());
        // rawPassword 를 그대로 넣어야 한다.
        // 복호화해서 넣어야 한다.

        Authentication authentication = authenticationManager.authenticate(authToken);

        SecurityContext securityContext = SecurityContextHolder.getContext();

        securityContext.setAuthentication(authentication);

        return new ResponseDto<Integer>(OK, OK.value(), 1);
    }


    /*
    @PostMapping("/api/user/login")
    public ResponseDto<Member> login(@RequestBody Member user, HttpSession session) {

        // System.out.println("MemberApiController: login 호출");

        Member principal = userService.login(user);

        if (principal != null) {
            session.setAttribute("principal", principal);
            session.setAttribute("role", USER);
        }

        return new ResponseDto<Member>(OK, OK.value(), principal);
    }
    */
}
