package cardmanage.cardmanage.reposity;

import cardmanage.cardmanage.domain.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("select c from Card c where c.member.id = :memberId")
    Page<Card> findCards(Long memberId, Pageable pageable);
}
