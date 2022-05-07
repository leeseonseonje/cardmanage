package cardmanage.cardmanage.repository;

import cardmanage.cardmanage.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByUserId(String userId);
    Optional<Member> findByUserId(String userId);
}
