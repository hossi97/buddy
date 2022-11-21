package ho.buddy.repository;

import ho.buddy.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Long> {
//    Member findByUsernameAndPassword(String username, String password);
    Optional<Member> findByUsername(String username);


}
