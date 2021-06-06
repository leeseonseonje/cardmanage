package cardmanage.cardmanage.controller;

import cardmanage.cardmanage.Service.CardService;
import cardmanage.cardmanage.domain.Card;
import cardmanage.cardmanage.dto.CardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping("/{memberId}/card")
    public String cardForm(@PathVariable Long memberId, Model model) {
        model.addAttribute("cardDto", new CardDto());
        return "card/createCardForm";
    }

    @PostMapping("/{memberId}/card")
    public String card(@PathVariable Long memberId, @Valid CardDto cardDto, BindingResult result) {
        if (result.hasErrors()) {
            return "card/createCardForm";
        }

        cardService.save(memberId, cardDto);
        return "redirect:/" + memberId + "/cards";
    }

    @GetMapping("/{memberId}/cards")
    public String cards(@PathVariable Long memberId, Model model) {
        List<Card> cards = cardService.findCards(memberId);
        model.addAttribute("cards", cards);

        return "card/cardList";
    }

    @GetMapping("/{memberId}/card/{cardId}")
    public String cardView(@PathVariable Long memberId, @PathVariable Long cardId, Model model) {
        Card card = cardService.findCard(cardId);
        model.addAttribute("card", card);

        return "card/cardView";
    }

    @GetMapping("/{memberId}/card/{cardId}/back")
    public String backButton(@PathVariable Long memberId, @PathVariable Long cardId, Model model) {
        Card card = cardService.findCard(cardId);
        model.addAttribute("card", card);

        return "redirect:/" + memberId + "/cards";
    }

    @GetMapping("/{memberId}/card-update/{cardId}")
    public String updateCardForm(@PathVariable Long memberId, @PathVariable Long cardId, Model model) {
        model.addAttribute("cardDto", new CardDto());
        return "card/updateCardForm";
    }

    @PostMapping("/{memberId}/card-update/{cardId}")
    public String updateCard(@PathVariable Long memberId, @PathVariable Long cardId,
                             @Valid CardDto cardDto, BindingResult result) {
        if (result.hasErrors()) {
            return "card/updateCardForm";
        }

        cardService.updateCard(cardId, cardDto);

        return "redirect:/" + memberId + "/cards";
    }

    @GetMapping("/{memberId}/card-delete/{cardId}")
    public String deleteCard(@PathVariable Long memberId, @PathVariable Long cardId) {
        cardService.deleteCard(cardId);

        return "redirect:/" + memberId + "/cards";
    }
}
