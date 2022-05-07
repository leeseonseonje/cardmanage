package cardmanage.cardmanage.controller;

import cardmanage.cardmanage.service.CardService;
import cardmanage.cardmanage.domain.Card;
import cardmanage.cardmanage.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    //명함 등록 화면
    @GetMapping("/{memberId}/card")
    public String cardForm(@PathVariable Long memberId, Model model) {
        model.addAttribute("cardDto", new CardDto());
        return "card/createCardForm";
    }

    //명함 정보 DB에 등록
    @PostMapping("/{memberId}/card")
    public String card(@PathVariable Long memberId, @Valid CardDto cardDto, BindingResult result) {
        if (result.hasErrors()) {
            return "card/createCardForm";
        }

        cardService.save(memberId, cardDto);
        return "redirect:/" + memberId + "/cards?page=1";
    }

    //등록한 명함 목록
    @GetMapping("/{memberId}/cards")
    public String cards(@PathVariable Long memberId, Model model, @RequestParam("page") int page) {
        Page<Card> cardPage = cardService.findCards(memberId, page-1);

        if (cardPage.getContent().isEmpty()) {
            return "redirect:/" + memberId;
        }
        model.addAttribute("cards", cardPage.getContent());
        model.addAttribute("totalPages", (cardPage.getTotalPages()));
        return "card/cardList";
    }

    //명함 상세 보기
    @GetMapping("/{memberId}/card/{cardId}")
    public String cardView(@PathVariable Long memberId, @PathVariable Long cardId, Model model) {
        Card card = cardService.findCard(cardId);
        model.addAttribute("card", card);

        return "card/cardView";
    }

    //명함 수정 화면
    @GetMapping("/{memberId}/card/{cardId}/edit")
    public String updateCardForm(@PathVariable Long memberId, @PathVariable Long cardId, Model model) {
        model.addAttribute("cardDto", new CardDto());
        return "card/updateCardForm";
    }

    //명함 수정 DB
    @PostMapping("/{memberId}/card/{cardId}/edit")
    public String updateCard(@PathVariable Long memberId, @PathVariable Long cardId,
                             @Valid CardDto cardDto, BindingResult result) {
        if (result.hasErrors()) {
            return "card/updateCardForm";
        }

        cardService.updateCard(cardId, cardDto);

        return "redirect:/" + memberId + "/cards?page=1";
    }

    //명함 삭제(DB)
    @GetMapping("/{memberId}/card/{cardId}/delete")
    public String deleteCard(@PathVariable Long memberId, @PathVariable Long cardId) {
        cardService.deleteCard(cardId);

        return "redirect:/" + memberId + "/cards?page=1";
    }
}
