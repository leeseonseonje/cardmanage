package cardmanage.cardmanage.reposity;

import cardmanage.cardmanage.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserId(String userId);
    Member findByUserId(String userId);
}
