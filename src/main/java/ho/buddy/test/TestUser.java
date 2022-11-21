package ho.buddy.test;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
// DB 에 있는 값을 가져올 것이므로 final 로 데이터가 변하지 않도록 잡아둔다.
// -> set 이 필요없을 때는 이렇게 설계해도 괜찮겠다만..
// > 데이터 업데이트 할 때는 안된다.
public class TestUser {
    private int id;
    private String username;
    private String password;
    private String email;
}
