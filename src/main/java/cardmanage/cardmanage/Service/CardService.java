package cardmanage.cardmanage.Service;


import cardmanage.cardmanage.domain.Card;
import cardmanage.cardmanage.domain.Member;
import cardmanage.cardmanage.dto.CardDto;
import cardmanage.cardmanage.reposity.CardRepository;
import cardmanage.cardmanage.reposity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final MemberRepository memberRepository;

    //카드등록
    public Card save(Long memberId, CardDto cardDto) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 회원 입니다."));

        Card card = Card.builder()
                .name(cardDto.getName())
                .companyName(cardDto.getCompanyName())
                .location(cardDto.getLocation())
                .email(cardDto.getEmail())
                .phoneNumber(cardDto.getPhoneNumber())
                .position(cardDto.getPosition())
                .member(findMember)
                .build();

        return cardRepository.save(card);
    }

    //카드 조회
    public List<Card> findCards(Long memberId){
        return cardRepository.findCards(memberId);
    }

    public Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("존재하지 않는 명함 입니다."));
    }

    //카드수정
    public void updateCard(Long cardId, CardDto cardDto) {
        Card card = cardRepository.findById(cardId).orElseThrow(
                () -> new RuntimeException("존재하지 않는 명함 입니다."));

        card.updateCard(cardDto);
    }

    public void deleteCard(Long cardId) {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("존재하지 않는 명함 입니다."));
        cardRepository.delete(card);
    }
}
