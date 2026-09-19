package jimin.scheduler.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member login(String id, String pw) {
        return memberRepository.findByIdAndPw(id, pw).orElse(null);
    }

    @Override
    public Member findMember(Long suid) {
        return memberRepository.findBySuid(suid).orElse(null);
    }
}
