package cardmanage.cardmanage.controller;

import cardmanage.cardmanage.Service.MemberService;
import cardmanage.cardmanage.domain.Member;
import cardmanage.cardmanage.dto.LoginDto;
import cardmanage.cardmanage.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/new")
    public String signUpForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "member/createMemberForm";
    }

    @PostMapping("/member/new")
    public String signUp(@Valid MemberDto memberDto, BindingResult result) {
        if (result.hasErrors()) {
            return "member/createMemberForm";
        }

        Member member = Member.builder().userId(memberDto.getUserId()).password(memberDto.getPassword())
                .name(memberDto.getName()).build();
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "member/loginMemberForm";
    }

    @PostMapping("/login")
    public String login(@Valid LoginDto loginDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "member/createMemberForm";
        }

        if (memberService.login(loginDto)) {
            return "redirect:/" + memberService.findMemberId(loginDto);
        } else {
            throw new IllegalStateException("아이디 혹은 비밀번호가 틀렸습니다.");
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
