package ho.buddy.test;

import static ho.buddy.domain.Role.USER;

import ho.buddy.domain.Member;
import ho.buddy.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(Member user) {

        user.setRole(USER);
        userRepository.save(user);

        return "회원가입이 완료됐습니다.";
    }

    @DeleteMapping("dummy/user/{id}")
    public String deleteMember(@PathVariable Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패했습니다. 해당 유저는 존재하지 않습니다. user_id: " + id;
        }

        return "삭제됐습니다. user_id: " + id;
    }

    @PutMapping("dummy/user/{id}")
    public Member updateMember(@PathVariable Long id, @RequestBody Member requestMember) {

        Member findMember = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalAccessError("해당 유저는 존재하지 않습니다. user_id: " + id);
        });

        findMember.setEmail(requestMember.getEmail());
        findMember.setPassword(requestMember.getPassword());

        userRepository.save(findMember);

        return null;
    }

    @GetMapping("dummy/user/{id}")
    public Member getMember(@PathVariable Long id) {
        Member user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalAccessError("해당 유저는 존재하지 않습니다. user_id: " + id);
        });
        return user;
    }

    @GetMapping("dummy/users/")
    public List<Member> getAllMember() {
        List<Member> users = userRepository.findAll();
        return users;
    }

    @GetMapping("dummy/user")
    public Page<Member> getMembersByPage(@PageableDefault(size=2, sort="id", direction = Direction.DESC)
    Pageable pageable) {
        Page<Member> users = userRepository.findAll(pageable);
        return users;
    }

}
