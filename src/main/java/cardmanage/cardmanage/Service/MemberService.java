package cardmanage.cardmanage.Service;

import cardmanage.cardmanage.domain.Member;
import cardmanage.cardmanage.dto.LoginDto;
import cardmanage.cardmanage.reposity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        if (memberRepository.existsByUserId(member.getUserId())) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public Boolean login(LoginDto loginDto) {
        Member findMember = memberRepository.findByUserId(loginDto.getUserId());
        return findMember.getPassword().equals(loginDto.getPassword());
    }

    public Long findMemberId(LoginDto loginDto) {
        Member findMember = memberRepository.findByUserId(loginDto.getUserId());
        return findMember.getId();
    }
}
